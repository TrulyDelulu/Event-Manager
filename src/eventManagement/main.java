package eventManagement;

public class main {
	
    public static void main(String[] args) {
        eventManager eventManager = new eventManager();
        BudgetTracking budgetTracker = new BudgetTracking();
        Menu menu = new Menu(eventManager, budgetTracker);
        menu.displayMenu();
    }
}

