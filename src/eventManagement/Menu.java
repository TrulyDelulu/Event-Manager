package eventManagement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private eventManager eventManager;
    private Scanner scanner;
    private BudgetTracking budgetTracker;
    private Map<Task, ArrayList<Checklist>> eventList;
    private Notification realTime;
    private Map<Task, budget> logHistory;
    private fileManagement fileManager;
    
    private final String fNEL = "eventList.ser";
    private final String fNLH = "logHistory.ser";

    public Menu(eventManager eventManager, BudgetTracking budgetTracker) {
        this.eventManager = eventManager;
        this.fileManager = new fileManagement();
        this.scanner = new Scanner(System.in);
		this.budgetTracker = new BudgetTracking();
		this.realTime = new Notification();
		this.eventList = fileManager.loadDataForEventList(fNEL);
		if (this.eventList == null) {
	        this.eventList = new LinkedHashMap<>();
	    }
		this.logHistory = fileManager.loadDataForLoghistory(fNLH);
		if (this.logHistory == null) {
	        this.logHistory = new LinkedHashMap<>();
	    }
    }

	public void displayMenu() {
    	
        while (true) {
        	realTime.rtNotify(eventList);
        	eventManager.getList(eventList);
        	budgetTracker.getHistory(logHistory);
            System.out.println("\n--- Event Management App ---");
            System.out.println("[1] Manage Events");
            System.out.println("[2] Manage Budget");
            System.out.println("[3] View All Events and Details");
            System.out.println("[4] Post-Event Report");
            System.out.println("[5] Save and Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    eventList = eventManager.displayEventMenu();
                    break;
                case 2:
                    logHistory = budgetTracker.manageBudget(eventManager.getEvent(eventList));
                    break;
                case 3:
                	eventManager.getList(eventList);
                	budgetTracker.getHistory(logHistory);
                	eventManager.viewListWithBudget(logHistory);
                    break;
                case 4:
                    eventManager.generatePostReport(eventList, logHistory);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    fileManager.saveDataForEventList(eventList, fNEL);
                    fileManager.saveDataForLoghistory(logHistory, fNLH);
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
        
    }
}
