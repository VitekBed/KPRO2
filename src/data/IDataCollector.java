package data;

public interface IDataCollector {
    /**
     * Metoda vrací počet řádků v tabulce
     * @param table Název tabulky
     * @return počet řádků
     */
    int getTableRowCount(String table);

    <T> T getElementAt (int i, String nazev, String table);
}
