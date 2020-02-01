package data;

import struct.User;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
/*
@Deprecated(forRemoval = true)
public class MockDataCollector implements IDataCollector {

    @Override
    public int getTableRowCount(String table) {
        return 3;
    }

    @Override
    public <T> T getElementAt(int i, String sloupec, String table) {
        switch (i){
            case 0: return (T)sloupec;
            case 1: return (T)"test1";
            case 2: return (T)"test2";
        }
        return null;
    }

    @Override
    public <T> List<T> getListZakzaka() {
        List<T> list = new ArrayList<>();
        list.add((T)("zakazka 1"));
        list.add((T)("zakazka 2"));
        list.add((T)("zakazka 3"));
        return list;
    }

    @Override
    public <T> List<T> getListZakzaka(User user) {
        List<T> list = new ArrayList<>();
        list.add((T)("zakazka 1"));
        list.add((T)("zakazka 3"));
        return list;
    }

    @Override
    public int getUserID(String user) throws FileNotFoundException {
        return 1;
    }

    @Override
    public String getUserString(int user) throws FileNotFoundException {
        return "test";
    }

    @Override
    public User getUser(Object user) {
        return new User(1,"test");
    }
}
*/