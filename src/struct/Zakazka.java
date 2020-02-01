package struct;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Zakazka extends BasicObject {
    private List<ZakazkaDataRow> zakazkaDataRowList;

    public Zakazka() {
        super();
    }

    @Override
    public int hashCode() {
        int hash = 11;
        hash = 71 * hash + this.getID();
        hash = 71 * hash + this.getName().hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getClass() == obj.getClass()
                && this.getID() == ((Zakazka)obj).getID()
                && this.getName().equals(((Zakazka)obj).getName()));
    }

    public Zakazka(int ID, String name) {
        super(ID, name);
    }

    public List<ZakazkaDataRow> getZakazkaDataRowList() {
        if (zakazkaDataRowList == null) return null;
        zakazkaDataRowList.sort(ZakazkaDataRow::compareTo);
        return zakazkaDataRowList;
    }

    public void setZakazkaDataRowList(List<ZakazkaDataRow> zakazkaDataRowList) {
        this.zakazkaDataRowList = zakazkaDataRowList;
    }

    public Object getElementAt(int row, Column sloupec) {
        ZakazkaDataRow z = zakazkaDataRowList.get(row);
        switch (sloupec)
        {
            case KDY: return z.getDatum();
            case POPIS: return z.getPopis();
            case TRVANI: return z.getTrvani();
            case UZIVATEL: return z.getUser().getName();
            default: return null;
        }
    }

    public enum Column {
        KDY,TRVANI,POPIS,UZIVATEL
    }
}
