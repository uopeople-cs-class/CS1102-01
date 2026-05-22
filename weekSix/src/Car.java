public class Car implements Vehicle, CarVehicle {
    private String make;
    private String model;
    private int year;
    private int numDoors;
    private String fuelType;

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    @Override
    public String getMake() { return make; }
    @Override
    public String getModel() { return model; }
    @Override
    public int getYear() { return year; }

    @Override
    public void setNumDoors(int doors) { this.numDoors = doors; }
    @Override
    public int getNumDoors() { return numDoors; }
    @Override
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }
    @Override
    public String getFuelType() { return fuelType; }
}