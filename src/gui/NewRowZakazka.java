package gui;

import main.main;
import struct.User;
import struct.Zakazka;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

class NewRowZakazka extends JDialog {
    NewRowZakazka(Zakazka zakazka, User user) {
        Container container = this.getContentPane();
        this.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        this.setTitle(zakazka.getName());
        JButton okButton = new JButton("Přidat");
        JButton stornoButton = new JButton("Storno");
        JPanel panel = new JPanel();

        JLabel label1 = new JLabel("Doba práce: ");
        JTextField trvani = new JTextField(4);
        JPanel panelzakazky = new JPanel();
        JLabel label2 = new JLabel("Popis práce: ");
        JTextArea popis = new JTextArea(4,20);
        panelzakazky.add(label1);
        panelzakazky.add(trvani);
        container.add(panelzakazky);
        container.add(label2);
        container.add(new JScrollPane(popis));
        panel.add(okButton);
        panel.add(stornoButton);
        container.add(panel);

        stornoButton.addActionListener(actionEvent -> this.setVisible(false));
        okButton.addActionListener(actionEvent -> {
            if (main.dataCollector.addZakazkaRow(zakazka,user,Float.parseFloat(trvani.getText()),popis.getText().replaceAll("(\\r|\\n)", " "), null))
                this.setVisible(false);
        });
    }

    public NewRowZakazka(Zakazka zakazka, Date datum, float doba, String text, User user) {
        Container container = this.getContentPane();
        this.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        this.setTitle(zakazka.getName());
        JButton okButton = new JButton("Upravit");
        JButton stornoButton = new JButton("Storno");
        JPanel panel = new JPanel();

        JLabel label1 = new JLabel("Doba práce: ");
        JTextField trvani = new JTextField(4);
        trvani.setText(String.valueOf(doba));
        JPanel panelzakazky = new JPanel();
        JLabel label2 = new JLabel("Popis práce: ");
        JTextArea popis = new JTextArea(4,20);
        popis.setText(text);
        panelzakazky.add(label1);
        panelzakazky.add(trvani);
        container.add(panelzakazky);
        container.add(label2);
        container.add(new JScrollPane(popis));
        panel.add(okButton);
        panel.add(stornoButton);
        container.add(panel);

        stornoButton.addActionListener(actionEvent -> this.setVisible(false));
        okButton.addActionListener(actionEvent -> {
            if (main.dataCollector.deleteRow(zakazka, datum) &&
                main.dataCollector.addZakazkaRow(zakazka,user,Float.parseFloat(trvani.getText()),popis.getText().replaceAll("(\\r|\\n)", " "),datum)) {
                this.setVisible(false);
            }
        });
    }


}
