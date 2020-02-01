package gui;

import guimodel.ZakazkaListModel;
import guimodel.ZakazkaTableModel;
import main.main;
import struct.User;
import struct.Zakazka;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Window extends JFrame {
    public Window () {
        this.setTitle("WorkLoger");

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //this.setAlwaysOnTop(true);

        this.setSize(550, 300);

        JList list = new JList<>(new ZakazkaListModel<>());
        this.add(new JScrollPane(list), BorderLayout.WEST);

        //JPanel menuPanel = new JPanel();

        JLabel label = new JLabel("Uživatel: ");
        JTextField userArea = new JTextField(2);
        /*
        JButton completeButton = new JButton("Kompletní přehled");
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ListModel listModel = new ZakazkaListModel();
                list.setModel(listModel);
            }
        });

        JButton userViewButton = new JButton("Přehled uživatele");
        userViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ListModel listModel = new ZakazkaListModel(true);
                main.setUser(userArea.getText());
                list.setModel(listModel);
            }
        });
         */
        //this.add(menuPanel, BorderLayout.NORTH);


        JTable table = new JTable();
        TableModel tableModel = new ZakazkaTableModel();
        table.setModel(tableModel);
        table.setAutoCreateRowSorter(true);
        /*TableRowSorter<ZakazkaTableModel> rowSorter = new TableRowSorter<>((ZakazkaTableModel)table.getModel());
        rowSorter.setComparator(0, Comparator.naturalOrder());
        table.setRowSorter(rowSorter);*/

        this.add(new JScrollPane(table), BorderLayout.CENTER);

        //menuPanel.add(completeButton);
        //menuPanel.add(userViewButton);
        //menuPanel.add(label);
        //menuPanel.add(userArea);

            JMenuBar mb;
            JMenu soubor,editace,filtr;
            JMenuItem save,refresh;
            JMenuItem delete,insert, newZakazka, edit;
            JMenuItem oneuser,everyone;
            JCheckBoxMenuItem tableoneuser;

            soubor = new JMenu("Soubor");
                save = new JMenuItem("Uložit"); save.setEnabled(false);
                refresh = new JMenuItem("Znovu načíst");
            editace = new JMenu("Editace");
                delete = new JMenuItem("Smazat řádek");
                insert = new JMenuItem("Nový řádek");
                edit = new JMenuItem("Editace řádku");
                newZakazka = new JMenuItem("Přidat zakázku");
            filtr = new JMenu("Filtr");
                everyone = new JMenuItem("Kompletní přehled");
                oneuser = new JMenuItem("Přehled uživatele");
                tableoneuser = new JCheckBoxMenuItem("Přehled uživatele v tabulce",false);
                tableoneuser.setEnabled(false);


        oneuser.addActionListener(actionEvent -> {
            ListModel listModel = new ZakazkaListModel(true);
            main.setUser(userArea.getText());
            list.setModel(listModel);
        });
        everyone.addActionListener(actionEvent -> {
            ListModel listModel = new ZakazkaListModel();
            list.setModel(listModel);
        });
        tableoneuser.addActionListener(actionEvent -> {
            ((ZakazkaTableModel) tableModel).setTableUserMode();

        });

            mb = new JMenuBar();
            mb.add(soubor);mb.add(editace);mb.add(filtr);

            soubor.add(save);soubor.add(refresh);
            editace.add(insert);editace.add(delete);editace.add(edit);editace.add(newZakazka);
            filtr.add(oneuser);filtr.add(everyone);filtr.add(tableoneuser);

            mb.add(new JLabel("  |  ")); mb.add(label); mb.add(userArea);

            JButton filtrButton = new JButton("OK");
            filtrButton.addActionListener(actionEvent -> {
                ListModel listModel = new ZakazkaListModel(true);
                main.setUser(userArea.getText());
                list.setModel(listModel);
            });
            mb.add(filtrButton);

            this.setJMenuBar(mb);

        this.revalidate();
        this.repaint();

        list.addListSelectionListener(listSelectionEvent -> {
                    Zakazka zakazka = (Zakazka)list.getSelectedValue();
                    ((ZakazkaTableModel)table.getModel()).setZakazka(zakazka);
                    table.setAutoCreateRowSorter(false);
                    table.setAutoCreateRowSorter(true);
                    table.revalidate();
                    table.repaint();
                });
        newZakazka.addActionListener(actionEvent -> {
            JDialog dialog;
            String userString = userArea.getText();
            if (!userString.equals(""))
                dialog = new NewZakazka(main.dataCollector.getUser(userArea.getText()));
            else
                dialog = new NewZakazka();
            dialog.setVisible(true);
            dialog.pack();
            dialog.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent windowEvent) {

                }

                @Override
                public void windowClosing(WindowEvent windowEvent) {
                    list.revalidate();
                    list.repaint();
                    table.setAutoCreateRowSorter(false);
                    table.revalidate();
                    table.repaint();
                    table.setAutoCreateRowSorter(true);
                }

                @Override
                public void windowClosed(WindowEvent windowEvent) {

                }

                @Override
                public void windowIconified(WindowEvent windowEvent) {

                }

                @Override
                public void windowDeiconified(WindowEvent windowEvent) {

                }

                @Override
                public void windowActivated(WindowEvent windowEvent) {

                }

                @Override
                public void windowDeactivated(WindowEvent windowEvent) {

                }
            });
        });
        insert.addActionListener(actionEvent -> {
            String userString = userArea.getText();
            if (userString.equals(""))
                JOptionPane.showMessageDialog(this, "Nejprvte vyplňte existujícího uživatele!", "Chyba", JOptionPane.ERROR_MESSAGE);
            else
            {
                User user = null;
                try {user = main.dataCollector.getUser(userString);}
                catch (NullPointerException e)
                {/*Schválně utopená chyba*/}
                if (user == null)
                    JOptionPane.showMessageDialog(this, "Nejprvte vyplňte existujícího uživatele!", "Chyba", JOptionPane.ERROR_MESSAGE);
                else if(list.getSelectedValue() == null)
                    JOptionPane.showMessageDialog(this, "Nejprvte vyberte zakázku v levém menu!", "Chyba", JOptionPane.ERROR_MESSAGE);
                else
                {
                    JDialog dialog = new NewRowZakazka((Zakazka)list.getSelectedValue(),user);
                    dialog.setVisible(true);
                    dialog.pack();
                    dialog.addWindowListener(new WindowListener() {
                        @Override
                        public void windowOpened(WindowEvent windowEvent) {

                        }

                        @Override
                        public void windowClosing(WindowEvent windowEvent) {
                            list.revalidate();
                            list.repaint();
                            table.setAutoCreateRowSorter(false);
                            table.revalidate();
                            table.repaint();
                            table.setAutoCreateRowSorter(true);
                        }

                        @Override
                        public void windowClosed(WindowEvent windowEvent) {

                        }

                        @Override
                        public void windowIconified(WindowEvent windowEvent) {

                        }

                        @Override
                        public void windowDeiconified(WindowEvent windowEvent) {

                        }

                        @Override
                        public void windowActivated(WindowEvent windowEvent) {

                        }

                        @Override
                        public void windowDeactivated(WindowEvent windowEvent) {

                        }
                    });
                }
            }
        });
        delete.addActionListener(actionEvent -> {
            for (int i : table.getSelectedRows()) {
                try {
                    main.dataCollector.deleteRow((Zakazka)list.getSelectedValue(), new SimpleDateFormat("yyyy-MM-ss HH:mm:ss").parse(table.getValueAt(i,0).toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                table.setAutoCreateRowSorter(false);
                table.revalidate();
                table.repaint();
                table.setAutoCreateRowSorter(true);
            }
        });
        edit.addActionListener(actionEvent -> {
            JDialog dialog = null;
            try {
                Zakazka zak = (Zakazka)list.getSelectedValue();
                Date dat = new SimpleDateFormat("yyyy-MM-ss HH:mm:ss").parse(table.getValueAt(table.getSelectedRow(),0).toString());
                float doba = (float)table.getValueAt(table.getSelectedRow(),1);
                String text = (String)table.getValueAt(table.getSelectedRow(),2);
                User user = main.dataCollector.getUser((String) table.getValueAt(table.getSelectedRow(),3));
                dialog = new NewRowZakazka(zak, dat, doba, text, user);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dialog.setVisible(true);
            dialog.pack();
            dialog.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent windowEvent) {

                }

                @Override
                public void windowClosing(WindowEvent windowEvent) {
                    list.revalidate();
                    list.repaint();
                    table.setAutoCreateRowSorter(false);
                    table.revalidate();
                    table.repaint();
                    table.setAutoCreateRowSorter(true);
                }

                @Override
                public void windowClosed(WindowEvent windowEvent) {

                }

                @Override
                public void windowIconified(WindowEvent windowEvent) {

                }

                @Override
                public void windowDeiconified(WindowEvent windowEvent) {

                }

                @Override
                public void windowActivated(WindowEvent windowEvent) {

                }

                @Override
                public void windowDeactivated(WindowEvent windowEvent) {

                }
            });
        });
        refresh.addActionListener(actionEvent -> {
            table.setAutoCreateRowSorter(false);
            table.revalidate();
            table.repaint();
            table.setAutoCreateRowSorter(true);
            ((AbstractTableModel)tableModel).fireTableDataChanged();
        });

        JToolBar toolBar = new JToolBar();
        this.add(toolBar, BorderLayout.NORTH);
        JButton btn1 = new JButton("N");
        btn1.setPreferredSize(new Dimension(40,20));
        JButton btn2 = new JButton("S");
        btn2.setPreferredSize(new Dimension(40,20));
        JButton btn3 = new JButton("E");
        btn3.setPreferredSize(new Dimension(40,20));
        toolBar.add(btn1);
        toolBar.add(btn2);
        toolBar.add(btn3);
        btn1.addActionListener(insert.getListeners(ActionListener.class)[0]);
        btn2.addActionListener(delete.getListeners(ActionListener.class)[0]);
        btn3.addActionListener(edit.getListeners(ActionListener.class)[0]);
    }
}
