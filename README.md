# KPRO2
Semestrální práce na KIKM-KPRO2 (FIM UHK) 2019/2020

## Zadání
Pro získání zápočtu je nutné odevzdat projekt v jazyce Java na libovolné téma, splňující následující minimální požadavky:

- [ ] Oddělení datového modelu od prezentační logiky.
- [ ] Použití alespoň jedné asociace 1:N (agregace) mezi třídami modelu (např. evidence nějakých záznamů).
- [ ] Požadovaná funkčnost z pohledu uživatele: 
  - [ ] přidání, úprava, smazání a zobrazení záznamu,
  - [ ] vyhledávání a řazení dat, 
  - [ ] ukládání a načítání dat ze souboru (nebo databáze, sítě, XML, ...).
- [ ] Použití GUI s využitím komponent JTable, JMenuBar a JToolBar při dodržení principů uvedených v první odrážce.

## Funkcionalita
### Hlavní cíle
- [ ] Program umožní evidovat práci na jednotlivých připomínkách a zakázkách
- [ ] Program si dokáže ze "serveru" natáhnout informace o jednotlivých zakázkách na kterých mám jako uživatl pracovat
a ty mi přednabízet
- [ ] Program si bude držet u sebe data o tom, jaké práce byly prováděny uživatelem a bude je synchronizovat se serverem
když to bude možné - uživatel tak bude mít přehled o své práci i offline
- [ ] Program __server__ umožní zobrazit data všech uživatelů podle uživatelů, zakázek

### Vedlejší cíle
- [ ] Evidovat půjde zakázku a konkrétní práci (při doplnění práce se automaticky dotáhne zakázka), uživatele který na 
tom pracoval a strávený čas
- [ ] Program uživateli ukáže kolik času danou prací již bylo celkové stráveno všemi uživateli a kolik zbývá
- [ ] V menu bude uživatel vybírat pouze ze zakázek a úkolů, na které byl přidělen, v tabulce se mu poté zobrazí přehled
statistika a tabulka jím odvedené práce

