package model;

public class Car {
    public String make;
    public String model;
    public int year;

    public Car(){}

    public Car(String make, String model, int year){
        this.make = make;
        this.model = model;
        this.year = year;
    }
    @Override
    public String toString() {
        return "Year: " + year + "\n" +
                "Make: " + make + "\n" +
                "Model: " + model + "\n\n";
    }
}
