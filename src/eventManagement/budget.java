package eventManagement;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class budget implements Serializable{
	private static final long serialVersionUID = 2L;
	private double limit;
	private double totalExpenses;
    private Map<String, Double> expenses;
    private Map<Task, Map<String, Double>> history;
    
    public budget() {
    	this.limit  = 0.0;
    	this.expenses = new LinkedHashMap<>();
    	this.history = new LinkedHashMap<>();
    }
    
    public double getLimit() {
    	return limit;
    }
    
    public double getTotalExpenses() {
    	return totalExpenses;
    }
    
    public Map<String, Double> getReceiptHistory(){
    	return expenses;
    }
    
    public Map<Task, Map<String, Double>> getHistory(){
    	return history;
    }
    
    public void setLimit(double l) {
    	this.limit = l;
    }
    
    public void setHistory(Map<Task, Map<String, Double>> logs){
    	this.history = logs;
    }
    
    public void addExpenses(String name, double amount) {
    	expenses.put(name, amount);
    }
    
    public void findTotalExpenses() {
    	double a = 0;
    	for (double expense : expenses.values()) {
            a += expense;
        }
    	totalExpenses = a;
    }
    
    public String postReport() {
    	String s = "A";
    	if (totalExpenses <= limit) {
    		s = "Congratulations! You managed your finances well and kept yourself from overpaying as you managed to save $" + (limit-totalExpenses);
    	} else {
    		s = "You went $"+ (totalExpenses-limit) +" over the budget limit that you set for yourself. Plan your finances ahead next time to avoid such incidents..";
    	}
    	return s;
    }
    
    public void printBudget() {
    	String colorPrio = "\033[31m";
    	String reset = "\033[0m";
        System.out.println("\nBudget Details:");
        expenses.forEach((name, amount) -> {
            System.out.println("\t- " + name + ": $" + colorPrio + amount + reset);
        });
    }
    
    public void viewBudget() {
    	String colorPrioR = "\033[31m";
    	String colorPrioG = "\033[32m";
    	String reset = "\033[0m";
        System.out.print("Budget Limit: $" + colorPrioG + getLimit() + reset);
        printBudget();
        findTotalExpenses();
        double totalExpenses = getTotalExpenses();
        System.out.println("Total Expenses: $" + colorPrioR + totalExpenses + reset);
        if ((getLimit() - totalExpenses)>=0) {
        	colorPrioR = "\033[32m";
        }
        System.out.println("Remaining Budget: $" + colorPrioR + (getLimit() - totalExpenses) + reset);
    }
    
    public void printBudgetForList() {
    	String colorPrio = "\033[31m";
    	String reset = "\033[0m";
        System.out.println("\t\t\tBudget Details:");
        expenses.forEach((name, amount) -> {
            System.out.println("\t\t\t\t- " + name + ": $" + colorPrio + amount + reset);
        });
    }
    
    public void viewBudgetForList() {
    	String colorPrioR = "\033[31m";
    	String colorPrioG = "\033[32m";
    	String reset = "\033[0m";
        System.out.println("\n\n\t\t\tBudget Tracker:\n\n\t\tBudget Limit: $" + colorPrioG + getLimit() + reset);
        printBudgetForList();
        findTotalExpenses();
        double totalExpenses = getTotalExpenses();
        System.out.println("\t\t\tTotal Expenses: $" + colorPrioR + totalExpenses + reset);
        if ((getLimit() - totalExpenses)>=0) {
        	colorPrioR = "\033[32m";
        }
        System.out.println("\t\t\tRemaining Budget: " + colorPrioR + (getLimit() - totalExpenses) + reset);
    }
}
