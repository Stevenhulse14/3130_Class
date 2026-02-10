package day1.genericLab;

public class pokemonOnlyBox <T extends pokemon>{
    private T value;

    public pokemonOnlyBox (T value) {
        this.value = value;
    }

    public T get() {
        return this.value;
    }

    public void set(T newValue) {
        this.value = value;
    }

    public String getType() {
        return "value" + this.value;
    }
}
