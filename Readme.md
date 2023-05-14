## Csapattagok:

```Kálai Árpád```
```Plöchl Péter```
```Mayer Levente```

## Játék rövid leírása

Az aknakereső egy olyan játék, amelyben a játékosnak az a feladata, hogy megtalálja az összes aknát,
amelyek rejtőznek a játéktéren elhelyezett mezők alatt. A játék általában egy négyzet alakú rácson játszódik, 
amelyben a mezők lehetnek fedetlenek vagy fedett állapotban. Az aknákat véletlenszerűen helyezik el a mezők alatt.

A játékos egymás után elkezdi felfedni a mezőket. Két játékos esetén egyszer az egyik, egyszer pedig a másik fed fel egy-egy mezőt.

## Játékmenet

1.  A játékmező egy **N**x**M** méretű négyzetes rács, mely tartalmaz **B** db aknát (ahol **N**, **M** és **B** konfigurálható).
    
2.  Az aknák elhelyezkedése véletlenszerű.
    
3.  Az első kattintással kezdődik a játék és ezzel együtt a számláló.
    
4.  Ha a kattintott mező tartalmaz egy aknát, a játék véget ér, a játékos veszített.
    
5.  Ha a kattintott mező nem tartalmaz aknát, akkor megjelenik a mezőn lévő szám, amely jelzi, hogy hány szomszédos mező tartalmaz aknát.
	
6.  Egy mező felfedése után a másik játékosra átkerül a lépés
    
7.  Ha az összes mező fel lett fedve vagy megjelölve, amely nem tartalmaz aknát, a játék véget ér, a játékosok nyertek.


## Játékfelület

1.  A játékfelületen látható az **N**x**M** méretű négyzetes rács, amelyen a mezők találhatók.
    
2.  Minden mező állapota lehet:
    
    -   fedett
        
    -   felfedett
        
    -   jelölt
        
3.  A felfedett mezőkön számok jelennek meg, amelyek jelzik, hogy hány akna található a szomszédos mezőkön. Ha a szám nulla lenne akkor a mezőn nem jelenik meg szám.
    
4.  A jelölt mezőkre egy zászló kerül, hogy jelezze, hogy ott akna található.


## Játékbeállítások

A játékos kiválaszthatja a játék nehézségi szintjét (könnyű, közepes, nehéz), ami meghatározza a rács méretét és az aknák számát.


## Játékvezérlés

-   A játékot egérrel lehet játszani.
    
-   A ball egérgombbal lehet felfedni a mezőket, a zászlóval megjelölt mezők nem kerülnek felfedésre.

-   A jobb egérgomb lenyomásával lehet megjelölni a mezőket zászlóval.
    
-   A játékos az ```Új játék``` gombra kattintva új játékot indíthat el.

-   A játékot megszakítani az ```exit``` gomb megnyomásával lehet.

## Játék elkezdése

A játékosnak lehetősége van ```Single player``` és ```Two player``` játékmódot, illetve nehézségi szintet választani.
Kétjátékos esetben szükség van egy játékszerverre, amit létre lehet hozni, vagy keresni már meglévő szervert és csatlakozni hozzá.

### Játék létrehozása
```Create Room``` gomb menyomásával létrehozhatunk egy játékszervert és megjelenik a hozzá tartozó IP cím.
Ha csatlakozott valaki a játékhoz, akkor megjelenik egy zöld gomb, amire kattinva elindíthatja a Host a játékot.

### Játékhoz csatlakozás

A host által létrehozott játékhoz tartozó IP cím beírásával, majd a ```Connect``` gomb megnyomása után megpróbál csatlakozni.
Ha sikerült csatlakozni, kapunk egy visszaigazolást annak sikerességéről és várunk míg a Host elindítja a játékot.


## Tesztesetek

1. Kapcsolat megszakadás vizsgálat

2. Bombák maximális számának ellenörzése

3. Felrombantott bomba észlelése

4. Ismételt lépés megakadályozásának ellenörzése

5. Játék végéne ellenörzése
