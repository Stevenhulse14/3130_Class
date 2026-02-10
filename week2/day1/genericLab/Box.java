package day1.genericLab;

public class Box <T> {

    private T value;
    
    public Box(T value){
        this.value = value;
    }
    public T get(){
        System.out.println(this.value);
        return this.value;
    }

    public void set(T newValue){
        this.value = value;
        System.out.println("Setting new pokemon !");
    }
}
