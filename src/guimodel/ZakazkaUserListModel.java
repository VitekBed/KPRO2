package guimodel;

import main.main;

import javax.management.ValueExp;
import javax.swing.*;
import java.util.List;

@Deprecated(since = "Since Commit 200201. Nahrazeno doplněním ZakazkaListModel i pro případy isUserMode.", forRemoval = true)
public class ZakazkaUserListModel<T> extends AbstractListModel<T> {
    private int user = 0;

    public ZakazkaUserListModel(int user) {
        setUser(user);
    }

    public String getUserString() {
        throw new UnsupportedOperationException();
    }
    public int getUser(){
        return user;
    }

    public void setUser(String user) {
        throw new UnsupportedOperationException();
    }
    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public int getSize() {
        //return main.dataCollector.getListZakzaka(user).size();
        return 0;
    }

    @Override
    public T getElementAt(int i) {
        //if (user == 0) throw new IllegalArgumentException("User cannot be 0");
        //List<T> list = main.dataCollector.getListZakzaka(user);
        //return  list.get(i);
        return null;
    }
}

