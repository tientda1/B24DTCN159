/*
Ho ten: Phung Quang Tien
MSSV: B24DTCN159
Lop: D24TXCN07-B
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

abstract class Vehicle {
    protected String id, brand;
    protected double price;

    public Vehicle() {}
    public Vehicle(String id, String brand, double price) {
        this.id = id;
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() { return brand; }
    public double getPrice() { return price; }

    public void input(Scanner sc) {
        System.out.print("Mã xe: "); id = sc.nextLine();
        System.out.print("Hãng: "); brand = sc.nextLine();
        System.out.print("Giá: "); price = sc.nextDouble();
        sc.nextLine();
    }

    public abstract void display();
}

class Car extends Vehicle {
    private int seats;

    public Car() {}
    public Car(String id, String brand, int seats, double price) {
        super(id, brand, price);
        this.seats = seats;
    }

    @Override
    public void input(Scanner sc) {
        super.input(sc);
        System.out.print("Số ghế: "); seats = sc.nextInt();
        sc.nextLine();
    }

    @Override
    public void display() {
        System.out.printf("Ô tô - Mã: %s, Hãng: %s, Ghế: %d, Giá: %.0f%n", id, brand, seats, price);
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle() {}
    public Motorcycle(String id, String brand, double price) {
        super(id, brand, price);
    }

    @Override
    public void display() {
        System.out.printf("Xe máy - Mã: %s, Hãng: %s, Giá: %.0f%n", id, brand, price);
    }
}

public class PhungQuangTien_B24DTCN159 {
    static ArrayList<Vehicle> vehicles = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n1. Nhập ô tô\n2. Nhập xe máy\n3. Xem danh sách\n4. Tìm ô tô\n5. Sắp xếp giá\n6. Thoát");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: addCar(); break;
                case 2: addMotorcycle(); break;
                case 3: showVehicles(); break;
                case 4: findCar(); break;
                case 5: sortVehicles(); break;
                case 6: System.out.println("Tạm biệt!"); break;
                default: System.out.println("Chọn sai!");
            }
        } while (choice != 6);
    }

    static void addCar() {
        Car car = new Car();
        car.input(sc);
        vehicles.add(car);
        System.out.println("Thêm ô tô OK!");
    }

    static void addMotorcycle() {
        Motorcycle bike = new Motorcycle();
        bike.input(sc);
        vehicles.add(bike);
        System.out.println("Thêm xe máy OK!");
    }

    static void showVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("Chưa có xe!");
            return;
        }
        for (Vehicle v : vehicles) v.display();
    }

    static void findCar() {
        System.out.print("Nhập hãng: ");
        String brand = sc.nextLine();
        boolean found = false;
        for (Vehicle v : vehicles) {
            if (v instanceof Car && v.getBrand().equalsIgnoreCase(brand)) {
                v.display();
                found = true;
            }
        }
        if (!found) System.out.println("Không tìm thấy!");
    }

    static void sortVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("Chưa có xe!");
            return;
        }
        Collections.sort(vehicles, (v1, v2) -> Double.compare(v1.getPrice(), v2.getPrice()));
        System.out.println("Danh sách xe đã sắp xếp:");
        showVehicles();
    }
}