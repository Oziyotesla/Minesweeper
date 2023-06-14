# Fejlesztői dokumentáció

## MinesweeperGUI osztály

A `MinesweeperGUI` osztály egy felhasználói felületet nyújt a "Minesweeper" játékhoz. Az osztály felelős a játéktábla megjelenítéséért, a gombok kezeléséért és a játékállapot frissítéséért.

### Konstruktor:

```java
public MinesweeperGUI(int rows, int cols)
```

- `rows`: A játéktábla sorainak száma.
- `cols`: A játéktábla oszlopainak száma.

Inicializálja a játékot a megadott mérettel. Betölti a szükséges ikonokat (zászló, számok, bomba), majd meghívja az `initializeBoard()` és az `initializeGUI()` metódusokat.

### Metódusok:

```java
private void initializeBoard()
```

Inicializálja a játéktáblát, létrehozva egy üres `int[][]` tömböt a sorok és oszlopok számával.

```java
private void initializeGUI()
```

Inicializálja a grafikus felületet (`JFrame`). Létrehoz egy `JPanel`-t a gombok elhelyezéséhez, majd létrehozza és hozzáadja a gombokat a panelhez. Beállítja a megfelelő méreteket, betűtípust és eseménykezelőt. Végül hozzáadja a panelt a `JFrame`-hez, beállítja a láthatóságot és a megjelenítési helyet.

```java
private class ButtonListener extends MouseAdapter
```

Ez az osztály felelős a gombok eseménykezeléséért. Figyeli a gombok kattintását és a jobb egérgomb lenyomását.

```java
private void revealMines(int i, int j)
```

Megjeleníti az összes bomba ikont a játéktáblán, a megadott pozíció kivételével.

```java
public void revealEmptyCells(int row, int col)
```

Megjeleníti a játéktáblán az üres cellákat, rekurzívan felfedve az összes szomszédos üres cellát is. A `row` és `col` paraméterek meghatározzák a kiindulási cella pozícióját.

```java
private void disableButtons()
```

Letiltja az összes gombot a játéktáblán.

```java
public void victory()
```

Megjeleníti a "Gratulálunk! Nyertél!" üzenetet és letiltja az összes gombot.

```java
public void defeat()
```

Megjeleníti a "Játék vége!" üzenetet és letiltja az összes gombot.

```java
public void changeFlagStatus(int x, int y, boolean flag)
```

Megváltoztatja a megadott pozícióban lévő gomb ikonját a zászló ikonra vagy a nullára (`null`) a `flag` paraméter alapján.

Ezen felül a `MinesweeperGUI` osztály rendelkezik a következő adattagokkal:

- `private JButton[][] buttons`: A játéktábla gombjait tartalmazó 2D-s tömb.


## Main Menu Window

Az osztály konstruktora inicializálja a főmenü ablakát. Beállítja az ablak címét, bezárás működését és a méretet. Létrehoz egy `JPanel` objektumot, amely a főmenüt fogja tartalmazni. Beállítja a panel elrendezését `GridLayout`-ra, amely egy oszlopos elrendezést használ. Beállítja a háttérszínt, a margókat és a belső margókat.

Létrehozza a különböző gombokat (`Difficulty`, `1 Player`, `2 Player`, `Connect`) és beállítja a gomb méretét, betűtípusát és eseménykezelőt. Az eseménykezelők reagálnak a gomb lenyomására és elvégzik a megfelelő műveleteket. Például a `Difficulty` gomb lenyomásakor megnyitja a nehézségi ablakot (`DifficultyWindow`), a `1 Player` gomb lenyomásakor pedig elindítja a játékot egyjátékos módra.

Létrehozza a `textField` objektumot, amely egy szövegmezőt tartalmaz.

Hozzáadja a gombokat és a szövegmezőt a panelhez, majd beállítja a panelt az ablak tartalmára. Beállítja az ablak méretét és helyzetét, majd láthatóvá teszi az ablakot.

#### `openMinesweeperGUI()`

Ez a privát segédmetódus a Minesweeper GUI-t nyitja meg. Beállítja a játékmező méretét és az aknák számát. Bezárja a főmenü ablakot, majd létrehozza és megjeleníti a `MinesweeperGUI` objektumot a megadott paraméterekkel.

#### `main(String[] args)`

Ez a metódus az alkalmazás belépési pontja. Létrehoz egy `MainMenuWindow` objektumot, amely inicializálja és megjeleníti a főmenüt.

Ez a dokumentáció röviden áttekinti a `MainMenuWindow` osztályt és annak metódusait, amelyek felelősek a főmenü grafikus felhasználói felületének létrehozásáért és az események kezeléséért.
- `private int[][] board`: A játéktábla állapotát tároló 2D-s tömb.
- `private int rows`: A játéktábla sorainak száma.
- `private int cols`: A játéktábla oszlopainak száma.
- `private ImageIcon flagIcon`: A zászló ikon.
- `private ImageIcon[] numberIcons`: A számok ikonjainak tömbje.
- `private ImageIcon bombIcon`: A bomba ikon.


## MultiPlayer Window

#### Metódusok:

#### `MultiPlayerWindow()`

Az osztály konstruktora inicializálja a többjátékos ablakát. Beállítja az ablak címét és a méretet. Létrehoz egy `JPanel` objektumot, amely a többjátékos ablak tartalmát fogja tartalmazni. Beállítja a panel elrendezését `GridLayout`-ra, amely egy oszlopos elrendezést használ. Beállítja a háttérszínt, a margókat és a belső margókat.

Létrehozza a "Create Server" gombot (`createServerButton`) és beállítja a méretét, betűtípusát és eseménykezelőt. Az eseménykezelő arra reagál, hogyha a gombot megnyomják, és meghívja a `Main.CreateServer()` metódust.

Létrehozza a `connectedIndicator` nevű `JLabel` objektumot, amely az állapotjelzőt fogja tartalmazni. Beállítja a méretét és frissíti az állapotjelzőt a `updateConnectedIndicator()` metódus segítségével.

Létrehozza a "Start Game" gombot (`startGameButton`) és beállítja a méretét, betűtípusát és eseménykezelőt. Az eseménykezelő arra reagál, hogyha a gombot megnyomják, és elindítja a többjátékos játékot a `Main.clickGame(true)` és `Main.createTimer()` metódusok meghívásával, valamint bezárja a többjátékos ablakot.

Hozzáadja a gombokat és az állapotjelzőt a panelhez, majd beállítja a panelt az ablak tartalmára. Beállítja az ablak méretét és helyzetét, majd láthatóvá teszi az ablakot.

#### `main(String[] args)`

Ez a metódus az alkalmazás belépési pontja. Létrehoz egy `MultiPlayerWindow` objektumot, amely inicializálja és megjeleníti a többjátékos ablakot.

#### `setConnected(boolean connected)`

Ez a metódus beállítja a `isConnected` változót a kapott értékre, majd frissíti az állapotjelzőt a `updateConnectedIndicator()` metódus segítségével.

#### `updateConnectedIndicator()`

Ez a privát segédmetódus frissíti az állapotjelzőt a `isConnected` változó alapján. Ha a `isConnected` értéke igaz, akkor a háttérszín zöld lesz, egyébként piros. Beállítja az állapotjelzőt átlátszóvá, hogy megjelenjen a háttérszín változása.


## Difficulty Window

### Metódusok:

#### `DifficultyWindow()`

Az osztály konstruktora inicializálja a nehézségi ablakot. Beállítja az ablak címét és a méretet. Létrehoz egy `JPanel` objektumot, amely a nehézségi ablak tartalmát fogja tartalmazni. Beállítja a panel elrendezését `GridLayout`-ra, amely egy oszlopos elrendezést használ. Beállítja a háttérszínt, a margókat és a belső margókat.

Létrehozza a "Chill" gombot (`chillButton`) és beállítja a méretét, betűtípusát és eseménykezelőt. Az eseménykezelő arra reagál, hogyha a gombot megnyomják, és beállítja a `diffnum` változót 1-re, majd meghívja a `Main.setDifficulty(diffnum)` metódust.

Létrehozza az "Eco" gombot (`ecoButton`) és beállítja a méretét, betűtípusát és eseménykezelőt. Az eseménykezelő arra reagál, hogyha a gombot megnyomják, és beállítja a `diffnum` változót 2-re, majd meghívja a `Main.setDifficulty(diffnum)` metódust.

Létrehozza a "Ludicrous" gombot (`ludicrousButton`) és beállítja a méretét, betűtípusát és eseménykezelőt. Az eseménykezelő arra reagál, hogyha a gombot megnyomják, és beállítja a `diffnum` változót 3-ra, majd meghívja a `Main.setDifficulty(diffnum)` metódust.

Hozzáadja a gombokat a panelhez, majd beállítja a panelt az ablak tartalmára. Beállítja az ablak méretét és helyzetét, majd láthatóvá teszi az ablakot.

#### `main(String[] args)`

Ez a metódus az alkalmazás belépési pontja. Létrehoz egy `DifficultyWindow` objektumot, amely inicializálja és megjeleníti a nehézségi ablakot.

