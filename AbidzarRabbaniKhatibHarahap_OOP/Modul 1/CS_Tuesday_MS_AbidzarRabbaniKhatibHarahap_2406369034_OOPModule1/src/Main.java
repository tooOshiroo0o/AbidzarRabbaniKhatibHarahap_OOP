
public class Main {
    public static void main(String[] args) {
    Vehicle VehicleSupraBapak = new Vehicle("Honda Supra", 1998, VehicleType.Motorcycle, 3000);
    Vehicle VehicleKalcer = new Vehicle("VW Beetle", 1998, VehicleType.Car, 200000);
    Vehicle VehicleGuede = new Vehicle("Isuzu Giga", 2011, VehicleType.Truck, 300000);

    customer customer1 = new customer("Joko", VehicleSupraBapak);
    customer customer2 = new customer("Sahroni", VehicleKalcer);
    customer customer3 = new customer("Teddy", VehicleGuede);

    customer1.ShowDetail();
    customer2.ShowDetail();
    customer3.ShowDetail();
    }
}

class Vehicle {
    private String brand;
    private int year;
    private VehicleType Type;
    private float price;

    public Vehicle(String brand, int year, VehicleType type, float price) {
        this.brand = brand;
        this.year = year;
        this.Type = type;
        this.price = price;
    }

    public void ShowDetail() {
        System.out.println("Brand: " + brand);
        System.out.println("Year: " + year);
        System.out.println("Type: " + Type);
        System.out.println("Price: " + price);
    }

    public float GetPrice() {
        return price;
    }
}

class customer {
    private String firstName;
    private Vehicle vehicle;

    public customer(String firstName, Vehicle vehicle) {
        this.firstName = firstName;
        this.vehicle = vehicle;
    }

    public double getTotalPrize() {
        return vehicle.GetPrice();
    }

    public void ShowDetail() {
        System.out.println("Customer Name: " + firstName);
        vehicle.ShowDetail();
        System.out.println("Total Prize: " + getTotalPrize());
        System.out.println("-------------------------------");
    }
}

enum VehicleType {
    Car,
    Motorcycle,
    Truck
}