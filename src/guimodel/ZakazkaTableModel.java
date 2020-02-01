package guimodel;

import main.main;
import struct.Zakazka;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class ZakazkaTableModel extends AbstractTableModel implements TableModel {
    private boolean tableUserMode = false;

    public boolean isTableUserMode() {
        return tableUserMode;
    }

    public void setTableUserMode(boolean tableUserMode) {
        this.tableUserMode = tableUserMode;
    }

    public void setTableUserMode() {
        setTableUserMode(!isTableUserMode());
    }

    public Zakazka getZakazka() {
        return zakazka;
    }

    public void setZakazka(Zakazka zakazka) {
        this.zakazka = zakazka;
    }

    private Zakazka zakazka;
    @Override
    public int getRowCount() {
        if (zakazka == null) return 0;
        return main.dataCollector.getTableRowCount(getZakazka());
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int i) {
        switch (i)
        {
            case 0:
                return "Datum";
            case 1:
                return "Hodin";
            case 2:
                return "Popis";
            case 3:
                return "Kdo";
            default:
                return "X";
        }
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {

        switch (col)
        {
            case 0: return main.dataCollector.getElementAt(row, Zakazka.Column.KDY,zakazka);
            case 1: return main.dataCollector.getElementAt(row, Zakazka.Column.TRVANI,zakazka);
            case 2: return main.dataCollector.getElementAt(row, Zakazka.Column.POPIS,zakazka);
            case 3: return main.dataCollector.getElementAt(row, Zakazka.Column.UZIVATEL,zakazka);
            default: return null;
        }
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {

    }

    @Override
    public void addTableModelListener(TableModelListener tableModelListener) {

    }

    @Override
    public void removeTableModelListener(TableModelListener tableModelListener) {

    }
}
