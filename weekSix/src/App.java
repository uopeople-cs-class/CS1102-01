import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Vehicle> vehicles = new ArrayList<>();
        
        while (true) {
            System.out.println("\n--- Vehicle Information System ---");
            System.out.println("1. Add a Car");
            System.out.println("2. Add a Motorcycle");
            System.out.println("3. Add a Truck");
            System.out.println("4. Display all vehicles");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid input
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            if (choice == 5) {
                System.out.println("Exiting...");
                break;
            }
            
            if (choice >= 1 && choice <= 3) {
                System.out.print("Enter make: ");
                String make = scanner.nextLine();
                System.out.print("Enter model: ");
                String model = scanner.nextLine();
                System.out.print("Enter year: ");
                int year = 0;
                if (scanner.hasNextInt()) {
                    year = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println("Invalid year. Vehicle not added.");
                    scanner.next();
                    continue;
                }
                
                if (choice == 1) {
                    Car car = new Car(make, model, year);
                    System.out.print("Enter number of doors: ");
                    if (scanner.hasNextInt()) {
                        car.setNumDoors(scanner.nextInt());
                        scanner.nextLine();
                    } else {
                        scanner.next();
                    }
                    System.out.print("Enter fuel type (petrol/diesel/electric): ");
                    car.setFuelType(scanner.nextLine());
                    vehicles.add(car);
                    System.out.println("Car added successfully!");
                } else if (choice == 2) {
                    Motorcycle moto = new Motorcycle(make, model, year);
                    System.out.print("Enter number of wheels: ");
                    if (scanner.hasNextInt()) {
                        moto.setNumWheels(scanner.nextInt());
                        scanner.nextLine();
                    } else {
                        scanner.next();
                    }
                    System.out.print("Enter motorcycle type (sport/cruiser/off-road): ");
                    moto.setMotorcycleType(scanner.nextLine());
                    vehicles.add(moto);
                    System.out.println("Motorcycle added successfully!");
                } else if (choice == 3) {
                    Truck truck = new Truck(make, model, year);
                    System.out.print("Enter cargo capacity (in tons): ");
                    if (scanner.hasNextDouble()) {
                        truck.setCargoCapacity(scanner.nextDouble());
                        scanner.nextLine();
                    } else {
                        scanner.next();
                    }
                    System.out.print("Enter transmission type (manual/automatic): ");
                    truck.setTransmissionType(scanner.nextLine());
                    vehicles.add(truck);
                    System.out.println("Truck added successfully!");
                }
            } else if (choice == 4) {
                if (vehicles.isEmpty()) {
                    System.out.println("No vehicles to display.");
                    continue;
                }
                System.out.println("\n--- All Vehicles ---");
                for (Vehicle v : vehicles) {
                    System.out.printf("Vehicle: %d %s %s\n", v.getYear(), v.getMake(), v.getModel());
                    if (v instanceof CarVehicle) {
                        CarVehicle c = (CarVehicle) v;
                        System.out.printf("  Type: Car | Doors: %d | Fuel: %s\n", c.getNumDoors(), c.getFuelType());
                    } else if (v instanceof MotorVehicle) {
                        MotorVehicle m = (MotorVehicle) v;
                        System.out.printf("  Type: Motorcycle | Wheels: %d | Style: %s\n", m.getNumWheels(), m.getMotorcycleType());
                    } else if (v instanceof TruckVehicle) {
                        TruckVehicle t = (TruckVehicle) v;
                        System.out.printf("  Type: Truck | Capacity: %.2f tons | Transmission: %s\n", t.getCargoCapacity(), t.getTransmissionType());
                    }
                    System.out.println("-------------------------");
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
}
