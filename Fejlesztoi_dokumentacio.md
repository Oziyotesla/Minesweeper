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






## CommunicationHandler

![commHandler.png](..%2F..%2FOneDrive%2FPictures%2FcommHandler.png)

A feladata, hogy segítsen vezérelni minden kommunikációval kapcsolatos eljárást.
Ezt az osztályt kell példányosítani és ennek a függvényeivel lehet meghívni a játék számára szolgáltatott funkciókat.
```java
public boolean joinGame(String ip)
```
Paraméterként kap egy string-et, mely azt az IP címet tartalmazza amelyre csatlakozni szeretnénk. A Cél egy TCP kapcsolat kialakítása.
Ennek megfelelően, kliensként létrehoz egy socket-et, elmenti a hozzá tartozó I/O stream-eket,
majd pedig küld egy egy joinRequest típusú üzenetet (Message class), várakozik a válaszra és ha sikerült a kapcsolat,
létrehoz a kliens számára egy üzenet fogadó és küldő class-t (ClientReceiveThread és ClientTransmitThread).
```java
public void hostGame()
```

Ha ez a függvény hívódik, akkor az azt jelenti, hogy Host-ként fog funkcionálni a CommunicationHandler class.
A JoinReqHandler class segítségével létrehozza a server socket-et, és várakozik arra, hogy valaki csatlakozzon hozzá.
Ezt a folyamatot a JoinReqHandler.start() fügvénnyel lehet elindítani.
JoinReqHandler-rel kapcsolatos információkat a hozzá tartozó leírásánál részletezem.
```java
public void stopAllCommunication()
```

Meghívásakor megszünteti az eddig létrehozott kommunikációs kapcsolatot,
legyen az kliens vagy host, és ezt üzenetként jelzi is a másik félnek.

```java
public void sendStartGame(Object data)
```

Host esetén, készít egy üzenetet a kapott paraméter alapján, ami a generált aknamezőt tartalmazza és továbbítja azt a kliensnek.

```java
public void sendClickData(Object data)
```

Készít egy üzenetet a kapott paraméter alapján, ami a kliens vagy a host aknamezején történt kattintással kapcsolatos információkat tartalmazza,
és továbbítja az a másik félnek.

```java
public void sendTimeData(Object data)
```

Host esetén, készít egy üzenetet a kapott paraméter alapján, ami a játékidőt tartalmazza,
és továbbítja az a másik félnek.

```java
public boolean checkIfConnectionActive()
```

CommunicationHandler egy változóját vizsgálja, ami ha nem null értékű, akkor az azt jelenti, hogy a host-hoz csatlakoztak, létrejött egy kapcsolat.

## Message

A segítségével készítjük el az üzeneteket, melyek nem csupán adattal rendelkeznek, hanem meg is lehet határozni az üzenet típusát.
- `joinRequest`
- `joinAccepted`
- `stopCommunication`
- `startingMinefieldData`
- `clickData`
- `timeData`


## ClientReceiveThread

A joinGame() hívása során jön létre ez a thread. A class feladata, hogy fogadja a szervertől kapott üzeneteket.

```java
public ClientReceiveThread(ObjectInputStream objectInputStream)
```

ObjectInputStream-et kapja paraméterként, melyet eltárol és elindítja a szál futását.

```java
public void run()
```

A szál futása során az objectInputStream-ből kiolvassa a kapott Message class típusú üzeneteket
és a tartalmának megfelelően feldolgozza azokat. Ezután folytatódik a szál futása és további üzenetekre vár.

```java
public void stopReceive()
```

Szükség esetén le lehet állítani a szál futását.

## ClientTransmitThread

A joinGame() hívása során jön létre ez a thread. A kliens nem csak fogadja az üzeneteket, hanem küldeni is szeretne, amit a ClientTransmitThread-del kivitelezek.

```java
public ClientTransmitThread(ObjectOutputStream objectOutputStream)
```

Paraméterként megkapja ObjectOutputStream-et. Létrehoz egy láncolt lista típusú változót, mely a küldendő üzeneteket tárolja.
Továbbá elindítja a szál futását.

```java
public void run()
```

A szál futása során folyamatosan ellenőrzi, hogy van e küldendő üzenet, amennyiben van akkor azt kiveszi a listából, és elküldi, majd pedig
újra megnézi, hogy van e még üzenet a listában.

```java
public void sendMessage(Message msg)
```

A paraméterként kapott üzenetet hozzáadja a láncolt listához, ezáltal ő az a funkció akit meg kell hívni, hogy üzenetet lehessen küldeni.

```java
public void stopTransmit()
```

Szükség esetén le lehet állítani a szál futását.


## JoinReqHandler

Mikor a hostGame függvény meghívódik, egy hosszasabb folyamat indul be, melynek első részét ez a class valósítja meg.
Ez egy thread, ami a konstruktor segítségével azonnal futni kezd.

```java
public void run()
```

Létrehoz egy szerver socket-et, majd várakozunk a kliens csatlakozására. Ha létrejött a kapcsolat, akkor CreateConnection
class segítségével létre hozom a host kommunikációját támogató osztályokat. Ezután már nem lehet csatlakozni a szerverhez.

```java
public void stopListening()
```
Ez a függvény zárja le a szerver socket-et.

## CreateConnection

```java
public CreateConnection(Socket serverSocket, CommunicationHandler communicationHandler)
```

Ennek csupán egy konstruktora van. Ő kezeli le a kapcsolat létrejöttét szerver oldalon. Kizárólag a JoinReqHandler hívja meg.
Ha a szerver input stream-jén érkezö üzenet, joinRequest típusú, akkor létrehoz egy Connection típusú változót.

## Connection

```java
public Connection(Socket serverSocket,
ObjectInputStream objectInputStream,
ObjectOutputStream objectOutputStream
)
```

Miután sikerült a csatlakozás, és meggyőződtünk róla, hogy valóban a játékhoz szeretne csatlakozni a kliens,
a CommunicationHandler activeConnection változóját felülírjuk ennek a class-nak a példányával.
Ez az osztály tárolja el a szerver socket-et, illetve létrehozza és szintén tárolja a szerverhez tartozó kommunikációt segítő osztályokat.

```java
public void closeConnection()
```

A class által felülírt változókat törli és zárja a server socket-et.

## ServerReceiveThread

```java
public ServerReceiveThread(ObjectInputStream objectInputStream, Connection connection)
```

Feladata, hogy fogadja a klienstől kapott üzeneteket. A konstruktora elmenti a paraméterül kapott InputStream objektumot,
és engedélyezi a szál működését.

```java
public void run()
```

Futása során, folyamatosan leolvassa az Input stream-et. Amennyiben olyan üzenet érkezett amire számítottunk,
akkor azt lekezeli, egyébként ignorálja.

```java
public void stopServer()
```
```java
public void startServer()
```

Ez a két függvény tudja tiltani és engedélyezni az Input stream leolvasását, a szál futása közben.

## ServerTransmitThread

```java
public ServerTransmitThread(ObjectOutputStream objectOutputStream, Connection connection)
```

Eredetileg thread lett volna, mint a ClientReceiveThread,
de rájöttem, hogy erre semmi szükség, így végül csak egyfajta interface-ként funkcionál a CommunicationHandler és
az Output stream-nek a writeObject függvénye között.

A következő függvényekkel lehet a megfelelő üzeneteket továbbítani:

```java
public void sendStartGame(Message msg)
```

```java
public void sendClickData(Message msg)
```

```java
public void sendTimeData(Message msg)
```
```java
public void sendStopCommunication(Message msg)
```


