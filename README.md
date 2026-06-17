# Symulacja-ForestFire

Projekt symulacji w Javie, obejmujący losowo generowaną planszę składającą się z różnych typów komórek, po której rozprzestrzenia się pożar. Symulacja zawiera również oddział strażacki, składający się z dwóch typów agentów (Firefighter, Helicopter), których celem jest ugaszenie ognia, zanim ten dotrze do miasta.

---

## Szybki start
- Java 21
- JavaFX
- IDE (np. IntelliJ IDEA)

---
## Uruchomienie
Symulacja jest uruchomiona po przez klasę:
Main.java

## Przykładowe parametry uruchomienia symulacji
new Simulation(200, 200, 0.65, 35, 20 ,5, 0.2);

## Działanie
- tworzona jest plansza o zadanej wielkości,
- prawa część wypełniana jest komórkami typu CityCell,
- pozostały obszar jest losowym ułożeniem ForestCell oraz EmptyCell,
- na lewej krawędzi planszy pojawia się ogień
- ogień rozprzestrzenia się po planszy
- w miejscu komórek CityCell pojawiają się agenci


## Agenci:

Firefighter
- gaszą ogień na małym obszarze
- działają dłużej

Helicopter
- zrzuca wodę na dużym obszarze
- działa jednorazowo

Obydwa typy
- posiadają określony zapas wody
- po jego wyczerpaniu wracają do miasta

## Warunek końca symulacji
Symulacja kończy się gdy ogień dotarze do komórek typu CityCell

---

