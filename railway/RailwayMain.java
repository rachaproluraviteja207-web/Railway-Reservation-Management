package railway;


import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RailwayMain {

		    static Scanner sc = new Scanner(System.in);

		   
		    // ================= DATA STORAGE =================

		    static ArrayList<Passenger> passengerList =
		            new ArrayList<Passenger>();

		    static ArrayList<Train> trainList =
		            new ArrayList<Train>();

		    static ArrayList<Booking> bookingList =
		            new ArrayList<Booking>();
		    
		    static ArrayList<User> userList = 
		    		new ArrayList<User>();
		    
		    static int nextPassengerId = 1;
		    static int nextPNR = 1000000001;

		           
		    // ================= MAIN METHOD =================

		    public static void main(String[] args) {

		       FileDB.loadPassengers();
		       FileDB.loadTrains();
		       FileDB.loadUsers();
		       FileDB.loadBookings();
		        
		         if(trainList.size() == 0) {
		        	 addSampleTrains();
		        	
		         }
		         
		        while (true) {

		            System.out.println("\n======================================");
		            System.out.println("   RAILWAY REGISTRATION MANAGEMENT");
		            System.out.println("======================================");

		            System.out.println("1. User");
		            System.out.println("2. Agent");
		            System.out.println("3. Exit");

		            System.out.print("Enter your choice: ");

		            int choice = readInt();

		            switch (choice) {

		            case 1:
		                userMenu();
		                break;

		            case 2:
		                agentLogin();
		                break;
		            	
		            case 3:
		                System.out.println("\nThank you for using Railway Registration System!");
		                sc.close();
		                return;

		            default:
		                System.out.println("Invalid choice! Please try again.");
		            }
		        }
		    }

		    	// ================= USER MENU =================
			
			    static void userMenu() {
			
			        int choice;
			
			        do {
			
			            System.out.println("\n======================================");
			            System.out.println("                 USER MENU");
			            System.out.println("======================================");
			
			            System.out.println("1. Register");
			            System.out.println("2. Login");
			            System.out.println("3. Back");
			
			            System.out.print("\nEnter your choice: ");
			
			            choice = readInt();
			
			            switch (choice) {
			
			            case 1:
			                registerUser();
			                break;
			
			            case 2:
			                loginUser();
			                break;
			
			            case 3:
			                System.out.println("Returning to Main Menu...");
			                break;
			
			            default:
			                System.out.println("Invalid choice!");
			            }
			
			        } while (choice != 3);
			    }
			    
			 // ================= USER REGISTRATION =================

			    static void registerUser() {

			        System.out.println("\n======================================");
			        System.out.println("          USER REGISTRATION");
			        System.out.println("======================================");

			        System.out.print("Enter Full Name     : ");
			        String name = sc.nextLine();

			        int age = readValidAge();

			        System.out.print("Enter Mobile Number : ");
			        String mobile = sc.nextLine();
			        
			        String gender = readValidGender();
			        
			        String email = readValidEmail();
			         
			        String address = readValidAddress();
			        
			        System.out.print("Create Username     : ");
			        String username = sc.nextLine();

			        if (usernameExists(username)) {

			            System.out.println("\nUsername already exists!");
			            return;
			        }

			        System.out.print("Create Password     : ");
			        String password = sc.nextLine();

			        System.out.print("Confirm Password    : ");
			        String confirmPassword = sc.nextLine();

			        if (!password.equals(confirmPassword)) {

			            System.out.println("\nPassword does not match!");
			            return;
			        }

			        String userId = generateUserId();
			         
			        int passengerId = nextPassengerId++;
			        
			        // Create Passenger automatically
			        Passenger passenger = new Passenger(
			                passengerId,
			                name,
			                age,
			                gender,
			                mobile,
			                email,
			                address
			        );

			        passengerList.add(passenger);
			        FileDB.savePassenger(passenger);

			        // Create User
			        User user = new User(
			                userId,
			                name,
			                age,
			                mobile,
			                email,
			                username,
			                password,
			                passengerId
			        );

			        userList.add(user);

			        FileDB.saveUser(user);

			        

			        System.out.println("\n======================================");
			        System.out.println("       REGISTRATION SUCCESSFUL");
			        System.out.println("======================================");

			        System.out.println("User ID  : " + userId);
			        System.out.println("Passenger ID : " + passengerId);
			        System.out.println("Username : " + username);

			        System.out.println("======================================");
			        
			        System.out.println("\nOpening User Menu...");
			        userHome(user);
			        }
			    
			 // ================= USER LOGIN =================

			    static void loginUser() {

			        System.out.println("\n======================================");
			        System.out.println("              USER LOGIN");
			        System.out.println("======================================");

			        System.out.print("Enter Username : ");
			        String username = sc.nextLine();
			        
			        
			        System.out.print("Enter Password : ");
			        String password = sc.nextLine();

			        User user = findUser(username, password);

			        if (user != null) {

			            System.out.println("\n======================================");
			            System.out.println("          LOGIN SUCCESSFUL");
			            System.out.println("======================================");

			            System.out.println("Welcome, " + user.name + "!");
			            System.out.println("Passenger ID : " + user.passengerId);


			            userHome(user);

			        } else {

			            System.out.println("\nInvalid Username or Password!");
			        }
			    }
			    
			 // ================= UPDATE PROFILE =================

			    static void updateProfile(User user) {

			        System.out.println("\n======================================");
			        System.out.println("            UPDATE PROFILE");
			        System.out.println("======================================");

			        System.out.println("\nCurrent Details:");

			        System.out.println("User ID  : " + user.userId);
			        System.out.println("Name     : " + user.name);
			        System.out.println("Age      : " + user.age);
			        System.out.println("Mobile   : " + user.mobile);
			        System.out.println("Email    : " + user.email);
			        System.out.println("Username : " + user.username);

			        System.out.println("\nEnter New Details:");

			        user.name = readValidName();

			        user.age = readValidAge();

			        user.mobile = readValidContact();

			        user.email = readValidEmail();

			        System.out.print("Do you want to change password? (yes/no): ");

			        String choice = sc.nextLine();

			        if (choice.equalsIgnoreCase("yes")) {

			            System.out.print("Enter New Password: ");
			            String newPassword = sc.nextLine();

			            System.out.print("Confirm New Password: ");
			            String confirmPassword = sc.nextLine();

			            if (newPassword.equals(confirmPassword)) {

			                user.password = newPassword;

			            } else {

			                System.out.println("Password does not match!");
			                return;
			            }
			        }

			        FileDB.saveAllUsers();

			        Passenger passenger =
			                findPassengerById(user.passengerId);

			        if (passenger != null) {

			            passenger.name = user.name;
			            passenger.age = user.age;
			            passenger.contact = user.mobile;
			            passenger.email = user.email;

			            FileDB.saveAllPassengers();
			        }

			        System.out.println("\n======================================");
			        System.out.println("       PROFILE UPDATED SUCCESSFULLY");
			        System.out.println("======================================");
			    }
			    
			 // ================= LOGGED IN USER MENU =================

			    static void userHome(User user) {

			        int choice;

			        do {

			            System.out.println("\n======================================");
			            System.out.println("              USER MENU");
			            System.out.println("======================================");

			            System.out.println("1. Book Ticket");
			            System.out.println("2. View My Tickets");
			            System.out.println("3. Search Ticket");
			            System.out.println("4. Cancel Ticket");
			            System.out.println("5. Update Profile");
			            System.out.println("6. Logout");

			            System.out.print("\nEnter your choice: ");

			            choice = readInt();

			            switch (choice) {

			            case 1:
			                bookTicket(user);
			                break;

			            case 2:
			                viewMyTickets(user);
			                break;

			            case 3:
			                searchTicket(user);
			                break;

			            case 4:
			                cancelTicket(user);
			                break;

			            case 5:
			                updateProfile(user);
			                break;

			            case 6:
			                System.out.println("Logged out successfully!");
			                break;

			            default:
			                System.out.println("Invalid choice!");
			            }

			        } while (choice != 6);
			    }
			    
			
			    // ================= USERNAME EXISTS =================

			    static boolean usernameExists(String username) {

			        try {

			            BufferedReader br =
			                    new BufferedReader(
			                            new FileReader("users.txt"));

			            String line;

			            while ((line = br.readLine()) != null) {

			                if (line.startsWith("Username      : ")) {

			                    String savedUsername =
			                            line.substring(16).trim();

			                    if (savedUsername.equals(username)) {

			                        br.close();
			                        return true;
			                    }
			                }
			            }

			            br.close();

			        } catch (FileNotFoundException e) {

			            return false;

			        } catch (IOException e) {

			            System.out.println("Error while checking username.");
			        }

			        return false;
			    }


			    // ================= FIND USER =================

			    static User findUser(String username, String password) {

			        try {

			            BufferedReader br =
			                    new BufferedReader(
			                            new FileReader("users.txt"));

			            String line;

			            String userId = "";
			            String name = "";
			            int age = 0;
			            String mobile = "";
			            String email = "";
			            String savedUsername = "";
			            String savedPassword = "";
			            int passengerId = 0;
			            while ((line = br.readLine()) != null) {

			                if (line.startsWith("User ID       : ")) {

			                    userId = line.substring(16).trim();

			                }
			                else if (line.startsWith("Name          : ")) {

			                    name = line.substring(16).trim();

			                }
			                else if (line.startsWith("Age           : ")) {

			                    age = Integer.parseInt(
			                            line.substring(16).trim());

			                }
			                else if (line.startsWith("Mobile        : ")) {

			                    mobile = line.substring(16).trim();

			                }
			                else if (line.startsWith("Email         : ")) {

			                    email = line.substring(16).trim();

			                }
			                else if (line.startsWith("Username      : ")) {

			                    savedUsername =
			                            line.substring(16).trim();

			                }
			                else if (line.startsWith("Passenger ID  : ")) {

			                    passengerId = Integer.parseInt(
			                            line.substring(16).trim());

			                }
			                else if (line.startsWith("Password      : ")) {

			                    savedPassword =
			                            line.substring(16).trim();

			                    
			                    if (savedUsername.equals(username) &&
			                        savedPassword.equals(password)) {
			                    	
			                    	 if (passengerId == 0) {

			                             for (int i = 0; i < passengerList.size(); i++) {

			                                 Passenger p = passengerList.get(i);

			                                 if (p.email.equalsIgnoreCase(email)) {

			                                     passengerId = p.id;
			                                     break;
			                                 }
			                             }
			                         }

			                    	
			                        br.close();

			                        return new User(
			                                userId,
			                                name,
			                                age,
			                                mobile,
			                                email,
			                                savedUsername,
			                                savedPassword,
			                                passengerId
			                        );
			                    }
			                }
			            }

			            br.close();

			        } catch (FileNotFoundException e) {

			            return null;

			        } catch (IOException e) {

			            System.out.println("Error while reading user data.");
			        }

			        return null;
			    }


			    // ================= GENERATE USER ID =================

			    static String generateUserId() {

			        int count = 1001;

			        try {

			            BufferedReader br =
			                    new BufferedReader(
			                            new FileReader("users.txt"));

			            String line;

			            while ((line = br.readLine()) != null) {

			                if (line.startsWith("User ID       : ")) {
			                    count++;
			                }
			            }

			            br.close();

			        } catch (FileNotFoundException e) {

			            // First user

			        } catch (IOException e) {

			            System.out.println("Error while generating User ID.");
			        }

			        return "U" + count;
			    }
			    
					    // ================= PASSENGER MENU =================

		    static void passengerMenu() {

		        int choice;

		        do {

		            System.out.println("\n===== PASSENGER MANAGEMENT =====");

		            System.out.println("1. Passenger Registration");
		            System.out.println("2. Update Passenger");
		            System.out.println("3. Search Passenger");
		            System.out.println("4. View All Passengers");
		            System.out.println("5. Delete Passenger");
		            System.out.println("6. Back");

		            System.out.print("Enter your choice: ");

		            choice = readInt();

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
		                break;

		            default:
		                System.out.println("Invalid choice!");
		            }

		        } while (choice != 6);
		    }


		    // ================= PASSENGER REGISTRATION =================

		    static void registerPassenger() {

		    	System.out.println("\n----- Passenger Details -----");

		    	String name = readValidName();
		    	int age = readValidAge();
		    	String gender = readValidGender();
		    	String phone = readValidContact();
		    	String email = readValidEmail();
		    	String address = readValidAddress();
		    	Passenger p =
		    	        new Passenger(
		    	                nextPassengerId,
		    	                name,
		    	                age,
		    	                gender,
		    	                phone,
		    	                email,
		    	                address
		    	        );

		    	passengerList.add(p);
		    	FileDB.savePassenger(p);
		    	nextPassengerId++;
		    	
		        System.out.println("\n==================================");
		        System.out.println(" PASSENGER REGISTERED SUCCESSFULLY");
		        System.out.println("==================================");

		        System.out.println("Passenger ID : " + p.id);
		        System.out.println("Name         : " + p.name);
		        System.out.println("Age          : " + p.age);
		        System.out.println("Gender       : " + p.gender);
		        System.out.println("Contact      : " + p.contact);
		        System.out.println("Email        : " + p.email);
		        System.out.println("Address      : " + p.address);

		        System.out.println("==================================");
		    }
		    	
		 
		    // ================= UPDATE PASSENGER =================

		    static void updatePassenger() {

		        System.out.print("\nEnter Passenger ID to update: ");

		        int id = readInt();

		        Passenger p = findPassengerById(id);

		        if (p == null) {

		            System.out.println("Passenger not found with ID: " + id);
		            return;
		        }

		        System.out.println("\nCurrent Details:");
		        p.show();

		        System.out.println("\nEnter New Details:");

		        p.name = readValidName();
		        p.age = readValidAge();
		        p.gender = readValidGender();
		        p.contact = readValidContact();
		        p.email = readValidEmail();
		        p.address = readValidAddress();

		        FileDB.saveAllPassengers();
		        System.out.println("Passenger updated successfully!");
		    }


		    // ================= SEARCH PASSENGER =================

		    static void searchPassenger() {

		        System.out.print("\nEnter Passenger ID to search: ");

		        int id = readInt();

		        Passenger p = findPassengerById(id);

		        if (p == null) {
		            System.out.println("Passenger not found.");
		        }
		        else {
		            p.show();
		        }
		    }


		    // ================= VIEW PASSENGERS =================

		    static void viewAllPassengers() {

		        System.out.println("\n--- All Registered Passengers ---");

		        if (passengerList.size() == 0) {

		            System.out.println("No passengers registered yet.");
		            return;
		        }

		        for (int i = 0; i < passengerList.size(); i++) {

		            passengerList.get(i).show();
		        }
		    }


		    // ================= DELETE PASSENGER =================

		    static void deletePassenger() {

		        System.out.print("\nEnter Passenger ID to delete: ");

		        int id = readInt();

		        Passenger p = findPassengerById(id);

		        if (p == null) {

		            System.out.println("Passenger not found.");
		        }
		        else {

		            passengerList.remove(p);

		            FileDB.saveAllPassengers();
		            System.out.println("Passenger deleted successfully.");
		        }
		    }


		    // ================= FIND PASSENGER =================

		    static Passenger findPassengerById(int id) {

		        for (int i = 0; i < passengerList.size(); i++) {

		            Passenger p = passengerList.get(i);

		            if (p.id == id) {
		                return p;
		            }
		        }

		        return null;
		    }
		    
				 // ================= AGENT LOGIN =================
				
				    static void agentLogin() {
				
				        System.out.println("\n======================================");
				        System.out.println("              AGENT LOGIN");
				        System.out.println("======================================");
				
				        System.out.print("Enter Employee ID : ");
				        String employeeId = sc.nextLine();
				
				        System.out.print("Enter Password     : ");
				        String password = sc.nextLine();
				
				        String employeeName = "";
				
				        if (employeeId.equals("101") && password.equals("1234")) {
				            employeeName = "Ravi Kumar";
				        }
				        else if (employeeId.equals("102") && password.equals("2345")) {
				            employeeName = "Priya Sharma";
				        }
				        else if (employeeId.equals("103") && password.equals("3456")) {
				            employeeName = "Arjun Reddy";
				        }
				        else if (employeeId.equals("104") && password.equals("4567")) {
				            employeeName = "Sneha Patel";
				        }
				        else if (employeeId.equals("105") && password.equals("5678")) {
				            employeeName = "Kiran Rao";
				        }
				        else {
				            System.out.println("\nInvalid Employee ID or Password!");
				            return;
				        }
				
				        System.out.println("\nLogin Successful!");
				        System.out.println("Welcome " + employeeName);
				        
				        agentMenu(employeeId, employeeName);
				    }

				    
				 // ================= CREATE AGENT PASSENGER =================

				    static void createAgentPassenger(String employeeId, String employeeName) {

				        // Check whether this agent already has a passenger profile
				        Passenger existingPassenger = null;

				        for (int i = 0; i < passengerList.size(); i++) {

				            Passenger p = passengerList.get(i);

				            if (p.address.equals("Railway Agent")
				                    && p.name.equalsIgnoreCase(employeeName)) {

				                existingPassenger = p;
				                break;
				            }
				        }

				        // Already created
				        if (existingPassenger != null) {

				            System.out.println("\nAgent passenger profile already exists.");
				            System.out.println("Passenger ID : "
				                    + existingPassenger.id);

				            return;
				        }

				        System.out.print("Enter Age : ");
				        int age = sc.nextInt();
				        sc.nextLine();

				        System.out.print("Enter Gender : ");
				        String gender = sc.nextLine();

				        System.out.print("Enter Contact : ");
				        String contact = sc.nextLine();

				        System.out.print("Enter Email : ");
				        String email = sc.nextLine();
				        
				        int passengerId = nextPassengerId++;

				        Passenger passenger = new Passenger(
				                passengerId,
				                employeeName,
				                age,
				                gender,
				                contact,
				                email,
				                "Railway Agent"
				        );
				        
				        

				        passengerList.add(passenger);

				        FileDB.savePassenger(passenger);

				        System.out.println("\n======================================");
				        System.out.println("   AGENT PASSENGER PROFILE CREATED");
				        System.out.println("======================================");

				        System.out.println("Employee ID  : " + employeeId);
				        System.out.println("Employee Name: " + employeeName);
				        System.out.println("Passenger ID : " + passengerId);

				        System.out.println("======================================");
				    }
				    
		    // ================= AGENT MENU =================

		    static void agentMenu(String employeeId, String employeeName) {

		        int choice;

		        do {

		            System.out.println("\n=====AGENT MENU =====");

		            System.out.println("1. Add Train");
		            System.out.println("2. Search Train");
		            System.out.println("3. View Trains");
		            System.out.println("4. Update Train");
		            System.out.println("5. Delete Train");
		            System.out.println("6. Book Ticket");
		            System.out.println("7. View All Tickets");
		            System.out.println("8. Cancel Ticket");
		            System.out.println("9. Back to Main Menu");

		            System.out.print("Enter your choice: ");

		            choice = readInt();

		            switch (choice) {

		            case 1:
		                addTrain();
		                break;

		            case 2:
		                searchTrain();
		                break;

		            case 3:
		                viewTrains();
		                break;

		            case 4:
		                updateTrain();
		                break;

		            case 5:
		                deleteTrain();
		                break;
		                
		            case 6:
		                bookTicket(employeeId, employeeName);
		                break;
		                
		            case 7:
		                viewAllTickets();
		                break;
		                
		            case 8:
		                agentCancelTicket();
		                break;

		            case 9:
		                System.out.println("Returning to Main Menu...");
		                break;
		                
		            default:
		                System.out.println("Invalid choice!");
		            }

		        } while (choice != 9);
		    }


		    
		 // ================= ADD TRAIN =================

		    static void addTrain() {

		        System.out.println("\n===== ADD TRAIN =====");

		        System.out.print("Enter Train Number: ");
		        int number = readInt();

		        if (findTrainByNumber(number) != null) {
		            System.out.println("Train number already exists!");
		            return;
		        }

		        System.out.print("Enter Train Name: ");
		        String name = sc.nextLine();

		        System.out.print("Enter Source: ");
		        String source = sc.nextLine();

		        System.out.print("Enter Destination: ");
		        String destination = sc.nextLine();

		        if (source.equalsIgnoreCase(destination)) {
		            System.out.println("Source and Destination cannot be same.");
		            return;
		        }

		        // ================= CLASS-WISE SEATS =================

		        System.out.print("Enter Sleeper (SL) Seats: ");
		        int slSeats = readInt();

		        System.out.print("Enter 3AC Seats: ");
		        int ac3Seats = readInt();

		        System.out.print("Enter 2AC Seats: ");
		        int ac2Seats = readInt();

		        if (slSeats < 0 || ac3Seats < 0 || ac2Seats < 0) {
		            System.out.println("Seats cannot be negative.");
		            return;
		        }

		        if (slSeats + ac3Seats + ac2Seats == 0) {
		            System.out.println("At least one seat is required.");
		            return;
		        }

		        // ================= FARE =================

		        System.out.print("Enter SL Fare: ");
		        int slFare = readInt();

		        System.out.print("Enter 3AC Fare: ");
		        int ac3Fare = readInt();

		        System.out.print("Enter 2AC Fare: ");
		        int ac2Fare = readInt();

		        // Basic fare will be SL fare
		        Train train = new Train(
		                number,
		                name,
		                source,
		                destination,
		                slSeats,
		                ac3Seats,
		                ac2Seats,
		                slFare,
		                ac3Fare,
		                ac2Fare
		        );

		        trainList.add(train);

		        FileDB.saveAllTrains();

		        System.out.println("\n======================================");
		        System.out.println("          TRAIN ADDED SUCCESSFULLY");
		        System.out.println("======================================");

		        System.out.println("Train Number : " + number);
		        System.out.println("Train Name   : " + name);
		        System.out.println("SL Seats     : " + slSeats);
		        System.out.println("3AC Seats    : " + ac3Seats);
		        System.out.println("2AC Seats    : " + ac2Seats);
		        System.out.println("Total Seats  : " + train.totalSeats);

		        System.out.println("======================================");
		    }
		    
		    // ================= SEARCH TRAIN =================

		    static void searchTrain() {

		        System.out.print("\nEnter Train Number: ");

		        int number = readInt();

		        Train train = findTrainByNumber(number);

		        if (train == null) {

		            System.out.println("Train not found.");
		        }
		        else {

		            train.show();
		        }
		    }


		    // ================= VIEW TRAINS =================

		    static void viewTrains() {

		        System.out.println("\n===== ALL TRAINS =====");

		        if (trainList.size() == 0) {

		            System.out.println("No trains available.");
		            return;
		        }

		        for (int i = 0; i < trainList.size(); i++) {

		            trainList.get(i).show();
		        }
		    }

		 // ================= UPDATE TRAIN =================

		    static void updateTrain() {

		        System.out.print("\nEnter Train Number to update: ");

		        int number = readInt();

		        Train train = findTrainByNumber(number);

		        if (train == null) {

		            System.out.println("Train not found.");
		            return;
		        }

		        System.out.println("\nCurrent Train Details:");
		        train.show();

		        System.out.print("\nEnter New Train Name: ");
		        train.trainName = sc.nextLine();

		        System.out.print("Enter New Source: ");
		        train.source = sc.nextLine();

		        System.out.print("Enter New Destination: ");
		        train.destination = sc.nextLine();

		        if (train.source.equalsIgnoreCase(train.destination)) {

		            System.out.println("Source and Destination cannot be same.");
		            return;
		        }

		        // ================= CLASS-WISE SEATS =================

		        System.out.print("Enter New Sleeper (SL) Seats: ");
		        int slSeats = readInt();

		        System.out.print("Enter New 3AC Seats: ");
		        int ac3Seats = readInt();

		        System.out.print("Enter New 2AC Seats: ");
		        int ac2Seats = readInt();

		        if (slSeats < 0 || ac3Seats < 0 || ac2Seats < 0) {

		            System.out.println("Seats cannot be negative.");
		            return;
		        }

		        if (slSeats + ac3Seats + ac2Seats == 0) {

		            System.out.println("At least one seat is required.");
		            return;
		        }

		        train.slSeats = slSeats;
		        train.ac3Seats = ac3Seats;
		        train.ac2Seats = ac2Seats;

		        train.availableSlSeats = slSeats;
		        train.availableAc3Seats = ac3Seats;
		        train.availableAc2Seats = ac2Seats;

		        train.totalSeats = slSeats + ac3Seats + ac2Seats;
		        train.availableSeats = train.totalSeats;

		        // ================= FARE =================

		        System.out.print("Enter SL Fare: ");
		        int slFare = readInt();

		        System.out.println("Enter 3AC Fare: ");
		        int ac3Fare = readInt();

		        System.out.println("Enter 2AC Fare: ");
		        int ac2Fare = readInt();
		       
		        train.slFare = slFare;
		        train.ac3Fare = ac3Fare;
		        train.ac2Fare = ac2Fare;
		        
		        FileDB.saveAllTrains();

		        
		        System.out.println("\n======================================");
		        System.out.println("       TRAIN UPDATED SUCCESSFULLY");
		        System.out.println("======================================");

		        train.show();
		    }
		    
		    // ================= DELETE TRAIN =================

		    static void deleteTrain() {

		        System.out.print("\nEnter Train Number to delete: ");

		        int number = readInt();

		        Train train = findTrainByNumber(number);

		        if (train == null) {

		            System.out.println("Train not found.");
		            return;
		        }

		        trainList.remove(train);

		        FileDB.saveAllTrains();
		        System.out.println("Train deleted successfully!");
		    }


		    // ================= FIND TRAIN =================

		    static Train findTrainByNumber(int number) {

		        for (int i = 0; i < trainList.size(); i++) {

		            Train train = trainList.get(i);

		            if (train.trainNumber == number) {
		                return train;
		            }
		        }

		        return null;
		    }



		 // ================= ADD SAMPLE TRAINS =================

		    static void addSampleTrains() {

		        Train t1 = new Train(
		                12703,
		                "Falaknuma Express",
		                "Secunderabad",
		                "Howrah",
		                50,     // SL Seats
		                30,     // 3AC Seats
		                20,     // 2AC Seats
		                800,    // SL Fare
		                1200,
		                1600
		        );

		        Train t2 = new Train(
		                12760,
		                "Charminar Express",
		                "Hyderabad",
		                "Chennai",
		                50,
		                30,
		                20,
		                700,
		                1100,
		                1500
		        );

		        Train t3 = new Train(
		                12727,
		                "Godavari Express",
		                "Visakhapatnam",
		                "Hyderabad",
		                50,
		                30,
		                20,
		                600,
		                1000,
		                1400
		        );

		        Train t4 = new Train(
		                12805,
		                "Janmabhoomi Express",
		                "Eluru",
		                "Secunderabad",
		                50,
		                30,
		                20,
		                650,
		                1050,
		                1450
		        );

		        Train t5 = new Train(
		                12717,
		                "Ratnachal Express",
		                "Vijayawada",
		                "Visakhapatnam",
		                50,
		                30,
		                20,
		                550,
		                950,
		                1350
		                
		        );

		        trainList.add(t1);
		        trainList.add(t2);
		        trainList.add(t3);
		        trainList.add(t4);
		        trainList.add(t5);

		        FileDB.saveAllTrains();
		    }
		 
		 
		 // ================= USER MULTIPLE TICKET BOOKING =================

		    static void bookTicket(User user) {

		        System.out.println("\n======================================");
		        System.out.println("        USER TICKET BOOKING");
		        System.out.println("======================================");

		        System.out.print("Enter Number of Tickets: ");
		        int numberOfTickets = readInt();

		        if (numberOfTickets <= 0) {
		            System.out.println("Invalid number of tickets!");
		            return;
		        }

		        Passenger userPassenger = findPassengerById(user.passengerId);

		        if (userPassenger == null) {
		            System.out.println("Passenger details not found.");
		            return;
		        }

		        if (trainList.size() == 0) {
		            System.out.println("No trains available.");
		            return;
		        }

		        // TRAIN SELECTION - ONLY ONCE

		        System.out.println("\n----- AVAILABLE TRAINS -----");

		        for (int j = 0; j < trainList.size(); j++) {

		            Train train = trainList.get(j);

		            System.out.println(
		                    (j + 1) + ". " +
		                    train.trainNumber + " - " +
		                    train.trainName +
		                    " (" + train.source +
		                    " -> " + train.destination + ")"
		            );

		            System.out.println(
		                    "   SL: " + train.availableSlSeats +
		                    " | 3AC: " + train.availableAc3Seats +
		                    " | 2AC: " + train.availableAc2Seats
		            );
		        }

		        System.out.print("\nSelect Train: ");
		        int trainChoice = readInt();

		        if (trainChoice < 1 || trainChoice > trainList.size()) {
		            System.out.println("Invalid train selection.");
		            return;
		        }

		        Train selectedTrain = trainList.get(trainChoice - 1);

		        // JOURNEY DATE - ONLY ONCE

		        System.out.print("\nEnter Journey Date (DD/MM/YYYY): ");
		        String date = sc.nextLine();

		        // CLASS SELECTION - ONLY ONCE

		        System.out.println("\n----- CLASS / SEAT TYPE -----");
		        System.out.println("1. Sleeper (SL)");
		        System.out.println("2. 3 AC");
		        System.out.println("3. 2 AC");

		        System.out.print("Select Class: ");
		        int seatChoice = readInt();

		        String seatType;

		        if (seatChoice == 1) {

		            if (selectedTrain.availableSlSeats < numberOfTickets) {

		                System.out.println(
		                        "Not enough Sleeper seats available!"
		                );

		                System.out.println(
		                        "Available Sleeper Seats: "
		                        + selectedTrain.availableSlSeats
		                );

		                return;
		            }

		            seatType = "SL";

		        } else if (seatChoice == 2) {

		            if (selectedTrain.availableAc3Seats < numberOfTickets) {

		                System.out.println(
		                        "Not enough 3AC seats available!"
		                );

		                System.out.println(
		                        "Available 3AC Seats: "
		                        + selectedTrain.availableAc3Seats
		                );

		                return;
		            }

		            seatType = "3AC";

		        } else if (seatChoice == 3) {

		            if (selectedTrain.availableAc2Seats < numberOfTickets) {

		                System.out.println(
		                        "Not enough 2AC seats available!"
		                );

		                System.out.println(
		                        "Available 2AC Seats: "
		                        + selectedTrain.availableAc2Seats
		                );

		                return;
		            }

		            seatType = "2AC";

		        } else {

		            System.out.println("Invalid class selection.");
		            return;
		        }
		        
		     
		     // ================= FARE CALCULATION =================

		        double farePerTicket;

		        if (seatType.equalsIgnoreCase("SL")) {

		            farePerTicket = selectedTrain.slFare;

		        }
		        else if (seatType.equalsIgnoreCase("3AC")) {

		            farePerTicket = selectedTrain.ac3Fare;

		        }
		        else {

		            farePerTicket = selectedTrain.ac2Fare;
		        }

		        double totalFare = farePerTicket * numberOfTickets;
		        
		        // BOOKING TYPE - ONLY ONCE

		        System.out.println("\n----- BOOKING TYPE -----");
		        System.out.println("1. Normal Booking");
		        System.out.println("2. Tatkal Booking");

		        System.out.print("Select Booking Type: ");
		        int bookingChoice = readInt();

		        String bookingType;

		        if (bookingChoice == 1) {

		            bookingType = "Normal";

		        } else if (bookingChoice == 2) {

		            bookingType = "Tatkal";

		        } else {

		            System.out.println("Invalid booking type.");
		            return;
		        }
		        
		        if (bookingType.equalsIgnoreCase("Tatkal")) {

		            totalFare = totalFare + (200 * numberOfTickets);
		        }

		        // ONE PNR FOR ALL PASSENGERS

		        int bookingPNR = nextPNR++;

		        // Store all bookings of this PNR
		        ArrayList<Booking> currentBookings = new ArrayList<>();

		        // MULTIPLE PASSENGERS

		        for (int i = 1; i <= numberOfTickets; i++) {

		            System.out.println(
		                    "\n======================================"
		            );

		            System.out.println(
		                    "        PASSENGER " + i + " DETAILS"
		            );

		            System.out.println(
		                    "======================================"
		            );

		            Passenger passenger;

		            // FIRST PASSENGER = LOGGED-IN USER

		            if (i == 1) {

		                passenger = userPassenger;

		                System.out.println(
		                        "Passenger ID : " + passenger.id
		                );

		                System.out.println(
		                        "Name         : " + passenger.name
		                );

		                System.out.println(
		                        "Age          : " + passenger.age
		                );

		                System.out.println(
		                        "Gender       : " + passenger.gender
		                );

		                System.out.println(
		                        "Phone        : " + passenger.contact
		                );

		                System.out.println(
		                        "Email        : " + passenger.email
		                );

		            }

		            // OTHER PASSENGERS

		            else {

		                String name = readValidName();

		                int age = readValidAge();

		                String gender = readValidGender();

		                String contact = readValidContact();

		                String email = readValidEmail();

		                String address = readValidAddress();

		                int passengerId = nextPassengerId++;

		                passenger = new Passenger(
		                        passengerId,
		                        name,
		                        age,
		                        gender,
		                        contact,
		                        email,
		                        address
		                );

		                passengerList.add(passenger);

		                FileDB.savePassenger(passenger);

		                System.out.println(
		                        "\nPassenger Registered Successfully!"
		                );

		                System.out.println(
		                        "Passenger ID : " + passengerId
		                );
		            }

		            // SEAT ALLOCATION

		            int seatNumber;

		            if (seatType.equals("SL")) {

		                seatNumber =
		                        selectedTrain.slSeats
		                        - selectedTrain.availableSlSeats
		                        + 1;

		                selectedTrain.availableSlSeats--;

		            }

		            else if (seatType.equals("3AC")) {

		                seatNumber =
		                        selectedTrain.ac3Seats
		                        - selectedTrain.availableAc3Seats
		                        + 1;

		                selectedTrain.availableAc3Seats--;

		            }

		            else {

		                seatNumber =
		                        selectedTrain.ac2Seats
		                        - selectedTrain.availableAc2Seats
		                        + 1;

		                selectedTrain.availableAc2Seats--;
		            }

		            
		            // REDUCE TOTAL AVAILABLE SEATS
		            
		            selectedTrain.availableSeats--;

		            
		            // SAME PNR FOR ALL PASSENGERS
		            
		            Booking booking = new Booking(
		                    bookingPNR,
		                    passenger,
		                    selectedTrain,
		                    date,
		                    seatType,
		                    seatNumber,
		                    bookingType
		            );

		            bookingList.add(booking);

		            currentBookings.add(booking);

		            FileDB.saveBookingData(booking);

		            System.out.println(
		                    "\nPassenger " + i + " Seat Allocated: "
		                    + seatType + "/" + seatNumber
		            );
		        }

		        
		        // SAVE TRAIN DETAILS

		        FileDB.saveAllTrains();

		        // FINAL MULTIPLE PASSENGER TICKET

		        System.out.println("\n\n======================================");
		        System.out.println("       TICKET BOOKED SUCCESSFULLY");
		        System.out.println("======================================");

		        System.out.println("PNR Number       : " + bookingPNR);

		        System.out.println(
		                "Train Number     : " + selectedTrain.trainNumber
		        );

		        System.out.println(
		                "Train Name       : " + selectedTrain.trainName
		        );

		        System.out.println(
		                "From             : " + selectedTrain.source
		        );

		        System.out.println(
		                "To               : " + selectedTrain.destination
		        );

		        System.out.println(
		                "Journey Date     : " + date
		        );

		        System.out.println(
		                "Class            : " + seatType
		        );

		        System.out.println(
		                "Booking Type     : " + bookingType
		        );

		        System.out.println(
		                "Total Passengers : " + numberOfTickets
		        );

		        System.out.println("\n--------------------------------------");
		        System.out.println("        PASSENGER DETAILS");
		        System.out.println("--------------------------------------");

		        int passengerNumber = 1;

		        for (Booking booking : currentBookings) {

		            System.out.println(
		                    "\n" + passengerNumber + ". "
		                    + booking.passenger.name
		            );

		            System.out.println(
		                    "   Passenger ID : "
		                    + booking.passenger.id
		            );

		            System.out.println(
		                    "   Age          : "
		                    + booking.passenger.age
		            );

		            System.out.println(
		                    "   Gender       : "
		                    + booking.passenger.gender
		            );

		            System.out.println(
		                    "   Seat         : "
		                    + booking.seatType
		                    + "/" + booking.seatNumber
		            );

		            System.out.println(
		                    "   Status       : CONFIRMED"
		            );

		            passengerNumber++;
		        }

		        System.out.println("\n--------------------------------------");

		        System.out.println(
		                "PNR " + bookingPNR
		                + " contains "
		                + numberOfTickets
		                + " passenger(s)."
		        );

		        System.out.println("\n--------------------------------------");

		        System.out.println("           PAYMENT DETAILS");
		        System.out.println("--------------------------------------");

		        System.out.println("Fare Per Ticket : ₹" + farePerTicket);
		        System.out.println("Total Tickets   : " + numberOfTickets);
		        System.out.println("Total Fare      : ₹" + totalFare);

		        System.out.println("\n--------------------------------------");

		        System.out.println(
		                "PNR " + bookingPNR +
		                " contains " + numberOfTickets +
		                " passenger(s)."
		        );

		        System.out.println("======================================");
		        System.out.println("          BOOKING COMPLETE");
		        System.out.println("======================================");
		    }
		       
		    
		 // ================= AGENT MULTIPLE TICKET BOOKING =================

		    static void bookTicket(String employeeId, String employeeName) {

		        System.out.println("\n======================================");
		        System.out.println("      AGENT MULTIPLE TICKET BOOKING");
		        System.out.println("======================================");

		        System.out.println("Employee ID   : " + employeeId);
		        System.out.println("Employee Name : " + employeeName);

		        // ================= NUMBER OF TICKETS =================

		        System.out.print("\nEnter Number of Tickets: ");
		        int numberOfTickets = readInt();

		        if (numberOfTickets <= 0) {
		            System.out.println("Invalid number of tickets!");
		            return;
		        }

		        // ================= TRAIN CHECK =================

		        if (trainList.size() == 0) {
		            System.out.println("No trains available.");
		            return;
		        }

		        // ================= AVAILABLE TRAINS =================

		        System.out.println("\n----- AVAILABLE TRAINS -----");

		        for (int i = 0; i < trainList.size(); i++) {

		            Train train = trainList.get(i);

		            System.out.println("\n" + (i + 1) + ". "
		                    + train.trainNumber + " - "
		                    + train.trainName);

		            System.out.println("   "
		                    + train.source + " -> "
		                    + train.destination);

		            System.out.println("   SL  : "
		                    + train.availableSlSeats);

		            System.out.println("   3AC : "
		                    + train.availableAc3Seats);

		            System.out.println("   2AC : "
		                    + train.availableAc2Seats);

		            System.out.println("   Total Available : "
		                    + train.availableSeats);
		        }

		        // ================= TRAIN SELECTION =================

		        System.out.print("\nSelect Train: ");
		        int trainChoice = readInt();

		        if (trainChoice < 1 ||
		            trainChoice > trainList.size()) {

		            System.out.println("Invalid train selection.");
		            return;
		        }

		        Train selectedTrain =
		                trainList.get(trainChoice - 1);

		        if (selectedTrain.availableSeats < numberOfTickets) {

		            System.out.println(
		                    "Not enough total seats available!"
		            );

		            System.out.println(
		                    "Available Seats : "
		                    + selectedTrain.availableSeats
		            );

		            return;
		        }

		        // ================= JOURNEY DATE =================

		        System.out.print(
		                "\nEnter Journey Date (DD-MM-YYYY): ");

		        String date = sc.nextLine();

		        // ================= CLASS SELECTION =================

		        System.out.println("\n----- CLASS / SEAT TYPE -----");

		        System.out.println("1. Sleeper (SL)");
		        System.out.println("2. 3 AC");
		        System.out.println("3. 2 AC");

		        System.out.print("Select Class: ");

		        int seatChoice = readInt();

		        String seatType;

		        if (seatChoice == 1) {

		            seatType = "SL";

		            if (selectedTrain.availableSlSeats < numberOfTickets) {

		                System.out.println(
		                        "Not enough Sleeper seats available!"
		                );

		                System.out.println(
		                        "Available Sleeper Seats : "
		                        + selectedTrain.availableSlSeats
		                );

		                return;
		            }
		        }

		        else if (seatChoice == 2) {

		            seatType = "3AC";

		            if (selectedTrain.availableAc3Seats < numberOfTickets) {

		                System.out.println(
		                        "Not enough 3AC seats available!"
		                );

		                System.out.println(
		                        "Available 3AC Seats : "
		                        + selectedTrain.availableAc3Seats
		                );

		                return;
		            }
		        }

		        else if (seatChoice == 3) {

		            seatType = "2AC";

		            if (selectedTrain.availableAc2Seats < numberOfTickets) {

		                System.out.println(
		                        "Not enough 2AC seats available!"
		                );

		                System.out.println(
		                        "Available 2AC Seats : "
		                        + selectedTrain.availableAc2Seats
		                );

		                return;
		            }
		        }

		        else {

		            System.out.println("Invalid class selection.");
		            return;
		        }

		        // ================= FARE CALCULATION =================

		        double farePerTicket;

		        if (seatType.equals("SL")) {

		            farePerTicket = 250;

		        }
		        else if (seatType.equals("3AC")) {

		            farePerTicket = 600;

		        }
		        else {

		            farePerTicket = 900;
		        }

		        // ================= BOOKING TYPE =================

		        System.out.println("\n----- BOOKING TYPE -----");

		        System.out.println("1. Normal Booking");
		        System.out.println("2. Tatkal Booking");

		        System.out.print("Select Booking Type: ");

		        int bookingChoice = readInt();

		        String bookingType;

		        if (bookingChoice == 1) {

		            bookingType = "Normal";

		        }
		        else if (bookingChoice == 2) {

		            bookingType = "Tatkal";

		        }
		        else {

		            System.out.println("Invalid booking type.");
		            return;
		        }

		        // ================= TOTAL FARE =================

		        double totalFare = farePerTicket * numberOfTickets;

		        if (bookingType.equalsIgnoreCase("Tatkal")) {

		            totalFare = totalFare + (200 * numberOfTickets);
		        }

		        // ================= ONE PNR FOR ALL =================

		        int bookingPNR = nextPNR++;

		        // Store current bookings
		        ArrayList<Booking> currentBookings =
		                new ArrayList<Booking>();

		        // ================= MULTIPLE PASSENGERS =================

		        for (int i = 1; i <= numberOfTickets; i++) {

		            System.out.println(
		                    "\n======================================"
		            );

		            System.out.println(
		                    "        PASSENGER " + i + " DETAILS"
		            );

		            System.out.println(
		                    "======================================"
		            );

		            // ================= PASSENGER DETAILS =================

		            String name = readValidName();

		            int age = readValidAge();

		            String gender = readValidGender();

		            String contact = readValidContact();

		            String email = readValidEmail();

		            String address = readValidAddress();

		            int passengerId = nextPassengerId++;

		            Passenger passenger = new Passenger(
		                    passengerId,
		                    name,
		                    age,
		                    gender,
		                    contact,
		                    email,
		                    address
		            );

		            passengerList.add(passenger);

		            FileDB.savePassenger(passenger);

		            System.out.println(
		                    "\nPassenger Registered Successfully!"
		            );

		            System.out.println(
		                    "Passenger ID : " + passengerId
		            );

		            // ================= SEAT NUMBER =================

		            int seatNumber;

		            if (seatType.equalsIgnoreCase("SL")) {

		                seatNumber =
		                        selectedTrain.slSeats
		                        - selectedTrain.availableSlSeats
		                        + 1;

		                selectedTrain.availableSlSeats--;

		            }

		            else if (seatType.equalsIgnoreCase("3AC")) {

		                seatNumber =
		                        selectedTrain.ac3Seats
		                        - selectedTrain.availableAc3Seats
		                        + 1;

		                selectedTrain.availableAc3Seats--;

		            }

		            else {

		                seatNumber =
		                        selectedTrain.ac2Seats
		                        - selectedTrain.availableAc2Seats
		                        + 1;

		                selectedTrain.availableAc2Seats--;
		            }

		            // ================= REDUCE TOTAL SEATS =================

		            selectedTrain.availableSeats--;

		            // ================= CREATE BOOKING =================

		            Booking booking = new Booking(
		                    bookingPNR,
		                    passenger,
		                    selectedTrain,
		                    date,
		                    seatType,
		                    seatNumber,
		                    bookingType
		            );

		            bookingList.add(booking);

		            currentBookings.add(booking);

		            FileDB.saveBookingData(booking);

		            System.out.println(
		                    "\nPassenger " + i
		                    + " Seat Allocated : "
		                    + seatType + "/" + seatNumber
		            );
		        }

		        // ================= SAVE TRAIN =================

		        FileDB.saveAllTrains();

		        // ================= FINAL TICKET =================

		        System.out.println("\n\n======================================");
		        System.out.println("       TICKET BOOKED SUCCESSFULLY");
		        System.out.println("======================================");

		        System.out.println(
		                "Booked By Agent  : " + employeeName
		        );

		        System.out.println(
		                "Employee ID      : " + employeeId
		        );

		        System.out.println(
		                "PNR Number       : " + bookingPNR
		        );

		        System.out.println(
		                "Train Number     : "
		                + selectedTrain.trainNumber
		        );

		        System.out.println(
		                "Train Name       : "
		                + selectedTrain.trainName
		        );

		        System.out.println(
		                "From             : "
		                + selectedTrain.source
		        );

		        System.out.println(
		                "To               : "
		                + selectedTrain.destination
		        );

		        System.out.println(
		                "Journey Date     : " + date
		        );

		        System.out.println(
		                "Class            : " + seatType
		        );

		        System.out.println(
		                "Booking Type     : " + bookingType
		        );

		        System.out.println(
		                "Total Passengers : "
		                + numberOfTickets
		        );

		        // ================= PASSENGER DETAILS =================

		        System.out.println(
		                "\n--------------------------------------"
		        );

		        System.out.println(
		                "        PASSENGER DETAILS"
		        );

		        System.out.println(
		                "--------------------------------------"
		        );

		        int passengerNumber = 1;

		        for (Booking booking : currentBookings) {

		            System.out.println(
		                    "\n" + passengerNumber
		                    + ". "
		                    + booking.passenger.name
		            );

		            System.out.println(
		                    "   Passenger ID : "
		                    + booking.passenger.id
		            );

		            System.out.println(
		                    "   Age          : "
		                    + booking.passenger.age
		            );

		            System.out.println(
		                    "   Gender       : "
		                    + booking.passenger.gender
		            );

		            System.out.println(
		                    "   Contact      : "
		                    + booking.passenger.contact
		            );

		            System.out.println(
		                    "   Email        : "
		                    + booking.passenger.email
		            );

		            System.out.println(
		                    "   Seat         : "
		                    + booking.seatType
		                    + "/" + booking.seatNumber
		            );

		            System.out.println(
		                    "   Status       : CONFIRMED"
		            );

		            passengerNumber++;
		        }

		        // ================= PAYMENT DETAILS =================

		        System.out.println(
		                "\n--------------------------------------"
		        );

		        System.out.println(
		                "           PAYMENT DETAILS"
		        );

		        System.out.println(
		                "--------------------------------------"
		        );

		        System.out.println(
		                "Fare Per Ticket : ₹"
		                + farePerTicket
		        );

		        System.out.println(
		                "Total Tickets   : "
		                + numberOfTickets
		        );

		        System.out.println(
		                "Total Fare      : ₹"
		                + totalFare
		        );

		        System.out.println(
		                "\n--------------------------------------"
		        );

		        System.out.println(
		                "PNR " + bookingPNR
		                + " contains "
		                + numberOfTickets
		                + " passenger(s)."
		        );

		        System.out.println("======================================");
		        System.out.println("          BOOKING COMPLETE");
		        System.out.println("======================================");
		    }
		       
		 // ================= SEARCH TICKET =================

		    static void searchTicket(User user) {

		        System.out.print("\nEnter PNR Number: ");

		        int pnr = readInt();

		        boolean found = false;

		        for (int i = 0; i < bookingList.size(); i++) {

		            Booking booking = bookingList.get(i);

		            if (booking.pnr == pnr) {

		                if (!found) {
		                    System.out.println("\n======================================");
		                    System.out.println("          TICKET DETAILS");
		                    System.out.println("======================================");
		                    System.out.println("PNR Number : " + booking.pnr);
		                    System.out.println("Train      : " + booking.train.trainName);
		                    System.out.println("Train No   : " + booking.train.trainNumber);
		                    System.out.println("From       : " + booking.train.source);
		                    System.out.println("To         : " + booking.train.destination);
		                    System.out.println("Journey Date: " + booking.journeyDate);
		                    System.out.println("======================================");
		                }

		                System.out.println("\nPassenger : "
		                        + booking.passenger.name);

		                System.out.println("Passenger ID : "
		                        + booking.passenger.id);

		                System.out.println("Age : "
		                        + booking.passenger.age);

		                System.out.println("Gender : "
		                        + booking.passenger.gender);

		                System.out.println("Seat : "
		                        + booking.seatType + "/" + booking.seatNumber);

		                System.out.println("Booking Type : "
		                        + booking.bookingType);

		                System.out.println("Fare : Rs."
		                        + booking.totalFare);

		                System.out.println("Status : CONFIRMED");

		                System.out.println("--------------------------------------");

		                found = true;
		            }
		        }

		        if (!found) {
		            System.out.println("Ticket not found.");
		        }
		    }    

		 // ================= CANCEL TICKET =================

		    static void cancelTicket(User user) {

		        System.out.print("\nEnter PNR Number to cancel: ");

		        int pnr = readInt();

		        Booking bookingToCancel = null;

		        for (int i = 0; i < bookingList.size(); i++) {

		            Booking booking = bookingList.get(i);

		            if (booking.pnr == pnr &&
		                 booking.passenger.id == user.passengerId) {
		                bookingToCancel = booking;
		                break;
		            }
		        }

		        if (bookingToCancel == null) {

		            System.out.println("Ticket not found.");
		            return;
		        }

		     // ================= RETURN CLASS-WISE SEAT =================

		        if (bookingToCancel.seatType.equals("SL")) {

		            bookingToCancel.train.availableSlSeats++;

		        }
		        else if (bookingToCancel.seatType.equals("3AC")) {

		            bookingToCancel.train.availableAc3Seats++;

		        }
		        else if (bookingToCancel.seatType.equals("2AC")) {

		            bookingToCancel.train.availableAc2Seats++;

		        }

		        bookingToCancel.train.availableSeats++;

		        bookingList.remove(bookingToCancel);

		        saveAllBookings();
		 
		        FileDB.saveAllTrains();
		        System.out.println("\nTicket cancelled successfully!");
		        System.out.println("PNR Number : " + pnr);
		        System.out.println("Available Seats : "
		                + bookingToCancel.train.availableSeats);
		    }
		    
		 // ================= SAVE ALL BOOKINGS =================

		    static void saveAllBookings() {

		        try {

		            FileWriter fw =
		                    new FileWriter("booking.txt", false);

		            BufferedWriter bw =
		                    new BufferedWriter(fw);

		            for (int i = 0; i < bookingList.size(); i++) {

		                Booking b = bookingList.get(i);

		                bw.write("======================================");
		                bw.newLine();
		                bw.write("          INDIAN RAILWAY TICKET");
		                bw.newLine();
		                bw.write("======================================");
		                bw.newLine();

		                bw.write("PNR Number    : " + b.pnr);
		                bw.newLine();

		                bw.write("\nPassenger Details");
		                bw.newLine();
		                bw.write("Passenger ID   : " + b.passenger.id);
		                bw.newLine();
		                bw.write("Passenger Name : " + b.passenger.name);
		                bw.newLine();
		                bw.write("Age            : " + b.passenger.age);
		                bw.newLine();
		                bw.write("Gender         : " + b.passenger.gender);
		                bw.newLine();
		                bw.write("Phone Number   : " + b.passenger.contact);
		                bw.newLine();
		                bw.write("Email          : " + b.passenger.email);
		                bw.newLine();

		                bw.write("\nTrain Details");
		                bw.newLine();
		                bw.write("Train No       : " + b.train.trainNumber);
		                bw.newLine();
		                bw.write("Train Name     : " + b.train.trainName);
		                bw.newLine();
		                bw.write("From           : " + b.train.source);
		                bw.newLine();
		                bw.write("To             : " + b.train.destination);
		                bw.newLine();
		                bw.write("Available Seats: " + b.train.availableSeats);
		                bw.newLine();

		                bw.write("\nJourney Date   : " + b.journeyDate);
		                bw.newLine();
		                bw.write("Class / Seat   : " + b.seatType);
		                bw.newLine();
		                bw.write("Seat Number    : " + b.seatNumber);
		                bw.newLine();
		                bw.write("Booking Type   : " + b.bookingType);
		                bw.newLine();

		                bw.write("--------------------------------------");
		                bw.newLine();
		                bw.write("Total Fare     : Rs." + b.totalFare);
		                bw.newLine();
		                bw.write("Booking Status : CONFIRMED");
		                bw.newLine();

		                bw.write("======================================");
		                bw.newLine();
		                bw.newLine();
		            }

		            bw.close();

		            System.out.println("Booking file updated successfully.");

		        }
		        catch (IOException e) {

		            System.out.println("Error while updating booking file.");
		        }
		    }

		 // ================= VIEW MY TICKETS =================

		    static void viewMyTickets(User user) {

		        System.out.println("\n===== MY TICKETS =====");

		        boolean found = false;

		        // First find bookings made by the logged-in user
		        for (int i = 0; i < bookingList.size(); i++) {

		            Booking booking = bookingList.get(i);

		            if (booking.passenger.id == user.passengerId) {

		                int currentPNR = booking.pnr;

		                // Avoid displaying the same PNR multiple times
		                boolean alreadyDisplayed = false;

		                for (int j = 0; j < i; j++) {

		                    if (bookingList.get(j).pnr == currentPNR) {
		                        alreadyDisplayed = true;
		                        break;
		                    }
		                }

		                if (alreadyDisplayed) {
		                    continue;
		                }

		                // Display all passengers under this PNR
		                System.out.println("\nPNR : " + currentPNR);

		                for (int j = 0; j < bookingList.size(); j++) {

		                    Booking passengerBooking = bookingList.get(j);

		                    if (passengerBooking.pnr == currentPNR) {

		                        System.out.println("\nPassenger : "
		                                + passengerBooking.passenger.name);

		                        System.out.println("Passenger ID : "
		                                + passengerBooking.passenger.id);

		                        System.out.println("Train : "
		                                + passengerBooking.train.trainName);

		                        System.out.println("Journey Date : "
		                                + passengerBooking.journeyDate);

		                        System.out.println("Seat Number : "
		                                + passengerBooking.seatNumber);

		                        System.out.println("Seat Type : "
		                                + passengerBooking.seatType);

		                        System.out.println("Booking Type : "
		                                + passengerBooking.bookingType);

		                        System.out.println("Fare : Rs."
		                                + passengerBooking.totalFare);

		                        System.out.println("Status : CONFIRMED");

		                        System.out.println("----------------------------------");
		                    }
		                }

		                found = true;
		            }
		        }

		        if (!found) {
		            System.out.println("You have no booked tickets.");
		        }
		    }
		    
		    
		 // ================= AGENT CANCEL TICKET =================

		    static void agentCancelTicket() {

		        System.out.println("\n===== AGENT CANCEL TICKET =====");

		        System.out.print("Enter PNR Number to cancel: ");

		        int pnr = readInt();

		        Booking bookingToCancel = null;

		        for (int i = 0; i < bookingList.size(); i++) {

		            Booking booking = bookingList.get(i);

		            if (booking.pnr == pnr) {

		                bookingToCancel = booking;
		                break;
		            }
		        }

		        if (bookingToCancel == null) {

		            System.out.println("Ticket not found.");
		            return;
		        }

		        System.out.println("\nTicket Details:");

		        bookingToCancel.displayTicket();

		        System.out.print(
		                "\nAre you sure you want to cancel? (yes/no): ");

		        String confirm = sc.nextLine();

		        if (!confirm.equalsIgnoreCase("yes")) {

		            System.out.println("Ticket cancellation stopped.");
		            return;
		        }

		        // Return seat
		     // ================= RETURN CLASS-WISE SEAT =================

		        if (bookingToCancel.seatType.equals("SL")) {

		            bookingToCancel.train.availableSlSeats++;

		        }
		        else if (bookingToCancel.seatType.equals("3AC")) {

		            bookingToCancel.train.availableAc3Seats++;

		        }
		        else if (bookingToCancel.seatType.equals("2AC")) {

		            bookingToCancel.train.availableAc2Seats++;

		        }

		        bookingToCancel.train.availableSeats++;

		        // Remove booking
		        bookingList.remove(bookingToCancel);

		        // Update files
		        saveAllBookings();
		        FileDB.saveAllTrains();

		        System.out.println("\n======================================");
		        System.out.println("       TICKET CANCELLED SUCCESSFULLY");
		        System.out.println("======================================");

		        System.out.println("PNR Number     : " + pnr);
		        System.out.println("Passenger      : "
		                + bookingToCancel.passenger.name);

		        System.out.println("Available Seats: "
		                + bookingToCancel.train.availableSeats);

		        System.out.println("======================================");
		    }
		    
		    // ================= VIEW ALL TICKETS =================

		    static void viewAllTickets() {

		        System.out.println("\n===== ALL BOOKINGS =====");

		        if (bookingList.size() == 0) {

		            System.out.println("No tickets booked yet.");
		            return;
		        }

		        for (int i = 0; i < bookingList.size(); i++) {

		            Booking booking = bookingList.get(i);

		            System.out.println("\nPNR : " + booking.pnr);
		            System.out.println("Passenger : " +
		                    booking.passenger.name);

		            System.out.println("Train : " +
		                    booking.train.trainName);

		            System.out.println("Status : CONFIRMED");

		            System.out.println("----------------------------------");
		        }
		    }


		    // ================= INPUT VALIDATION =================

		    static int readInt() {

		        while (true) {

		            try {

		                String input = sc.nextLine();

		                return Integer.parseInt(input);

		            }
		            catch (NumberFormatException e) {

		                System.out.print(
		                        "Please enter a valid number: "
		                );
		            }
		        }
		    }


		    static boolean isValidName(String name) {

		        if (name == null ||
		            name.trim().length() == 0) {

		            return false;
		        }

		        for (int i = 0; i < name.length(); i++) {

		            char c = name.charAt(i);

		            if (!Character.isLetter(c) &&
		                c != ' ') {

		                return false;
		            }
		        }

		        return true;
		    }


		    static int readValidAge() {

		        while (true) {

		            System.out.print("Enter Age: ");

		            int age = readInt();

		            if (age > 0 && age <= 120) {

		                return age;
		            }

		            System.out.println(
		                    "Invalid age. Enter between 1 and 120."
		            );
		        }
		    }


		    static String readValidName() {

		        while (true) {

		            System.out.print("Enter Name: ");

		            String name = sc.nextLine();

		            if (isValidName(name)) {

		                return name;
		            }

		            System.out.println(
		                    "Invalid name. Only letters and spaces allowed."
		            );
		        }
		    }


		    static String readValidGender() {

		        while (true) {

		            System.out.print(
		                    "Enter Gender (Male/Female/Other): "
		            );

		            String gender = sc.nextLine();

		            if (gender.equalsIgnoreCase("Male") ||
		                gender.equalsIgnoreCase("Female") ||
		                gender.equalsIgnoreCase("Other")) {

		                return gender;
		            }

		            System.out.println(
		                    "Invalid gender."
		            );
		        }
		    }


		    static String readValidContact() {

		        while (true) {

		            System.out.print(
		                    "Enter Contact Number (10 digits): "
		            );

		            String contact = sc.nextLine();

		            if (contact.matches("[6-9][0-9]{9}")) {

		                return contact;
		            }

		            System.out.println(
		                    "Invalid number. Enter a valid 10 digit number."
		            );
		        }
		    }


		    static String readValidEmail() {

		        while (true) {

		            System.out.print("Enter Email: ");

		            String email = sc.nextLine();

		            if (email.contains("@") &&
		                email.contains(".")) {

		                return email;
		            }

		            System.out.println(
		                    "Invalid email."
		            );
		        }
		    }


		    static String readValidAddress() {

		        while (true) {

		            System.out.print("Enter Address: ");

		            String address = sc.nextLine();

		            if (address.trim().length() > 0) {

		                return address;
		            }

		            System.out.println(
		                    "Address cannot be empty."
		            );
		        }
		    }
		}
	

