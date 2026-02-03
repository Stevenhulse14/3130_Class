# Pokémon Generics Lab — Solution Key

This file contains one correct implementation for each step.

---

## Step 1 — `PokeBox.java`

```java
public class PokeBox {
    private Pokemon value;

    public PokeBox(Pokemon value) {
        this.value = value;
    }

    public Pokemon get() {
        return value;
    }

    public void set(Pokemon newValue) {
        this.value = newValue;
    }
}
```

---

## Step 2 — `Box.java`

```java
public class Box<T> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void set(T newValue) {
        this.value = newValue;
    }
}
```

---

## Step 3 — `Pair.java`

```java
public class Pair<K, V> {
    private K first;
    private V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() { return first; }
    public V getSecond() { return second; }

    @Override
    public String toString() {
        return first + " -> " + second;
    }
}
```

---

## Step 4 — `PokemonOnlyBox.java`

```java
public class PokemonOnlyBox<T extends Pokemon> {
    private T value;

    public PokemonOnlyBox(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void set(T newValue) {
        this.value = newValue;
    }

    public String getType() {
        return value.getType();
    }
}
```

---

## Step 5 — `BattlePokemon.java`

```java
public class BattlePokemon extends Pokemon {
    private int hp;

    public BattlePokemon(String name, String type, int hp) {
        super(name, type);
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public String toString() {
        return super.toString() + " HP=" + hp;
    }
}
```

---

## Step 6 + Step 7 — `Main.java` Example Tests

```java
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static <T> void swap(Box<T> a, Box<T> b) {
        T temp = a.get();
        a.set(b.get());
        b.set(temp);
    }

    public static void printTeam(List<? extends Pokemon> team) {
        for (Pokemon p : team) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        Pokemon pikachu = new Pokemon("Pikachu", "Electric");
        Pokemon charmander = new Pokemon("Charmander", "Fire");

        // Step 1
        PokeBox pb = new PokeBox(pikachu);
        System.out.println("Step 1: " + pb.get());
        pb.set(charmander);
        System.out.println("Step 1 after set: " + pb.get());
        System.out.println();

        // Step 2
        Box<Pokemon> pokemonBox = new Box<>(pikachu);
        Box<String> itemBox = new Box<>("Oran Berry");
        Box<Integer> levelBox = new Box<>(25);

        System.out.println("Step 2: " + pokemonBox.get());
        System.out.println("Step 2: " + itemBox.get());
        System.out.println("Step 2: " + levelBox.get());
        System.out.println();

        // Step 3
        Pair<Pokemon, String> heldItem = new Pair<>(pikachu, "Oran Berry");
        System.out.println("Step 3: " + heldItem);
        System.out.println();

        // Step 4
        PokemonOnlyBox<Pokemon> onlyPokes = new PokemonOnlyBox<>(charmander);
        System.out.println("Step 4 type: " + onlyPokes.getType());
        System.out.println();

        // Step 5
        BattlePokemon squirtle = new BattlePokemon("Squirtle", "Water", 60);
        PokemonOnlyBox<BattlePokemon> battleBox = new PokemonOnlyBox<>(squirtle);
        System.out.println("Step 5: " + battleBox.get());
        System.out.println();

        // Step 6
        Box<String> a = new Box<>("Potion");
        Box<String> b = new Box<>("Rare Candy");
        System.out.println("Before swap: a=" + a.get() + ", b=" + b.get());
        swap(a, b);
        System.out.println("After swap:  a=" + a.get() + ", b=" + b.get());
        System.out.println();

        // Step 7 (challenge)
        List<Pokemon> team1 = new ArrayList<>();
        team1.add(pikachu);
        team1.add(charmander);

        List<BattlePokemon> team2 = new ArrayList<>();
        team2.add(new BattlePokemon("Bulbasaur", "Grass", 50));
        team2.add(new BattlePokemon("Charmander", "Fire", 55));

        System.out.println("Team 1:");
        printTeam(team1);
        System.out.println();

        System.out.println("Team 2:");
        printTeam(team2);
    }
}
```
