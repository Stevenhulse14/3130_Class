package day1.genericLab;

public class Box<T> {

    private T poke;
    
    public Box(T poke){
        this.poke = poke;
    }
    public T get(){
        System.out.println(this.poke);
        return this.poke;
    }

    public void set(T poke){
        this.poke = poke;
        System.out.println("Setting new pokemon !");
    }
}
