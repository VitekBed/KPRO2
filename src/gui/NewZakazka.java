package gui;

import main.main;
import struct.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NewZakazka extends JDialog {
    private User user;
    public NewZakazka(User user) {
        this.user = user;
        New();
    }

    public NewZakazka() {
        New();
    }

    private void New() {
        Container container = this.getContentPane();
        this.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        JButton okButton = new JButton("Přidat");
        JButton stornoButton = new JButton("Storno");
        JPanel panel = new JPanel();

        JLabel label = new JLabel("Zakázka: ");
        JTextField zakazka = new JTextField(20);
        JPanel panelzakazky = new JPanel();
        panelzakazky.add(label);
        panelzakazky.add(zakazka);
        container.add(panelzakazky);
        panel.add(okButton);
        panel.add(stornoButton);
        container.add(panel);

        okButton.addActionListener(actionEvent -> {
            main.dataCollector.addZakazka(zakazka.getText(), user);
            this.getParent().revalidate();
            this.setVisible(false);
        });
        stornoButton.addActionListener(actionEvent -> this.setVisible(false));
    }


}
