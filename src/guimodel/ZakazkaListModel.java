package guimodel;

import data.IDataCollector;
import main.*;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class ZakazkaListModel<T> extends AbstractListModel<T> {

    private final String zakazkaTableName = "zakazka";
    @Override
    public int getSize() {
        return main.dataCollector.getTableRowCount(zakazkaTableName);
    }

    @Override
    public T getElementAt(int i) {
        return main.dataCollector.<T>getElementAt(i,"nazev",zakazkaTableName);
    }

}
