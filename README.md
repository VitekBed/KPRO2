# KPRO2
Semestrální práce na KIKM-KPRO2 (FIM UHK) 2019/2020

## Návod k použití
Aplikace není zcela intuitivní a má několik chyb. Proto raději nabízím nějaký popis.

### Práce s daty
Po spuštění je aplikace připravena v režimu, kdy jsou vidět všechny zakázky v databázi. Kliknutím na název
zakázky v levém menu se zobrazí záznamy o práci prováděné na konkrétní zakázce. 
#### Uživatelé - filtrování
Jsou připraveni 4 uživatelé,
podle kterých si lze zafiltrovat na omezené množství zakázek (jména: *Já*, *Jitka*, *David*, *Martina*). Vyplněním jména
do pole v MenuBaru a klinutím na `OK` lze spustit zafiltrování. Vypnout (i zapnout) filtrování lze v menu Filtr.

Vyplnění uživatele je potřeba také v okamžiku zakládání nového řádku do tabulky. Pokud do tabulky uživatel
přidá řádek a zakázku nemá v omezeném výběru zakázek, pak se mu tam přidá. Při pokusu přidat řádek bez zadání uživatele,
vyskočí varovná hláška.

Pokud se zakládá nová zakázka a uživatel je vyplněn, přidá se mu zakázka do omezeného výběru hned při založení.

**Možnost Filtr / Kompletní přehled zafunguje docela dobře i v některých případech, kdy se napřenačtou data**
#### Práce s tabulkou zakázky
- **Nový řádek** - je možné jej vytvořit přes menu Editace / Nový řádek, nebo stisknutím `N` v ToolBaru. Nejprve je nutné
mít vyplněné jméno uživatele pod kterým chci záznam vložit v textovém poli umístěném v MenuBaru. Pro vkládání nového
záznam se zobrazuje "formulář", kde je potřeba vyplnit počet hodin (lze desetinné číslo, ale pouze s desetinnou tečkou `.`)
a je možné vyplnit popis práce. Stisknutím `OK` se data uloží, pokud se uložení povede, okno se zavře.
- **Smazat řádek** - je možné kdykoliv, nejprve je potřeba požadovaný řádek označit v tabulce, poté lze řádek smazat přes
menu Editace / Smazat řádek, nebo stisknutím `S` v ToolBaru.
- **Editovat řádek** - je možné kdykoli, nejprve je potřeba požadovaný řádek označit v tabulce, poté lze řádek upravit přes
menu Editace / Editace řádku nebo stisknutím `E` v ToolBaru. Zobrazí se formulář totožný s předvyplěnými hodnotami, které
je možné upravit, stejně jako když se zadává nový řádek.
#### Přidání zakázky
Pro práci se zakázkami mám pouze jedinou možnost a to je přidávat nové. V menu Editace / Nová zakázka lze zadat název nové
zakázky, založí se záznam do repositorální tabulky a  případně pokud je vyplněn uživatel, tak se zakázka
přidá do omezeného výběru zadaného uživatele.

Mazání zakázek je potřeba dělat ručně v tabulce **zakazky** (a případně přečíslovat). Mazání tabulek zakázek není potřeba, při založení
nové se přepíší.

### Struktura uložených dat
- Veškerá data jsou ukládána v csv formátu s oddělovačem `;`.
- Neceločíselné hodnoty jsou ukládány s desetinnou tečkou `.`.
- Datumy jsou ukládány ve formátu `yyyy-MM-dd HH:mm:ss`. 
- Tabulky by na svém konci neměly obsahovat prázdné řádky.
- Tabulky nesmí obsahovat prázdné řádky mezi řádky s daty.

#### Přehled "tabulek"
Data se kterými program pracuje jsou uloženy ve třech "repositorálních" tabulkách a neomezeném nožství dalších tabulek,
které reprezentují jednotlivé "zakázky". Repositorální tabulky jsou uloženy přímo ve složce `database`, tabulky k
jednotlivým "zakázkám" jsou ukládány v `databace\tables`.

**Repositorální tabulky**
- **uzivatele** - překladová tabulka mezi číslem uživatele a jeho jménem. V tuto chvíli není vše připraveno
na vyhledávání výhradně podle čísel uživatel a je tedy mít unikátní číslo i jméno. Tím tato tabulka pozbývá
v tuto chvíli většího smyslu.
  - struktura tabulky: `číslo_uživatele;název_uživatele` 
- **zakazky** - překladová tabulka mezi číslem zakázky a jeho jménem. Obdobná jako `uzivatele`.
  - struktura tabulky: `číslo_zakázky;název_zakázky`
- **pristup** - tabulka umožňující filtrování zakázek, na které zadaná osoba pracovala. Do této tabulky se
zapisují záznamy při zakládání nové zakázky nebo při zadávání nového řádku, pokud tam již uživatel není 
uveden. Jedná se o vazební tabulku N:N mezi zakázkami a uživateli.
  - struktura tabulky: `číslo_zakázky;číslo_uživatele`

**Tabulky takázek**

Tyto tabulky obsahují data ke konkrétní zakázce, vytváří se v okamžiku kdy se vytvoří nová zakázka. Jejich
jméno je slouženo s předpony `zak` a čísla zakázky v tabulce **zakazky**.

Struktura tabulek `datum_ulozeni;doba_trvani;popis;cislo_uzivatele` 

## Známé chyby
- **\#1 Nutnost unikátních názvů osob**, data do GUI je potřeba načítat tak, aby se dalo získávat číslo řádku souboru,
respektive nějaké unikátní identifikační číslo záznamu v tabulce a pod tímto si předávat informace mezi GUI 
a Interfacem `IDataCollector` pro práci s daty.  
- **\#2 Neumím si poradit s prázdnými řádky**, pokud z nějakého důvodu v tabulkách zakázek vzniknou prázdné
řádky (nyní už snad nevznikají), zastaví to načítání dat zakázky, tabulka se nepřepíše. 
- **\#3 Není možné zafiltrovat v rámci tabulky**, filtrují se mi pouze záznamy zakázek podle repozitorální
tabulky **pristupy**. Chtěl bych umožnit zafiltrovat i na konkrétní osobu v tabulce s přehledem prací.
*RowSorter* pomocí kterého jsem to zkoušel mi nezabral tak jak jsem očekával.
- **\#4 Po přidání zakázky se neprovede refresh**, je nutné kliknout na menu Filtr a zvolit žádaný způsob zafiltrování.
- **\#5 Neexistuje mazání nebo editace zakázek** 
## Zadání
Pro získání zápočtu je nutné odevzdat projekt v jazyce Java na libovolné téma, splňující následující minimální požadavky:

- [X] Oddělení datového modelu od prezentační logiky.
- [X] Použití alespoň jedné asociace 1:N (agregace) mezi třídami modelu (např. evidence nějakých záznamů).
- [X] Požadovaná funkčnost z pohledu uživatele: 
  - [X] přidání, úprava, smazání a zobrazení záznamu,
  - [X] vyhledávání a řazení dat, 
  - [X] ukládání a načítání dat ze souboru (nebo databáze, sítě, **XML**, ...).
- [X] Použití GUI s využitím komponent JTable, JMenuBar a JToolBar při dodržení principů uvedených v první odrážce.

## Plánovaná funkcionalita 
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

