package main;

import data.*;
import gui.*;
import struct.User;

public class main {
    public static void main(String[] args) {
        System.out.println("191221.1057 - VBE");
        new Window();
    }

    //protože pro klienta, může být následující static, jedná se o jakousi cache klienta
    //final public static IDataCollector dataCollector = new MockDataCollector();
    final public static IDataCollector dataCollector = new CsvDataCollector();

    public static User getUser() {
        return user;
    }

    public static void setUser(String user) {
        main.user = dataCollector.getUser(user);
    }

    public static User user = null;
}
