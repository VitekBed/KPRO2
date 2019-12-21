package gui;

import data.IDataCollector;
import data.TxtDataCollector;
import guimodel.ZakazkaListModel;

import javax.swing.*;

public class Window extends JFrame {
    public Window () {
        this.setTitle("WorkLoger");

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //this.setAlwaysOnTop(true);

        this.setSize(550, 150);
        JList list = new JList<>(new ZakazkaListModel<>());
        this.add(list);

    }
}
