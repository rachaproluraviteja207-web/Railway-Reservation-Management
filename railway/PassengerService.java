package railway;
import java.util.ArrayList;
import java.util.Scanner;

public class PassengerService {
	
	    static Scanner sc = RailwayMain.sc;

	    static ArrayList<Passenger> passengerList = 
	            RailwayMain.passengerList;


	    // ================= PASSENGER MENU =================

	    static void passengerMenu() {

	        int choice;

	        do {

	            System.out.println("\n======================================");
	            System.out.println("       PASSENGER MANAGEMENT");
	            System.out.println("======================================");

	            System.out.println("1. Passenger Registration");
	            System.out.println("2. Update Passenger");
	            System.out.println("3. Search Passenger");
	            System.out.println("4. View All Passengers");
	            System.out.println("5. Delete Passenger");
	            System.out.println("6. Back");

	            System.out.print("Enter your choice: ");

	            choice = RailwayMain.readInt();

	            switch (choice) {

	            case 1:
	                registerPassenger();
	                break;

	            case 2:
	                updatePassenger();
	                break;

	            case 3:
	                searchPassenger();
	                break;

	            case 4:
	                viewAllPassengers();
	                break;

	            case 5:
	                deletePassenger();
	                break;

	            case 6:
	                System.out.println("Returning...");
	                break;

	            default:
	                System.out.println("Invalid choice!");
	            }

	        } while (choice != 6);
	    }


	    // ================= REGISTER =================

	    static void registerPassenger() {

	        System.out.println("\n===== PASSENGER REGISTRATION =====");

	        String name = RailwayMain.readValidName();

	        int age = RailwayMain.readValidAge();

	        String gender = RailwayMain.readValidGender();

	        String contact = RailwayMain.readValidContact();

	        String email = RailwayMain.readValidEmail();

	        String address = RailwayMain.readValidAddress();


	        int id = RailwayMain.nextPassengerId;


	        Passenger p =
	                new Passenger(
	                        id,
	                        name,
	                        age,
	                        gender,
	                        contact,
	                        email,
	                        address
	                );


	        passengerList.add(p);

	        RailwayMain.nextPassengerId++;

	        FileDB.savePassenger(p);


	        System.out.println("\nPassenger registered successfully!");
	        System.out.println("Passenger ID : " + p.id);
	    }


	    // ================= UPDATE =================

	    static void updatePassenger() {

	        System.out.print("\nEnter Passenger ID: ");

	        int id = RailwayMain.readInt();


	        Passenger p =
	                RailwayMain.findPassengerById(id);


	        if (p == null) {

	            System.out.println("Passenger not found.");
	            return;
	        }


	        System.out.println("\nCurrent Details:");
	        p.show();


	        System.out.println("\nEnter New Details:");

	        p.name = RailwayMain.readValidName();

	        p.age = RailwayMain.readValidAge();

	        p.gender = RailwayMain.readValidGender();

	        p.contact = RailwayMain.readValidContact();

	        p.email = RailwayMain.readValidEmail();

	        p.address = RailwayMain.readValidAddress();


	        FileDB.saveAllPassengers();


	        System.out.println("\nPassenger updated successfully!");
	    }


	    // ================= SEARCH =================

	    static void searchPassenger() {

	        System.out.print("\nEnter Passenger ID: ");

	        int id = RailwayMain.readInt();


	        Passenger p =
	                RailwayMain.findPassengerById(id);


	        if (p == null) {

	            System.out.println("Passenger not found.");

	        } else {

	            System.out.println("\nPassenger Details:");
	            p.show();
	        }
	    }


	    // ================= VIEW ALL =================

	    static void viewAllPassengers() {

	        System.out.println("\n===== ALL PASSENGERS =====");


	        if (passengerList.isEmpty()) {

	            System.out.println("No passengers found.");
	            return;
	        }


	        for (int i = 0; i < passengerList.size(); i++) {

	            Passenger p =
	                    passengerList.get(i);

	            p.show();

	            System.out.println("--------------------------------------");
	        }
	    }


	    // ================= DELETE =================

	    static void deletePassenger() {

	        System.out.print("\nEnter Passenger ID: ");

	        int id = RailwayMain.readInt();


	        Passenger p =
	                RailwayMain.findPassengerById(id);


	        if (p == null) {

	            System.out.println("Passenger not found.");
	            return;
	        }


	        System.out.println("\nPassenger Details:");
	        p.show();


	        System.out.print(
	                "\nAre you sure you want to delete? (yes/no): ");

	        String confirm = sc.nextLine();


	        if (!confirm.equalsIgnoreCase("yes")) {

	            System.out.println("Deletion cancelled.");
	            return;
	        }


	        passengerList.remove(p);

	        FileDB.saveAllPassengers();


	        System.out.println("\nPassenger deleted successfully!");
	    }
	}
