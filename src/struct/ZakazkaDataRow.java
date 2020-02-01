package struct;

import java.util.Date;

public class ZakazkaDataRow implements Comparable {
    private String datum;
    private float trvani;
    private String popis;

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public float getTrvani() {
        return trvani;
    }

    public void setTrvani(float trvani) {
        this.trvani = trvani;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    public ZakazkaDataRow(String datum, float trvani, String popis, User user) {
        this.datum = datum;
        this.trvani = trvani;
        this.popis = popis;
        this.user = user;
    }

    @Override
    public int compareTo(Object o) {
        return this.getDatum().compareTo(((ZakazkaDataRow)o).getDatum());
    }
}
