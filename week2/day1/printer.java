package day1;
public class printer <T> {
    
    private T variableName;

    public printer(T variableName) {
        this.variableName = variableName;
    }

    public void printNumber() {
        System.out.println(variableName);
    }
}
