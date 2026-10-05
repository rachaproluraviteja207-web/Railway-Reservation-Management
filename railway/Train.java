package railway;

public class Train {
	
	    int trainNumber;
	    String trainName;
	    String source;
	    String destination;

	    int totalSeats;
	    int availableSeats;

	    int slSeats;
	    int ac3Seats;
	    int ac2Seats;

	    int availableSlSeats;
	    int availableAc3Seats;
	    int availableAc2Seats;

	    int slFare;
	    int ac3Fare;
	    int ac2Fare;

	    Train(int trainNumber, String trainName, String source,
	          String destination, int slSeats, int ac3Seats,
	          int ac2Seats, int slFare, int ac3Fare, int ac2Fare) {

	        this.trainNumber = trainNumber;
	        this.trainName = trainName;
	        this.source = source;
	        this.destination = destination;

	        this.slSeats = slSeats;
	        this.ac3Seats = ac3Seats;
	        this.ac2Seats = ac2Seats;

	        this.availableSlSeats = slSeats;
	        this.availableAc3Seats = ac3Seats;
	        this.availableAc2Seats = ac2Seats;

	        this.totalSeats = slSeats + ac3Seats + ac2Seats;
	        this.availableSeats = this.totalSeats;

	        this.slFare = slFare;
	        this.ac3Fare = ac3Fare;
	        this.ac2Fare = ac2Fare;
	    }

	    void show() {

	        System.out.println("----------------------------------");
	        System.out.println("Train Number       : " + trainNumber);
	        System.out.println("Train Name         : " + trainName);
	        System.out.println("Source             : " + source);
	        System.out.println("Destination        : " + destination);
	        System.out.println("Sleeper Seats      : " + slSeats);
	        System.out.println("Available SL Seats : " + availableSlSeats);
	        System.out.println("3AC Seats          : " + ac3Seats);
	        System.out.println("Available 3AC      : " + availableAc3Seats);
	        System.out.println("2AC Seats          : " + ac2Seats);
	        System.out.println("Available 2AC      : " + availableAc2Seats);
	        System.out.println("Total Seats        : " + totalSeats);
	        System.out.println("Available Seats    : " + availableSeats);
	        System.out.println("SL Fare            : Rs." + slFare);
	        System.out.println("3AC Fare           : Rs." + ac3Fare);
	        System.out.println("2AC Fare           : Rs." + ac2Fare);
	        System.out.println("----------------------------------");
	    }
	}

