package gui;

import javax.swing.*;

public class Window extends JFrame {
    public Window ()
    {
		/*Data = new ArrayList<String[]>();
		Data.add(new String[]{"teď", "Toto", "Takto"});
		Data.add(new String[]{"předtím","Tamto","Tak"});
		Data.add(new String[] {"teď", "Toto", "Takto"});*/

        this.setTitle("WorkLoger");

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setAlwaysOnTop(true);


        //this.pack();
        this.setSize(550, 150);


    }
}
