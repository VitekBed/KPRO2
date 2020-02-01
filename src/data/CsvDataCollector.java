package data;

import struct.User;
import struct.Zakazka;
import struct.ZakazkaDataRow;

import java.io.*;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvDataCollector implements IDataCollector {
    @Override
    public int getTableRowCount(Zakazka zakazka) {
        if (zakazka == null) return 0;
        Path pathToFile = Paths.get("database\\tables\\zak"+zakazka.getID());
        if (!Files.exists(pathToFile)) {
            System.out.println("File " + pathToFile + " not exists!");
            return 0;
        }
        try {
            return (int)Files.lines(pathToFile).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Object getElementAt(int i, Zakazka.Column sloupec, Zakazka zakazka) {
        Path pathToFile = Paths.get("database\\tables\\zak"+zakazka.getID());
        if (!Files.exists(pathToFile)) {
            System.out.println("File " + pathToFile + " not exists!");
            return null;
        }
        if (zakazka.getZakazkaDataRowList() == null) fillDataRowList(zakazka);
        return zakazka.getElementAt(i, sloupec);
    }

    private void fillDataRowList(Zakazka zakazka) {
        Path pathToFile = Paths.get("database\\tables\\zak"+zakazka.getID());
        if (!Files.exists(pathToFile)) {
            System.out.println("File " + pathToFile + " not exists!");
        }
        List<ZakazkaDataRow> zakazkaDataRowList = new ArrayList<>();
        try {
            BufferedReader br = Files.newBufferedReader(pathToFile);
            String line = br.readLine();
            while (line != null) {
                String[] row = line.split(";");
                zakazkaDataRowList.add(new ZakazkaDataRow(row[0],Float.parseFloat(row[1]),row[2],getUser(Integer.parseInt(row[3]))));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        zakazka.setZakazkaDataRowList(zakazkaDataRowList);
    }

    @Override
    public <T> List<T> getListZakzaka() {
        Path pathToFile = Paths.get("database\\zakazky");
        if (!Files.exists(pathToFile)) {
            System.out.println("File not exists!");
            return null;
        }

        List<T> list = new ArrayList<>();
        try {
            BufferedReader br = Files.newBufferedReader(pathToFile);
            String line = br.readLine();
            while (line != null) {
                String[] row = line.split(";");
                list.add((T) new Zakazka(Integer.parseInt(row[0]),row[1]));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public <T> List<T> getListZakzaka(User user) {
        Path pathToFile = Paths.get("database\\pristup");
        if (!Files.exists(pathToFile)) {
            System.out.println("File not exists!");
            return null;
        }

        List<T> list = new ArrayList<>();
        try {
            BufferedReader br = Files.newBufferedReader(pathToFile);
            String line = br.readLine();
            while (line != null) {
                String[] row = line.split(";");
                if (Integer.parseInt(row[1]) == user.getID())
                    list.add((T) getZakazka(Integer.parseInt(row[0])));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Zakazka getZakazka(int id) {
        Path pathToFile = Paths.get("database\\zakazky");
        if (!Files.exists(pathToFile)) {
            System.out.println("File not exists!");
            return null;
        }

        Zakazka zakazka = null;
        try {
            BufferedReader br = Files.newBufferedReader(pathToFile);
            String line = br.readLine();
            while (line != null && zakazka == null) {
                String[] row = line.split(";");
                if (Integer.parseInt(row[0]) == id) zakazka = new Zakazka(id,row[1]);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zakazka;
    }

    @Override
    public int getUserID(String user) throws FileNotFoundException {
        Path pathToFile = Paths.get("database\\uzivatele");
        if (!Files.exists(pathToFile)) {
            System.out.println("File not exists!");
            throw new FileNotFoundException();
        }

        int id = 0;
        try {
            BufferedReader br = Files.newBufferedReader(pathToFile);
            String line = br.readLine();
            while (line != null && id == 0) {
                String[] row = line.split(";");
                if (row[1].equals(user)) id = Integer.parseInt(row[0]);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (id == 0) throw new NullPointerException();
        return id;
    }

    @Override
    public String getUserString(int user) throws FileNotFoundException {
        Path pathToFile = Paths.get("database\\uzivatele");
        if (!Files.exists(pathToFile)) {
            System.out.println("File not exists!");
            throw new FileNotFoundException();
        }

        String name = null;
        try {
            BufferedReader br = Files.newBufferedReader(pathToFile);
            String line = br.readLine();
            while (line != null && name == null) {
                String[] row = line.split(";");
                if (Integer.parseInt(row[0]) == user) name = (String)row[1];
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (name == null) throw new NullPointerException();
        return name;
    }

    @Override
    public User getUser(Object user) {
        if (user.getClass() == Integer.class)
        {
            try {
                return new User((int)user,getUserString((int)user));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if (user.getClass() == String.class)
        {
            try {
                return new User(getUserID((String)user),(String)user);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean addZakazka(String zakazka, User user) {
        if (zakazka.equals("")) return false;
        BufferedWriter writer = null;
        int id = getListZakzaka().size()+1;
        try {
            writer = new BufferedWriter(new FileWriter("database\\zakazky", true));
            writer.newLine();
            writer.append(id+";"+zakazka);
            writer.close();

            writer = new BufferedWriter(new FileWriter("database\\tables\\zak"+id));
            writer.close();

            if (user != null) {
                writer = new BufferedWriter(new FileWriter("database\\pristup", true));
                writer.newLine();
                writer.append(id + ";" + user.getID());
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addZakazkaRow(Zakazka zakazka, User user, float doba, String popisText, Date datum) {
        try {
            fillDataRowList(zakazka);
            BufferedWriter writer = null;
            writer = new BufferedWriter(new FileWriter("database\\tables\\zak"+zakazka.getID(),true));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            if (datum != null) date = datum;
            if (Files.size(Paths.get("database\\tables\\zak"+zakazka.getID()))>0 && zakazka.getZakazkaDataRowList().size()<Files.lines(Paths.get("database\\tables\\zak"+zakazka.getID())).count())
                writer.newLine();
            writer.append(dateFormat.format(date)+";"+doba+";"+popisText+";"+ user.getID());
            writer.close();

            addAccess(zakazka, user);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteRow(Zakazka zakazka, Date date) {
        fillDataRowList(zakazka);
        try {
            //Files.delete(Paths.get("database\\tables\\zak"+zakazka.getID()));
            BufferedWriter writer = null;
            writer = new BufferedWriter(new FileWriter("database\\tables\\zak"+zakazka.getID()));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i = 0; i < zakazka.getZakazkaDataRowList().size(); i++) {
                ZakazkaDataRow row = zakazka.getZakazkaDataRowList().get(i);
                Date datum = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(row.getDatum());
                if (!date.equals(datum))
                {
                    writer.write(dateFormat.format(datum)+";"+row.getTrvani()+";"+row.getPopis()+";"+ row.getUser().getID());
                    if (i != zakazka.getZakazkaDataRowList().size()-1) writer.newLine();
                }
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        fillDataRowList(zakazka);
        return true;
    }

    private void addAccess(Zakazka zakazka, User user) {
        if (!(getListZakzaka(user)).contains(zakazka)) {
            try {
                BufferedWriter writer = null;
                writer = new BufferedWriter(new FileWriter("database\\pristup", true));
                writer.newLine();
                writer.append(zakazka.getID() + ";" + user.getID());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
