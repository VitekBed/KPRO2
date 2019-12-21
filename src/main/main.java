package main;

import data.*;
import gui.*;

public class main {
    public static void main (String[] args)
    {
        System.out.println("191221.1057 - VBE");
        new Window();
    }
    final public static IDataCollector dataCollector = new TxtDataCollector();
}
