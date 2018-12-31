import ExhibitionCenter.User;
import ExhibitionCenter.Utils_vdm;
import ExhibitionCenter.quotes.ConferenceQuote;


import ExhibitionCenter.Auditorium;
import ExhibitionCenter.CarParking;
import ExhibitionCenter.Center;
import ExhibitionCenter.Foyer;
import ExhibitionCenter.Installation;
import ExhibitionCenter.Pavilion;
import ExhibitionCenter.Room;

public class Main {

	public static void main(String[] args) {
		User admin = new User("admin", "admin1234");
		Installation pavilion1 = new Pavilion("Pavilion1", 500, 150, 30, 120.5, 145.6, false, true, true, false, true);
		Installation pavilion2 = new Pavilion("Pavilion2", 400, 70, 15, 100, 150, true, false, true, false, true);
		Installation auditorium1 = new Auditorium("Auditorium1", 500, 120, 7, 50, 67.7, true, true, true, false, true, true);
		Installation auditorium2 = new Auditorium("Auditorium2", 350, 70, 5, 40, 50, true, false, true, true, true, true);
		Installation parking1 = new CarParking("Auditorium2", 200, 70, 4, 100, 120);
		Installation foyer1 = new Foyer("Foyer1", 70, 15, 5, 50, 20, false, true, true, false);
		Installation foyer2 = new Foyer("Foyer2", 20, 5, 5, 10, 10, false, false, true, false);
		Installation foyer3 = new Foyer("Foyer3", 80, 20, 4, 50, 25.6, true, true, true, true);
		Installation room1 = new Room("Room1", 20, 15, 4, 10, 10, false, true, false, false, false, false, false, false);
		Installation room2 = new Room("Room2", 30, 25, 4, 15, 15, true, true, true, false, true, true, false, true);
		Installation room3 = new Room("Room3", 35.6, 20, 4, 10, 15.6, true, true, true, true, true, true, true, false);
		
		Center center = new Center("My Exhibition Center", pavilion1);
		center.addUser(admin);
		center.addInstallations("admin", pavilion2);
		center.addInstallations("admin", auditorium1);
		center.addInstallations("admin", auditorium2);
		center.addInstallations("admin", parking1);
		center.addInstallations("admin", foyer1);
		center.addInstallations("admin", foyer2);
		center.addInstallations("admin", foyer3);
		center.addInstallations("admin", room1);
		center.addInstallations("admin", room2);
		center.addInstallations("admin", room3);
		
		center.addInstallationToInstallation("admin", "Pavilion1", foyer1);
		center.addInstallationToInstallation("admin", "Auditorium1", room2);
		
		User user1 = new User("User1", "1234");
		User user2 = new User("User2", "1234");
		center.addUser(user1);
		center.addUser(user2);
		center.createEvent("Event Test", 4, 5, new Utils_vdm.Date(2019, 1, 1), new Utils_vdm.Date(2019, 1, 7), true, new ConferenceQuote(), pavilion1, "User1");
		
		CommandLineInterface cli = new CommandLineInterface(center);
        cli.mainMenu();
	}
	
}
