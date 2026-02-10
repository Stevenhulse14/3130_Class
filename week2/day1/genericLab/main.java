package day1.genericLab;

public class main {
    public static void main(String [] args){

        pokemon pikachu = new pokemon("Pikachu", "Electric");
        pokemon charmander = new pokemon("Charmander", "Fire");
        
        System.out.println(pikachu);
        System.out.println(charmander);

        Pair<pokemon, String> heldItem = new Pair<> (pikachu, "Oran Berry");
        System.out.println(heldItem);

        Box<pokemon> newPokemon = new Box<>(pikachu);
        System.out.println(newPokemon);

        pokemonOnlyBox<pokemon> b1 = new pokemonOnlyBox<>(pikachu);
        System.out.println(b1.getType());
        
    }
}
