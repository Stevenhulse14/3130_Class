# Lab: Pokémon Generics (Step-by-Step)

You’re going to build a small **Pokémon Storage System** to learn Java generics in a smooth ramp:

1. start with a normal class (no generics)
2. refactor into a generic class (`<T>`)
3. add two generic types (`<K, V>`)
4. add a bounded generic (`<T extends Pokemon>`)
5. write a generic method
6. optional challenge: wildcards (`? extends Pokemon`)

---

## Learning Goals

By the end of this lab, you should be able to:

- Explain what a generic type parameter like `T` means
- Create and use a generic class like `Box<T>`
- Create and use a class with two type parameters like `Pair<K, V>`
- Use bounds like `T extends Pokemon` to restrict types
- Write a generic method like `<T> void swap(...)`
- (Optional) Understand why wildcards exist for lists

---

## Project Setup

### Files you start with

Create these 2 files first.

#### `Pokemon.java`

```java
public class Pokemon {
    private String name;
    private String type;

    public Pokemon(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() { return name; }
    public String getType() { return type; }

    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}
```

#### `Main.java`

```java
public class Main {
    public static void main(String[] args) {
        Pokemon pikachu = new Pokemon("Pikachu", "Electric");
        Pokemon charmander = new Pokemon("Charmander", "Fire");

        System.out.println(pikachu);
        System.out.println(charmander);
    }
}
```

---

## Step 1 — The “bad” version (no generics)

**Goal**
Create a class that stores only a Pokemon.

**Task**
Create `PokeBox.java`.

**Requirements**

- private field: `Pokemon value`
- constructor: takes a `Pokemon`
- method: `Pokemon get()`
- method: `void set(Pokemon newValue)`

✅ **Test (add to Main)**

- Make a `PokeBox` with Pikachu
- Print `box.get()`
- Set it to Charmander
- Print again

---

## Step 2 — Refactor to generics (Box<T>)

**Goal**
Make one reusable class that can store any type.

**Task**
Create `Box.java`.

**Requirements**

- class header: `public class Box<T>`
- field: `private T value;`
- constructor: `Box(T value)`
- `T get()`
- `void set(T newValue)`

✅ **Test**

```java
Box<Pokemon> pokemonBox = new Box<>(pikachu);
Box<String> itemBox = new Box<>("Oran Berry");
Box<Integer> levelBox = new Box<>(25);
```

---

## Step 3 — Two type parameters (Pair<K, V>)

**Goal**
Store two related values, like a Pokémon and its held item.

**Task**
Create `Pair.java`.

**Requirements**

- class header: `public class Pair<K, V>`
- fields: `private K first; private V second;`
- constructor sets both
- getters: `getFirst()`, `getSecond()`
- `toString()` returns: `"first -> second"`

✅ **Test**

```java
Pair<Pokemon, String> heldItem = new Pair<>(pikachu, "Oran Berry");
System.out.println(heldItem);
```

---

## Step 4 — Bounded generics (`<T extends Pokemon>`)

**Goal**
Create a box that only accepts Pokemon or subclasses.

**Task**
Create `PokemonOnlyBox.java`.

**Requirements**

- header: `public class PokemonOnlyBox<T extends Pokemon>`
- store `T value`
- `T get()`, `void set(T newValue)`
- method: `String getType()` that returns `value.getType()`

✅ **Test**

```java
PokemonOnlyBox<Pokemon> b1 = new PokemonOnlyBox<>(pikachu);
System.out.println(b1.getType());
```

---

## Step 5 — Create a subclass (BattlePokemon) + show bounds working

**Goal**
Prove `T extends Pokemon` works with subclasses too.

**Task**
Create `BattlePokemon.java`.

**Requirements**

- `extends Pokemon`
- adds `int hp`
- constructor includes `hp`
- getter for `hp`
- override `toString()` to include HP

✅ **Test**

```java
BattlePokemon squirtle = new BattlePokemon("Squirtle", "Water", 60);
PokemonOnlyBox<BattlePokemon> battleBox = new PokemonOnlyBox<>(squirtle);
System.out.println(battleBox.get());
```

---

## Step 6 — Generic method: swap two boxes

**Goal**
Write a method that swaps the contents of two boxes.

**Task**
Inside `Main.java`, write a method:

**Requirements**

```java
public static <T> void swap(Box<T> a, Box<T> b)
```

Swap using `get()` and `set()`.

✅ **Test**

```java
Box<String> a = new Box<>("Potion");
Box<String> b = new Box<>("Rare Candy");
System.out.println("Before: a=" + a.get() + ", b=" + b.get());
swap(a, b);
System.out.println("After:  a=" + a.get() + ", b=" + b.get());
```

---

## Step 7 (Challenge) — Wildcards + Team Printer

Only do this if you’re comfortable.

**Goal**
Print any list of Pokémon (including lists of subclasses).

**Task**
Write:

```java
public static void printTeam(java.util.List<? extends Pokemon> team)
```

✅ **Test**

- Works on `List<Pokemon>`
- Works on `List<BattlePokemon>`

---

## Run Instructions

Compile and run:

```bash
javac *.java
java Main
```

---

## Submission

Submit your GitHub repo link with:

- all `.java` files
- this `README.md`

Good luck — and don’t rush. Each step is meant to feel small.
