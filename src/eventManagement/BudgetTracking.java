package eventManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class BudgetTracking {
	private static final long serialVersionUID = 2L;
    private budget expenses;
    private Map<Task, budget> history;
    
    public void getHistory(Map<Task, budget> logHistory){
    	this.history = logHistory;
    }
    
    public BudgetTracking() {
    	this.expenses = new budget();
    	this.history = new LinkedHashMap<>();
    }

    public Map<Task, budget> manageBudget(Task event) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        
        expenses = transLog(event);
        
        if (event.getName() != "Unnamed") {
        	do {
                System.out.println("\nBudget Management Menu for " + event.getName());
                System.out.println("[1] Set Budget Limit");
                System.out.println("[2] Add Expense");
                System.out.println("[3] View Budget");
                System.out.println("[4] Back");
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        setBudgetLimit(scanner, expenses);
                        break;
                    case 2:
                        addExpense(scanner, expenses);
                        break;
                    case 3:
                        expenses.viewBudget();
                        break;
                    case 4:
                    	history.put(event, expenses);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                } 
        	} while (choice != 4);
        } else {
        	System.out.println(" ");
        }
        return history;
    }
    
    public budget transLog(Task event) {
    	budget receipts = history.get(event);
    	if (receipts == null) {
        	receipts = new budget();
        } 
    	return receipts;
    }

    private void setBudgetLimit(Scanner scanner, budget receipts) {
        System.out.print("Enter budget limit: ");
        double limit = scanner.nextDouble();
        receipts.setLimit(limit);
        System.out.println("Budget limit set to: " + receipts.getLimit());
    }

    private void addExpense(Scanner scanner, budget receipts) {
        System.out.print("Enter expense name: ");
        String name = scanner.nextLine();
        System.out.print("Enter expense amount: ");
        double amount = scanner.nextDouble();
        receipts.addExpenses(name, amount);
        System.out.println("Expense added.");
    }
}
