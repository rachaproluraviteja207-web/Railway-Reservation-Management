package railway;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileDB {
	
		// ================= LOAD PASSENGERS =================

	    static void loadPassengers() {

	        try {

	            BufferedReader br =
	                    new BufferedReader(
	                            new FileReader("passengers.txt"));

	            String line;

	            int id = 0;
	            String name = "";
	            int age = 0;
	            String gender = "";
	            String contact = "";
	            String email = "";
	            String address = "";
	            
	            while ((line = br.readLine()) != null) {

	                if (line.startsWith("Passenger ID   : ")) {

	                    id = Integer.parseInt(
	                            line.substring(17).trim());
	                }

	                else if (line.startsWith("Name           : ")) {

	                    name = line.substring(17).trim();
	                }

	                else if (line.startsWith("Age            : ")) {

	                    age = Integer.parseInt(
	                            line.substring(17).trim());
	                }

	                else if (line.startsWith("Gender         : ")) {

	                    gender = line.substring(17).trim();
	                }

	                else if (line.startsWith("Contact Number : ")) {

	                    contact = line.substring(17).trim();
	                }

	                else if (line.startsWith("Email          : ")) {

	                    email = line.substring(17).trim();
	                }

	                else if (line.startsWith("Address        : ")) {

	                    address = line.substring(17).trim();
	                    
	                

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

	                    RailwayMain.passengerList.add(p);

	                    if (id >= RailwayMain.nextPassengerId) {
	                        RailwayMain.nextPassengerId = id + 1;
	                    }
	                }
	            }

	            br.close();

	        }
	        catch (FileNotFoundException e) {

	         
	        }
	        catch (IOException e) {

	            System.out.println(
	                    "Error while loading passenger data."
	            );
	        }
	    }
	    
		// ================= SAVE PASSENGER =================

		static void savePassenger(Passenger p) {

		    try {

		        FileWriter fw =
		                new FileWriter("passengers.txt", true);

		        BufferedWriter bw =
		                new BufferedWriter(fw);

		        bw.write("======================================");
		        bw.newLine();

		        bw.write("Passenger ID   : " + p.id);
		        bw.newLine();

		        bw.write("Name           : " + p.name);
		        bw.newLine();

		        bw.write("Age            : " + p.age);
		        bw.newLine();

		        bw.write("Gender         : " + p.gender);
		        bw.newLine();

		        bw.write("Contact Number : " + p.contact);
		        bw.newLine();

		        bw.write("Email          : " + p.email);
		        bw.newLine();

		        bw.write("Address        : " + p.address);
		        bw.newLine();

		        bw.write("======================================");
		        bw.newLine();
		        bw.newLine();

		        bw.close();

		        System.out.println("Passenger information saved to passengers.txt");

		    } catch (IOException e) {

		        System.out.println("Error while saving passenger.");
		    }
		}


		//===================== SAVE ALL PASSENGERS==================
	    static void saveAllPassengers() {

	        try {

	            FileWriter fw = new FileWriter("passengers.txt", false);
	            BufferedWriter bw = new BufferedWriter(fw);

	            for (int i = 0; i < RailwayMain.passengerList.size(); i++) {

	                Passenger p = RailwayMain.passengerList.get(i);

	                bw.write("======================================");
	                bw.newLine();

	                bw.write("Passenger ID   : " + p.id);
	                bw.newLine();

	                bw.write("Name           : " + p.name);
	                bw.newLine();

	                bw.write("Age            : " + p.age);
	                bw.newLine();

	                bw.write("Gender         : " + p.gender);
	                bw.newLine();

	                bw.write("Contact Number : " + p.contact);
	                bw.newLine();

	                bw.write("Email          : " + p.email);
	                bw.newLine();

	                bw.write("Address        : " + p.address);
	                bw.newLine();

	                bw.write("======================================");
	                bw.newLine();
	                bw.newLine();
	            }

	            bw.close();

	        } catch (IOException e) {

	            System.out.println("Error while updating passenger data.");
	        }
	    }

	    // ================= LOAD USERS =================

	    static void loadUsers() {

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
	            String username = "";
	            String password = "";
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

	                    username = line.substring(16).trim();
	                }
	                else if (line.startsWith("Passenger ID  : ")) {

	                    passengerId = Integer.parseInt(
	                            line.substring(16).trim());
	                }

	                else if (line.startsWith("Password      : ")) {

	                    password = line.substring(16).trim();

	                    if (passengerId == 0) {

	                        for (int i = 0; i < RailwayMain.passengerList.size(); i++) {

	                            Passenger p = RailwayMain.passengerList.get(i);

	                            if (p.email.equalsIgnoreCase(email)) {

	                                passengerId = p.id;
	                                break;
	                            }
	                        }
	                    }    
	                    
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

	                    RailwayMain.userList.add(user);
	                }
	            }

	            br.close();

	        }
	        catch (FileNotFoundException e) {

	            // File does not exist yet
	            // It will be created during registration

	        }
	        catch (IOException e) {

	            System.out.println("Error while loading users.");
	        }
	    }
	    
	    
	    // ================= SAVE USER =================

	    static void saveUser(User user) {

	        try {

	            FileWriter fw = new FileWriter("users.txt", true);
	            BufferedWriter bw = new BufferedWriter(fw);

	            bw.write("======================================");
	            bw.newLine();
	            bw.write("User ID       : " + user.userId);
	            bw.newLine();
	            bw.write("Name          : " + user.name);
	            bw.newLine();
	            bw.write("Age           : " + user.age);
	            bw.newLine();
	            bw.write("Mobile        : " + user.mobile);
	            bw.newLine();
	            bw.write("Email         : " + user.email);
	            bw.newLine();
	            bw.write("Username      : " + user.username);
	            bw.newLine();
	            bw.write("Passenger ID  : " + user.passengerId);
	            bw.newLine();
	            bw.write("Password      : " + user.password);
	            bw.newLine();
	            bw.write("======================================");
	            bw.newLine();
	            bw.newLine();

	            bw.close();

	            System.out.println("User information saved to users.txt");

	        } catch (IOException e) {

	            System.out.println("Error while saving user.");
	        }
	    }
	    			 // ================= SAVE ALL USERS =================

	    static void saveAllUsers() {

	        try {

	            FileWriter fw =
	                    new FileWriter("users.txt", false);

	            BufferedWriter bw =
	                    new BufferedWriter(fw);

	            for (int i = 0; i < RailwayMain.userList.size(); i++) {

	                User user = RailwayMain.userList.get(i);

	                bw.write("======================================");
	                bw.newLine();

	                bw.write("User ID       : " + user.userId);
	                bw.newLine();

	                bw.write("Name          : " + user.name);
	                bw.newLine();

	                bw.write("Age           : " + user.age);
	                bw.newLine();

	                bw.write("Mobile        : " + user.mobile);
	                bw.newLine();

	                bw.write("Email         : " + user.email);
	                bw.newLine();

	                bw.write("Username      : " + user.username);
	                bw.newLine();
	                
	                bw.write("Passenger ID  : " + user.passengerId);
	                bw.newLine();

	                bw.write("Password      : " + user.password);
	                bw.newLine();

	                bw.write("======================================");
	                bw.newLine();
	                bw.newLine();
	            }

	            bw.close();

	            System.out.println("User information updated in users.txt");

	        }
	        catch (IOException e) {

	            System.out.println("Error while updating user data.");
	        }
	    }

	 // ================= LOAD TRAINS =================

	    static void loadTrains() {

	        try {

	            BufferedReader br =
	                    new BufferedReader(
	                            new FileReader("train.txt"));

	            String line;

	            int trainNumber = 0;
	            String trainName = "";
	            String source = "";
	            String destination = "";

	            int slSeats = 0;
	            int availableSlSeats = 0;

	            int ac3Seats = 0;
	            int availableAc3Seats = 0;

	            int ac2Seats = 0;
	            int availableAc2Seats = 0;

	            int totalSeats = 0;
	            int availableSeats = 0;

	            int slFare = 0;
	            int ac3Fare = 0;
	            int ac2Fare = 0;
	            

	            while ((line = br.readLine()) != null) {

	                if (line.startsWith("Train Number    : ")) {

	                    trainNumber =
	                            Integer.parseInt(
	                                    line.substring(18).trim());
	                }

	                else if (line.startsWith("Train Name      : ")) {

	                    trainName =
	                            line.substring(18).trim();
	                }

	                else if (line.startsWith("Source          : ")) {

	                    source =
	                            line.substring(18).trim();
	                }

	                else if (line.startsWith("Destination     : ")) {

	                    destination =
	                            line.substring(18).trim();
	                }

	                else if (line.startsWith("SL Seats        : ")) {

	                    slSeats =
	                            Integer.parseInt(
	                                    line.substring(18).trim());
	                }

	                else if (line.startsWith("Available SL    : ")) {

	                    availableSlSeats =
	                            Integer.parseInt(
	                                    line.substring(18).trim());
	                }

	                else if (line.startsWith("3AC Seats       : ")) {

	                    ac3Seats =
	                            Integer.parseInt(
	                                    line.substring(18).trim());
	                }

	                else if (line.startsWith("Available 3AC   : ")) {

	                    availableAc3Seats =
	                            Integer.parseInt(
	                                    line.substring(18).trim());
	                }

	                else if (line.startsWith("2AC Seats       : ")) {

	                    ac2Seats =
	                            Integer.parseInt(
	                                    line.substring(18).trim());
	                }

	                else if (line.startsWith("Available 2AC   : ")) {

	                    availableAc2Seats =
	                            Integer.parseInt(
	                                    line.substring(18).trim());
	                }

	                else if (line.startsWith("Total Seats     : ")) {

	                    totalSeats =
	                            Integer.parseInt(
	                                    line.substring(18).trim());
	                }

	                else if (line.startsWith("Available Seats : ")) {

	                    availableSeats =
	                            Integer.parseInt(
	                                    line.substring(18).trim());
	                }

	                else if (line.startsWith("SL Fare         : ")) {

	                    slFare = Integer.parseInt(
	                            line.substring(18)
	                                    .replace("Rs.", "")
	                                    .trim());
	                }

	                else if (line.startsWith("3AC Fare        : ")) {

	                    ac3Fare = Integer.parseInt(
	                            line.substring(18)
	                                    .replace("Rs.", "")
	                                    .trim());
	                }

	                else if (line.startsWith("2AC Fare        : ")) {

	                    ac2Fare = Integer.parseInt(
	                            line.substring(18)
	                                    .replace("Rs.", "")
	                                    .trim());

	                
	                    Train train = new Train(
	                            trainNumber,
	                            trainName,
	                            source,
	                            destination,
	                            slSeats,
	                            ac3Seats,
	                            ac2Seats,
	                            slFare,
	                            ac3Fare,
	                            ac2Fare
	                    );

	                    train.availableSlSeats = availableSlSeats;
	                    train.availableAc3Seats = availableAc3Seats;
	                    train.availableAc2Seats = availableAc2Seats;

	                    train.totalSeats = totalSeats;
	                    train.availableSeats = availableSeats;

	                    RailwayMain.trainList.add(train);
	                }
	            }

	            br.close();

	        }

	        catch (FileNotFoundException e) {

	            // First time running the program

	        }

	        catch (IOException e) {

	            System.out.println(
	                    "Error while loading trains.");
	        }
	    }
	    
	 // ================= SAVE TRAIN =================

	    static void saveTrain(Train train) {

	        try {

	            FileWriter fw = new FileWriter("train.txt", true);
	            BufferedWriter bw = new BufferedWriter(fw);

	            bw.write("======================================");
	            bw.newLine();

	            bw.write("Train Number    : " + train.trainNumber);
	            bw.newLine();

	            bw.write("Train Name      : " + train.trainName);
	            bw.newLine();

	            bw.write("Source          : " + train.source);
	            bw.newLine();

	            bw.write("Destination     : " + train.destination);
	            bw.newLine();

	            bw.write("SL Seats        : " + train.slSeats);
	            bw.newLine();

	            bw.write("Available SL    : " + train.availableSlSeats);
	            bw.newLine();

	            bw.write("3AC Seats       : " + train.ac3Seats);
	            bw.newLine();

	            bw.write("Available 3AC   : " + train.availableAc3Seats);
	            bw.newLine();

	            bw.write("2AC Seats       : " + train.ac2Seats);
	            bw.newLine();

	            bw.write("Available 2AC   : " + train.availableAc2Seats);
	            bw.newLine();

	            bw.write("Total Seats     : " + train.totalSeats);
	            bw.newLine();

	            bw.write("Available Seats : " + train.availableSeats);
	            bw.newLine();

	            bw.write("SL Fare         : Rs." + train.slFare);
	            bw.newLine();

	            bw.write("3AC Fare        : Rs." + train.ac3Fare);
	            bw.newLine();

	            bw.write("2AC Fare        : Rs." + train.ac2Fare);
	            bw.newLine();
	            
	            bw.write("======================================");
	            bw.newLine();
	            bw.newLine();

	            bw.close();

	        }
	        catch (IOException e) {

	            System.out.println("Error while saving train.");
	        }
	    }

	 // ================= SAVE ALL TRAINS =================

	    static void saveAllTrains() {

	        try {

	            FileWriter fw =
	                    new FileWriter("train.txt", false);

	            BufferedWriter bw =
	                    new BufferedWriter(fw);

	            for (int i = 0; i < RailwayMain.trainList.size(); i++) {

	                Train train = RailwayMain.trainList.get(i);

	                bw.write("======================================");
	                bw.newLine();

	                bw.write("Train Number    : " + train.trainNumber);
	                bw.newLine();

	                bw.write("Train Name      : " + train.trainName);
	                bw.newLine();

	                bw.write("Source          : " + train.source);
	                bw.newLine();

	                bw.write("Destination     : " + train.destination);
	                bw.newLine();

	                bw.write("SL Seats        : " + train.slSeats);
	                bw.newLine();

	                bw.write("Available SL    : " + train.availableSlSeats);
	                bw.newLine();

	                bw.write("3AC Seats       : " + train.ac3Seats);
	                bw.newLine();

	                bw.write("Available 3AC   : " + train.availableAc3Seats);
	                bw.newLine();

	                bw.write("2AC Seats       : " + train.ac2Seats);
	                bw.newLine();

	                bw.write("Available 2AC   : " + train.availableAc2Seats);
	                bw.newLine();

	                bw.write("Total Seats     : " + train.totalSeats);
	                bw.newLine();

	                bw.write("Available Seats : " + train.availableSeats);
	                bw.newLine();

	                bw.write("SL Fare         : Rs." + train.slFare);
	                bw.newLine();

	                bw.write("3AC Fare        : Rs." + train.ac3Fare);
	                bw.newLine();

	                bw.write("2AC Fare        : Rs." + train.ac2Fare);
	                bw.newLine();

	                bw.write("======================================");
	                bw.newLine();
	                bw.newLine();
	            }

	            bw.close();

	        }
	        catch (IOException e) {

	            System.out.println("Error while updating train data.");
	        }
	    }
	 
		// ================= LOAD BOOKINGS =================

		static void loadBookings() {

		    try {

		        BufferedReader br =
		                new BufferedReader(
		                        new FileReader("booking.txt"));

		        String line;

		        int pnr = 0;
		        int passengerId = 0;
		        int trainNumber = 0;
		        String journeyDate = "";
		        String seatType = "";
		        int seatNumber = 0;
		        String bookingType = "";

		        while ((line = br.readLine()) != null) {

		            if (line.startsWith("PNR Number    : ")) {

		                pnr = Integer.parseInt(
		                        line.substring(15).trim());
		            }

		            else if (line.startsWith("Passenger ID   : ")) {

		                passengerId = Integer.parseInt(
		                        line.substring(17).trim());
		            }

		            else if (line.startsWith("Train No       : ")) {

		                trainNumber = Integer.parseInt(
		                        line.substring(17).trim());
		            }

		            else if (line.startsWith("Journey Date   : ")) {

		                journeyDate =
		                        line.substring(17).trim();
		            }

		            else if (line.startsWith("Class / Seat   : ")) {

		                seatType =
		                        line.substring(17).trim();
		            }

		            else if (line.startsWith("Seat Number    : ")) {

		                seatNumber = Integer.parseInt(
		                        line.substring(17).trim());
		            }

		            else if (line.startsWith("Booking Type   : ")) {

		                bookingType =
		                        line.substring(17).trim();

		                // Find passenger
		                Passenger passenger =
		                        RailwayMain.findPassengerById(passengerId);

		                // Find train
		                Train train =
		                        RailwayMain.findTrainByNumber(trainNumber);

		                // Create booking only if both exist
		                if (passenger != null && train != null) {

		                    Booking booking =
		                            new Booking(
		                                    pnr,
		                                    passenger,
		                                    train,
		                                    journeyDate,
		                                    seatType,
		                                    seatNumber,
		                                    bookingType
		                            );

		                    RailwayMain.bookingList.add(booking);

		                    // Update next PNR
		                    if (pnr >= RailwayMain.nextPNR) {
		                        RailwayMain.nextPNR = pnr + 1;
		                    }
		                }
		            }
		        }

		        br.close();

		    }
		    catch (FileNotFoundException e) {

		        // First time running the program

		    }
		    catch (IOException e) {

		        System.out.println("Error while loading bookings.");
		    }
		}

	    //================SAVE BOOKING DATA================
	    
		static void saveBookingData(Booking booking) {

		    try {

		        FileWriter fw =
		                new FileWriter("booking.txt",true);

		        BufferedWriter bw =
		                new BufferedWriter(fw);

		        Booking b = booking;

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
		        

		        bw.close();
		        fw.close();

		        System.out.println("Ticket saved to booking.txt");

		    }
		    catch (IOException e) {

		        System.out.println("Error while saving ticket.");
		    }
		}
		


	    
	    
	}


