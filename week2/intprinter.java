public class intprinter <T> {
    
    private T variableName;

    public intprinter(T variableName) {
        this.variableName = variableName;
    }

    public void printNumber() {
        System.out.println(variableName);
    }
}
