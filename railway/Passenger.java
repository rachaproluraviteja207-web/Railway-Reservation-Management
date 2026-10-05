package railway;

public class Passenger {
	
			    int id;
			    String name;
			    int age;
			    String gender;
			    String contact;
			    String email;
			    String address;

			    Passenger(int id, String name, int age, String gender,
			              String contact, String email, String address) {

			        this.id = id;
			        this.name = name;
			        this.age = age;
			        this.gender = gender;
			        this.contact = contact;
			        this.email = email;
			        this.address = address;
			    }

			    void show() {

			        System.out.println("----------------------------------");
			        System.out.println("Passenger ID   : " + id);
			        System.out.println("Name           : " + name);
			        System.out.println("Age            : " + age);
			        System.out.println("Gender         : " + gender);
			        System.out.println("Contact Number : " + contact);
			        System.out.println("Email          : " + email);
			        System.out.println("Address        : " + address);
			        System.out.println("----------------------------------");
			    }

		}


