package day1.genericLab;

public class pokemon {
    public String name;
    public String type;

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
