public class Motorcycle implements Vehicle, MotorVehicle {
    private String make;
    private String model;
    private int year;
    private int numWheels;
    private String motorcycleType;

    public Motorcycle(String make, String model, int year) {
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
    public void setNumWheels(int wheels) { this.numWheels = wheels; }
    @Override
    public int getNumWheels() { return numWheels; }
    @Override
    public void setMotorcycleType(String type) { this.motorcycleType = type; }
    @Override
    public String getMotorcycleType() { return motorcycleType; }
}