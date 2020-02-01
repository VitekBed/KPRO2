package guimodel;

import main.*;

import javax.swing.*;
import java.util.List;


public class ZakazkaListModel<T> extends AbstractListModel<T> {
    public boolean isUserMode() {
        return userMode;
    }

    public void setUserMode(boolean userMode) {
        this.userMode = userMode;
    }

    private boolean userMode = false;

    public ZakazkaListModel() {
        setUserMode(false);
    }
    public ZakazkaListModel(boolean userMode) {
        setUserMode(userMode);
    }

    @Override
    public int getSize() {
        if (isUserMode())
            return main.dataCollector.getListZakzaka(main.getUser()).size();
        else
            return main.dataCollector.getListZakzaka().size();
    }

    @Override
    public T getElementAt(int i) {
        if (isUserMode())
        {
            List<T> list = main.dataCollector.getListZakzaka(main.getUser());
            return  list.get(i);
        }
        else {
            List<T> list = main.dataCollector.getListZakzaka();
            return list.get(i);
        }

    }

}
