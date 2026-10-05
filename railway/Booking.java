package railway;

public class Booking {

	    int pnr;
	    Passenger passenger;
	    Train train;
	    String journeyDate;
	    String seatType;
	    int seatNumber;
	    String bookingType;
	    int totalFare;

	    Booking(int pnr, Passenger passenger, Train train,
	            String journeyDate, String seatType, int seatNumber,
	            String bookingType) {

	        this.pnr = pnr;
	        this.passenger = passenger;
	        this.train = train;
	        this.journeyDate = journeyDate;
	        this.seatType = seatType;
	        this.seatNumber = seatNumber;
	        this.bookingType = bookingType;

	        calculateFare();
	    }

	    void calculateFare() {

	        if (seatType.equalsIgnoreCase("2AC")) {

	            totalFare = train.ac2Fare;

	        } else if (seatType.equalsIgnoreCase("3AC")) {

	            totalFare = train.ac3Fare;

	        } else if (seatType.equalsIgnoreCase("SL")) {

	            totalFare = train.slFare;
	        }

	        if (bookingType.equalsIgnoreCase("Tatkal")) {

	            totalFare = totalFare + 200;
	        }
	    }

	    void displayTicket() {

	        System.out.println("\n======================================");
	        System.out.println("          INDIAN RAILWAY TICKET");
	        System.out.println("======================================");

	        System.out.println("PNR Number    : " + pnr);

	        System.out.println("\nPassenger Details");
	        System.out.println("Passenger ID   : " + passenger.id);
	        System.out.println("Passenger Name : " + passenger.name);
	        System.out.println("Age            : " + passenger.age);
	        System.out.println("Gender         : " + passenger.gender);
	        System.out.println("Phone Number   : " + passenger.contact);
	        System.out.println("Email          : " + passenger.email);

	        System.out.println("\nTrain Details");
	        System.out.println("Train No       : " + train.trainNumber);
	        System.out.println("Train Name     : " + train.trainName);
	        System.out.println("From           : " + train.source);
	        System.out.println("To             : " + train.destination);
	        System.out.println("Available Seats: " + train.availableSeats);

	        System.out.println("\nJourney Date   : " + journeyDate);
	        System.out.println("Class / Seat   : " + seatType);
	        System.out.println("Seat Number    : " + seatNumber);
	        System.out.println("Booking Type   : " + bookingType);

	        System.out.println("--------------------------------------");
	        System.out.println("Total Fare     : Rs." + totalFare);
	        System.out.println("Booking Status : CONFIRMED");

	        System.out.println("======================================");
	        System.out.println("        HAVE A SAFE JOURNEY!");
	        System.out.println("======================================");
	    }
	}


