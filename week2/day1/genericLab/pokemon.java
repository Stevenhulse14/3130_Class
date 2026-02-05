package day1.genericLab;

public class pokemon {
    private String name;
    private String type;

    public pokemon(String name, String type){
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return name;
    }
    public String getType(){
        return type;
    }
    @Override
    public String toString(){

        return name + "(" + type + ")";
    }
}
