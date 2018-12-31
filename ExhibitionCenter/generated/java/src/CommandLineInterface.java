import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;

import org.overture.codegen.runtime.MapUtil;
import org.overture.codegen.runtime.Utils;
import org.overture.codegen.runtime.VDMMap;
import org.overture.codegen.runtime.VDMSet;

import ExhibitionCenter.Center;
import ExhibitionCenter.Event;
import ExhibitionCenter.User;
import ExhibitionCenter.Utils_vdm;
import ExhibitionCenter.quotes.*;

public class CommandLineInterface {
	
	private static final int EMPTY_LINES = 10;
	private Scanner reader = new Scanner(System.in);
	private Center center;
	private String userName = null;
	
	CommandLineInterface(Center center) {
        this.center = center;
    }
	
	private void printLine() {
        System.out.println("=====================================");
    }
	
	private void printEmptyLines(int linesToPrint) {
        for (int i = 0; i < linesToPrint; i++) {
            System.out.println();
        }
    }
	
	/**
	 * Display menu entry ==> number : entry name
	 * @param menuEntries
	 */
	private void printMenuEntries(ArrayList<SimpleEntry<String, Callable<Void>>> menuEntries) {
        for (int i = 0; i < menuEntries.size(); i++) {
            System.out.println((i + 1) + ": " + menuEntries.get(i).getKey());
        }
    }
	
	/**
	 * Waits for user input that is a number that corresponds to one menu entry
	 * @param bottomBound
	 * @param upperBound
	 * @return
	 */
	private int getUserInput(int bottomBound, int upperBound) {
        System.out.print("Choose an option: ");
        int option = Integer.parseInt(reader.nextLine());

        if (option < bottomBound && option > upperBound) {
            System.out.println("Invalid option");
            option = getUserInput(bottomBound, upperBound);
        }

        return option - 1;
    }
	
	public boolean isLoggedIn() {
		return userName != null;
	}

	/**
	 * Create Main Menu
	 */
    public void mainMenu() {
        printLine();
        System.out.println("Welcome to " + center.name);
        ArrayList<SimpleEntry<String, Callable<Void>>> mainMenuEntries = new ArrayList<>();

        while (true) {
            mainMenuEntries.clear();
            addMainMenuEntries(mainMenuEntries);
            printMenuEntries(mainMenuEntries);
            int option = getUserInput(1, mainMenuEntries.size() - 1);

            try {
                mainMenuEntries.get(option).getValue().call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Add entries to the Main Menu: Create Account, Login and Exit
     * @param mainMenuEntries
     */
    private void addMainMenuEntries(ArrayList<SimpleEntry<String, Callable<Void>>> mainMenuEntries) {
        if (!isLoggedIn()) {
            mainMenuEntries.add(new SimpleEntry<>("Create Account", () -> {
                createAccountMenu();
                return null;
            }));
            mainMenuEntries.add(new SimpleEntry<>("Login", () -> {
                loginMenu();
                return null;
            }));
        } else {
            mainMenuEntries.add(new SimpleEntry<>("Logout", () -> {
                this.userName = null;
                loginMenu();
                return null;
            }));
        }

        mainMenuEntries.add(new SimpleEntry<>("Exit", () -> {
            System.exit(0);
            return null;
        }));
    }
    
    /**
     * Action triggered when selecting entry Create Account
     */
    private void createAccountMenu() {
        printEmptyLines(EMPTY_LINES);
        printLine();
        System.out.println("Create account menu");
        System.out.print("Name: ");
        String name = reader.nextLine();
        System.out.print("Password: ");
        String password = reader.nextLine();
        center.addUser(new User(name, password));
        printEmptyLines(EMPTY_LINES);
    }
    
    /**
     * Action triggered when selecting entry Login
     */
    private void loginMenu() {
        printEmptyLines(EMPTY_LINES);
        printLine();
        System.out.println("Login menu");
        System.out.print("Name: ");
        String name = reader.nextLine();
        System.out.print("Password: ");
        String password = reader.nextLine();
        if (((User) Utils.get(center.users, name)).password.compareTo(password) == 0) {
        	this.userName = name;
            loggedInMenu();
        } else {
            printEmptyLines(EMPTY_LINES);
            System.out.println("Incorrect name, password combination. Please try again.");
        }
        printEmptyLines(EMPTY_LINES);
    }
    
    /**
     * After user is logged in
     */
    private void loggedInMenu() {
        printEmptyLines(EMPTY_LINES);
        printLine();
        System.out.println("Welcome " + userName + '\n');
        ArrayList<SimpleEntry<String, Callable<Void>>> loggedInMenuEntries = new ArrayList<>();

        while (true) {
            loggedInMenuEntries.clear();
            addLoggedInMenuEntries(loggedInMenuEntries);
            printMenuEntries(loggedInMenuEntries);
            int option = getUserInput(1, loggedInMenuEntries.size() - 1);

            try {
                loggedInMenuEntries.get(option).getValue().call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Add entries Users, Events, Installations, Service and Logout
     * to the menu that is shown after loggin in
     * @param loggedInMenuEntries
     */
    private void addLoggedInMenuEntries(ArrayList<SimpleEntry<String, Callable<Void>>> loggedInMenuEntries) {
        if (isLoggedIn()) {
            if(userName.compareTo("admin") == 0) {
            	loggedInMenuEntries.add(new SimpleEntry<>("Users", () -> {
                    usersMenu();
                    return null;
                }));
            } else {
            	loggedInMenuEntries.add(new SimpleEntry<>("My Profile", () -> {
                    usersMenu();
                    return null;
                }));
            }
            	
            loggedInMenuEntries.add(new SimpleEntry<>("Events", () -> {
                eventsMenu();
                return null;
            }));
            loggedInMenuEntries.add(new SimpleEntry<>("Installations", () -> {
                //installationsMenu();
                return null;
            }));
            loggedInMenuEntries.add(new SimpleEntry<>("Services", () -> {
                //servicesMenu();
                return null;
            }));
            loggedInMenuEntries.add(new SimpleEntry<>("Logout", () -> {
                this.userName = null;
                printEmptyLines(EMPTY_LINES);
                mainMenu();
                return null;
            }));
        } else {
            mainMenu();
        }
    }
    /**
     * When selecting menu Users
     * If we are admin, all users' names are listed and two other entries,
     * Show User Details and Main Menu, are added.
     * If we are a regular user, our profile (details) are shown
     */
    private void usersMenu() {
        printEmptyLines(EMPTY_LINES);
        printLine();
        if(userName.compareTo("admin") == 0) {
        	System.out.println("All Users");
        	Iterator<String> it = MapUtil.dom(center.users).iterator();
        	while(it.hasNext()) {
        		String user = (String) it.next();
        		System.out.println(user);
        	}
        	System.out.println();
        	
            ArrayList<SimpleEntry<String, Callable<Void>>> usersMenuEntries = new ArrayList<>();

            while (true) {
            	usersMenuEntries.clear();
            	usersMenuEntries.add(new SimpleEntry<>("Show User Details", () -> {
                    viewUsersDetailsMenu();
                    return null;
                }));
            	usersMenuEntries.add(new SimpleEntry<>("Main Menu", () -> {
                    loggedInMenu();
                    return null;
                }));
            	
                printMenuEntries(usersMenuEntries);
                int option = getUserInput(1, usersMenuEntries.size() - 1);

                try {
                	usersMenuEntries.get(option).getValue().call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
        	viewUsersDetailsMenu();
        }
        
    }
    
    /**
     * Display details of one specific user
     */
    private void viewUsersDetailsMenu() {
    	
    	String name = userName;
    	
    	if(userName.compareTo("admin") == 0) {
    		printEmptyLines(EMPTY_LINES);
        	printLine();
    		System.out.print("User name: ");
            name = reader.nextLine();
    	}
        
        try {
        	VDMMap userDetails = center.showUserDetails(userName, name);
        	Iterator it = userDetails.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println("  " + pair.getKey() + ": " + pair.getValue());
                it.remove(); // avoids a ConcurrentModificationException
            }
        } catch (IllegalArgumentException e) {
        	System.out.println("There is no user " + name + ". Try again...");
        }
    	reader.nextLine();
    	printEmptyLines(EMPTY_LINES);
    }
    
    /**
     * Add entries to menu Events
     */
    private void eventsMenu() {
    	printEmptyLines(EMPTY_LINES);
        printLine();
        
    	ArrayList<SimpleEntry<String, Callable<Void>>> eventsMenuEntries = new ArrayList<>();
    	while (true) {
    		eventsMenuEntries.clear();
            addEventsMenuEntries(eventsMenuEntries);
            printMenuEntries(eventsMenuEntries);
            int option = getUserInput(1, eventsMenuEntries.size() - 1);

            try {
            	eventsMenuEntries.get(option).getValue().call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * The entries Show All Events, Show All Available Events, 
     * Show All Available Events Between Two Dates
     * and Main Menu are added to menu Events 
     * @param eventsMenuEntries
     */
    private void addEventsMenuEntries(ArrayList<SimpleEntry<String, Callable<Void>>> eventsMenuEntries) {
    	eventsMenuEntries.add(new SimpleEntry<>("Show All Events", () -> {
            showEventsMenu("all");
            return null;
        }));
    	
    	eventsMenuEntries.add(new SimpleEntry<>("Show All Available Events", () -> {
            showEventsMenu("all");
            return null;
        }));
    	
    	eventsMenuEntries.add(new SimpleEntry<>("Show All Available Events Between Two Dates", () -> {
            showEventsMenu("all");
            return null;
        }));
    	
    	eventsMenuEntries.add(new SimpleEntry<>("Main Menu", () -> {
            loggedInMenu();
            return null;
        }));
    }
    
    /**
     * The name of events, selected by a specific condition,
     * are listed and two entries are added: Select Event and Main Menu
     * @param range
     */
    private void showEventsMenu(String range) {
    	printEmptyLines(EMPTY_LINES);
        printLine();
        
        VDMSet events = null;
        if(range.compareTo("all") == 0) {
        	events = center.listEvents(userName);
        }
        if(events.size() == 0) {
        	System.out.println("There is no event yet");
        	System.out.println();
        } else {
        	System.out.println("All Events");
        	Iterator<Event> it = events.iterator();
        	while(it.hasNext()) {
        		String event = ((Event) it.next()).name;
        		System.out.println(event);
        	}
        	System.out.println();
        }
        
    	
    	ArrayList<SimpleEntry<String, Callable<Void>>> showEventsMenuEntries = new ArrayList<>();
        while (true) {
        	showEventsMenuEntries.clear();
        	showEventsMenuEntries.add(new SimpleEntry<>("Select Event", () -> {
        		selectEventMenu();
                return null;
            }));
        	showEventsMenuEntries.add(new SimpleEntry<>("Main Menu", () -> {
                loggedInMenu();
                return null;
            }));
            printMenuEntries(showEventsMenuEntries);
            int option = getUserInput(1, showEventsMenuEntries.size() - 1);

            try {
            	showEventsMenuEntries.get(option).getValue().call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    	
    }
    
    /**
     * Menu where user is asked to select an event.
     * After that, more entries, related to the event selected,
     * are added to the menu
     */
    private void selectEventMenu() {
    	printEmptyLines(EMPTY_LINES);
    	printLine();
    	
		System.out.print("Event name: ");
        String event = reader.nextLine();
        if(!center.events.containsKey(event)) {
        	System.out.println("There is no event " + event + ". Try again...");
        	System.out.println();
        	return;
        }
        
        ArrayList<SimpleEntry<String, Callable<Void>>> selectedEventMenuEntries = new ArrayList<>();
        while (true) {
        	selectedEventMenuEntries.clear();
            addSelectedEventMenuEntries(selectedEventMenuEntries, event);
            printMenuEntries(selectedEventMenuEntries);
            int option = getUserInput(1, selectedEventMenuEntries.size() - 1);

            try {
            	selectedEventMenuEntries.get(option).getValue().call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Menu with option related to a specific event.
     * Add entries
     * @param selectedEventMenuEntries
     * @param event
     */
    private void addSelectedEventMenuEntries(ArrayList<SimpleEntry<String, Callable<Void>>> selectedEventMenuEntries, String eventName) {
    	Event event = (Event) center.events.get(eventName);
    	
    	selectedEventMenuEntries.add(new SimpleEntry<>("Show Event Details", () -> {
            viewEventDetails(eventName);
            return null;
        }));
    	if(event.attendees.contains(userName)) {
    		selectedEventMenuEntries.add(new SimpleEntry<>("Buy Ticket", () -> {
                return null;
            }));
    	}
    	if(userName.compareTo(event.host) == 0 || userName.compareTo("admin") == 0) {
    		selectedEventMenuEntries.add(new SimpleEntry<>("Show Money Spent", () -> {
                return null;
            }));
        	selectedEventMenuEntries.add(new SimpleEntry<>("Show Participants", () -> {
                return null;
            }));
    	}
    	if(userName.compareTo(event.host) == 0) {
    		selectedEventMenuEntries.add(new SimpleEntry<>("Edit Event", () -> {
    			editEventMenu(eventName);
                return null;
            }));
    	}
    	
    	selectedEventMenuEntries.add(new SimpleEntry<>("Main Menu", () -> {
            loggedInMenu();
            return null;
        }));
    }
    
    /**
     * Display an event's details
     * @param event
     */
    private void viewEventDetails(String event) {
    	printEmptyLines(EMPTY_LINES);
    	printLine();
    	
    	try {
        	VDMMap eventDetails = center.showEventDetails(userName, event);
        	Iterator it = eventDetails.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println("  " + pair.getKey() + ": " + pair.getValue());
                it.remove(); // avoids a ConcurrentModificationException
            }
        } catch (IllegalArgumentException e) {
        	System.out.println("There is no event " + event + ". Try again...");
        }
    	
    	reader.nextLine();
    	printEmptyLines(EMPTY_LINES);
    }
    
    
    private void editEventMenu(String event) {
    	printEmptyLines(EMPTY_LINES);
        printLine();
        
    	ArrayList<SimpleEntry<String, Callable<Void>>> editEventMenuEntries = new ArrayList<>();
        while (true) {
        	editEventMenuEntries.clear();
        	addEditEventEntries(editEventMenuEntries, event);
            printMenuEntries(editEventMenuEntries);
            int option = getUserInput(1, editEventMenuEntries.size() - 1);

            try {
            	editEventMenuEntries.get(option).getValue().call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void addEditEventEntries(ArrayList<SimpleEntry<String, Callable<Void>>> editEventMenuEntries, String eventName) {
    	Event event = (Event) center.events.get(eventName);
    	
    	editEventMenuEntries.add(new SimpleEntry<>("Change Name", () -> {
    		changeEventName(eventName);
            return null;
        }));
    	editEventMenuEntries.add(new SimpleEntry<>("Change Number Of Total Tickets", () -> {
    		changeEventTotalTickets(eventName);
            return null;
        }));
    	editEventMenuEntries.add(new SimpleEntry<>("Change Ticket's Price", () -> {
    		changeEventTicketPrice(eventName);
            return null;
        }));
    	editEventMenuEntries.add(new SimpleEntry<>("Change Dates", () -> {
    		changeEventDates(eventName);
            return null;
        }));
    	String privacy = "Turn Private";
    	if(!event.privacy) {
    		privacy = "Turn Public";
    	}
    	editEventMenuEntries.add(new SimpleEntry<>(privacy, () -> {
    		if(event.privacy) {
    			center.changeEventPrivacy(eventName, userName, false);
    		} else {
    			center.changeEventPrivacy(eventName, userName, true);
    		}
            return null;
        }));
    	editEventMenuEntries.add(new SimpleEntry<>("Change Event Type", () -> {
    		changeEventType(eventName);
            return null;
        }));
    	editEventMenuEntries.add(new SimpleEntry<>("Change Intallation", () -> {
    		
            return null;
        }));
    	editEventMenuEntries.add(new SimpleEntry<>("Add Service", () -> {
    		addServiceToEvent(eventName);
            return null;
        }));
    	editEventMenuEntries.add(new SimpleEntry<>("Remove Service", () -> {
    		removeServiceFromEvent(eventName);
            return null;
        }));
    	editEventMenuEntries.add(new SimpleEntry<>("Remove Participant", () -> {
    		
            return null;
        }));
    	editEventMenuEntries.add(new SimpleEntry<>("Invite User", () -> {
    		inviteToEvent(eventName);
            return null;
        }));
    	editEventMenuEntries.add(new SimpleEntry<>("Main Menu", () -> {
            loggedInMenu();
            return null;
        }));
    }

    private void changeEventName(String eventName) {
    	printEmptyLines(EMPTY_LINES);
    	printLine();
    	
		System.out.print("New event name: ");
        String newEventName = reader.nextLine();
        center.changeEventName(eventName, userName, newEventName);
        
        printEmptyLines(EMPTY_LINES);
    }
    
    private void changeEventTotalTickets(String eventName) {
    	printEmptyLines(EMPTY_LINES);
    	printLine();
    	
		System.out.print("New number of tickets: ");
        Integer newTotalTickets = Integer.parseInt(reader.nextLine());
        center.changeEventTotalTickets(eventName, userName, newTotalTickets);
        
        printEmptyLines(EMPTY_LINES);
    }
    
    private void changeEventTicketPrice(String eventName) {
    	printEmptyLines(EMPTY_LINES);
    	printLine();
    	
		System.out.print("New ticket price: ");
        Float newTicketPrice = Float.parseFloat(reader.nextLine());
        center.changeEventTicketPrice(eventName, userName, newTicketPrice);
        
        printEmptyLines(EMPTY_LINES);
    }
    
    private void changeEventDates(String eventName) {
    	printEmptyLines(EMPTY_LINES);
    	printLine();
    	
		System.out.print("New starting date\n\n");
		System.out.print("  Day: ");
        Float bDay = Float.parseFloat(reader.nextLine());
        System.out.print("  Month: ");
        Float bMonth = Float.parseFloat(reader.nextLine());
        System.out.print("  Year: ");
        Float bYear = Float.parseFloat(reader.nextLine());
        Utils_vdm.Date newBeginDate = new Utils_vdm.Date(bYear, bMonth, bDay);
        
        System.out.print("New ending date\n\n");
		System.out.print("  Day: ");
        Float eDay = Float.parseFloat(reader.nextLine());
        System.out.print("  Month: ");
        Float eMonth = Float.parseFloat(reader.nextLine());
        System.out.print("  Year: ");
        Float eYear = Float.parseFloat(reader.nextLine());
        Utils_vdm.Date newEndingDate = new Utils_vdm.Date(eYear, eMonth, eDay);
        
        center.changeEventDate(eventName, userName, new beginQuote(), newBeginDate);
        center.changeEventDate(eventName, userName, new endingQuote(), newEndingDate);
        
        printEmptyLines(EMPTY_LINES);
    }
    
    private void changeEventType(String eventName) {
    	printEmptyLines(EMPTY_LINES);
    	printLine();
    	
		System.out.print("Event Types\n\n");
		System.out.println("1- Conference");
		System.out.println("2- Trade Fair");
		System.out.println("3- Party");
		System.out.println("4- Musical");
		System.out.println("5- Team Building\n");
		System.out.print("Choose one type: ");
		Integer option = Integer.parseInt(reader.nextLine());
		
		switch (option) {
        case 1:  center.changeEventType(eventName, userName, new ConferenceQuote());
                 break;
        case 2:  center.changeEventType(eventName, userName, new TradeFairQuote());
                 break;
        case 3:  center.changeEventType(eventName, userName, new PartyQuote());
                 break;
        case 4:  center.changeEventType(eventName, userName, new MusicalQuote());
                 break;
        case 5:  center.changeEventType(eventName, userName, new TeamBuildingQuote());
                 break;
        default: System.out.println("Invalid option");
                 break;
    }
        
        printEmptyLines(EMPTY_LINES);
    }
    
    private void addServiceToEvent(String eventName) {
    	printEmptyLines(EMPTY_LINES);
    	printLine();
    	
    	VDMSet availableServices = center.availableServicesForEvent(eventName);
    	if(availableServices.size() == 0) {
    		System.out.println("There are no more services available");
    	} else {
    		int i = 1;
        	System.out.println("Available Services\n");
        	Iterator<Object> it = availableServices.iterator();
        	ArrayList<Object> services = new ArrayList<Object>();
        	while(it.hasNext()) {
        		Object service = it.next();
        		services.add(service);
        		System.out.println(i + "- " + service);
        		i++;
        	}
        	System.out.println();
        	
        	System.out.print("Choose one service: ");
    		Integer option = Integer.parseInt(reader.nextLine());
    		if(option < 1 && option > services.size()) {
    			System.out.println("Invalid option");
    		} else {
    			center.addServiceToEvent(eventName, userName, services.get(option -  1));
    		}
    	}
    	printEmptyLines(EMPTY_LINES);
    }
    
    private void removeServiceFromEvent(String eventName) {
    	printEmptyLines(EMPTY_LINES);
    	printLine();
    	
    	VDMSet eventServices = center.listEventServices(eventName);
    	if(eventServices.size() == 0) {
    		System.out.println(eventName + " does not have any service.");
    	} else {
    		int i = 1;
        	System.out.println("Event's Services\n");
        	Iterator<Object> it = eventServices.iterator();
        	ArrayList<Object> services = new ArrayList<Object>();
        	while(it.hasNext()) {
        		Object service = it.next();
        		services.add(service);
        		System.out.println(i + "- " + service);
        		i++;
        	}
        	System.out.println();
        	
        	System.out.print("Choose one service: ");
    		Integer option = Integer.parseInt(reader.nextLine());
    		if(option < 1 && option > services.size()) {
    			System.out.println("Invalid option");
    		} else {
    			center.removeServiceFromEvent(eventName, userName, services.get(option -  1));
    		}
    	}
    	printEmptyLines(EMPTY_LINES);
    }
    
    private void removeAttendeeFromEvent(String eventName) {
    	printEmptyLines(EMPTY_LINES);
    	printLine();
    	
    	Event event = (Event)center.events.get(eventName);
    	Iterator<String> it = event.attendees.iterator();
    	System.out.println("Attendees:\n");
    	while(it.hasNext()) {
    		System.out.println(it.next());
    	}
    	System.out.print("Attendee name: ");
    	String attendeeToRemove = reader.nextLine();
    	center.removeUserFromEvent(eventName, attendeeToRemove, new attendeeQuote());
    	printEmptyLines(EMPTY_LINES);
    }
    
    private void removeStaffFromEvent(String eventName) {
    	printEmptyLines(EMPTY_LINES);
    	printLine();
    	
    	Event event = (Event)center.events.get(eventName);
    	Iterator<String> it = event.staff.iterator();
    	System.out.println("Staff members:\n");
    	while(it.hasNext()) {
    		System.out.println(it.next());
    	}
    	System.out.print("Staff member name: ");
    	String staffToRemove = reader.nextLine();
    	center.removeUserFromEvent(eventName, staffToRemove, new attendeeQuote());
    	printEmptyLines(EMPTY_LINES);
    }
    
    private void inviteToEvent(String eventName) {
    	printEmptyLines(EMPTY_LINES);
    	printLine();
    	
    	System.out.print("User name: ");
    	String userToInvite = reader.nextLine();
    	center.inviteToEvent(eventName, userName, userToInvite);
    	printEmptyLines(EMPTY_LINES);
    }
}
