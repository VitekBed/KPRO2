package data;

import struct.User;
import struct.Zakazka;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

public interface IDataCollector {
    /**
     * Metoda vrací počet řádků v tabulce
     * @param table Název tabulky
     * @return počet řádků
     */
    int getTableRowCount(Zakazka zakazka);

    /**
     * Vrací hondotu v daném místě tabulky
     * @param i Číslo řádku
     * @param sloupec Název sloupce
     * @param zakazka Instance zakázky
     * @return
     */
    Object getElementAt (int i, Zakazka.Column sloupec, Zakazka zakazka);

    /**
     * Vrací seznam všech zakázek v tabulce zakázek
     * @param <T> Typ návratové hodnoty (záleží na implementaci, int pro číselnou hodnotu, string pro textový popis)
     * @return Seznam zakázek
     */
    <T> List<T> getListZakzaka();

    /**
     * Vrací seznam zakázek omezený na zakázky přidělené konkrétnímu uživateli
     * @param user Identifikace uživatele
     * @param <T> Typ návratové hodnoty (záleží na implementaci, int pro číselnou hodnotu, string pro textový popis)
     * @param <U> Typ hodnoty pomocí které identifikujeme uživatele (záleží na implementaci, int pro číselnou hodnotu, string pro textový popis)
     * @return Seznam zakázek
     */
    <T> List<T> getListZakzaka(User user);

    //<T> String getUserString(T user);
    int getUserID(String user) throws FileNotFoundException;
    String getUserString(int user) throws FileNotFoundException;
    User getUser(Object user);


    boolean addZakazka(String zakazka, User user);

    boolean addZakazkaRow(Zakazka zakazka, User user, float doba, String popisText, Date datum);

    boolean deleteRow(Zakazka zakazka, Date date);
}
