package day1.genericLab;

public class Pair<K, V> {
    private K first;
    private V second;

    Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst(){
        return this.first;
    }

    public V getSecond(){
        return this.second;
    }

    public String toString() {
        return "first: " + first + ", second: " + second;  
    }

}
