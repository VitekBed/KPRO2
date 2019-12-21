package data;

public class TxtDataCollector implements IDataCollector {

    @Override
    public int getTableRowCount(String table) {
        return 3;
    }

    @Override
    public <T> T getElementAt(int i, String nazev, String table) {
        switch (i){
            case 0: return (T)nazev;
            case 1: return (T)"test1";
            case 2: return (T)"test2";
        }
        return null;
    }
}
