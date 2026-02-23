package eventManagement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class eventManager {
	
    private Map<Task, ArrayList<Checklist>> list;
    private Scanner scan;

    public eventManager() {
        list = new LinkedHashMap<>();
        scan = new Scanner(System.in);
    }
    
    public void getList(Map<Task, ArrayList<Checklist>> fullList) {
    	this.list = fullList;
    }

    public void addEvent(Map<Task, ArrayList<Checklist>> events) {
        System.out.print("\nEnter event name: ");
        String n = scan.nextLine();
        System.out.print("\nEnter event description: ");
        String d = scan.nextLine();
        System.out.print("\nEnter event due date [in dd/MM/yyyy (ex. 09/02/2023)]: ");
        String dd = scan.nextLine();
        System.out.print("\nEnter event due time on that day [in HH:mm wherein HH is within 0-24 and mm is within 0-60 (ex. 18:35)]: ");
        String t = scan.nextLine();
        System.out.print("\nEnter level of priority [High, Medium, Low]: ");
        String p = scan.nextLine();
        int pN = 0;
        if (p.equalsIgnoreCase("High")){
        	pN = 1;
    	} else if (p.equalsIgnoreCase("Medium")) {
    		pN = 2;
    	} else if (p.equalsIgnoreCase("Low")) {
    		pN = 3;
    	} else {
    		pN = 0;
            System.out.println("No priority set!");
    	}
        Task newEvent = new Task(n, d, dd, t, pN);
        events.put(newEvent, new ArrayList<>());
        System.out.println("Event added successfully!");
    }

    public void viewEvents(Map<Task, ArrayList<Checklist>> fullList) {
        if (list.keySet().isEmpty()) {
            System.out.println("No events available.");
        } else {
            for (int i = 0; i < list.keySet().size(); i++) {
                System.out.printf("\n%d. ", (i + 1));
                ArrayList<Task> eventSet = new ArrayList<>(fullList.keySet());
                eventSet.get(i).displayTask();
            }
        }
    }
    
    public void viewList() {
        if (list.keySet().isEmpty()) {
            System.out.println("No events available.");
        } else {
        	int p = 1;
        	System.out.println("Viewing events...");
        	for (Task key : list.keySet()) {
                System.out.println(" [" + key.getProgress() + " " + '%' + "] " + p + ". " + key.displayDescription());
                int l = 1;
                ArrayList<Checklist> checker = list.get(key);
                if (checker.isEmpty()) {
                    System.out.println("\t\t\tNo checklists available");
                }
                for (Task Task : list.get(key)) {
                	System.out.printf("\t\t%d. ", l);
                    System.out.print(Task.displayDescription());
                    System.out.print("\n");
                    l++;
                }
                p++;
            }
        }
    }
    
    public void viewListWithBudget(Map<Task, budget> history) {
        if (list.keySet().isEmpty()) {
            System.out.println("No events available.");
        } else {
        	int p = 1;
        	System.out.println("Viewing events...");
        	for (Task key : list.keySet()) {
                System.out.println(" [" + key.getProgress() + " " + '%' + "] " + p + ". " + key.displayDescription());
                int l = 1;
                ArrayList<Checklist> checker = list.get(key);
                if (checker.isEmpty()) {
                    System.out.println("\t\t\tNo checklists available");
                }
                for (Task Task : list.get(key)) {
                	System.out.printf("\t\t%d. ", l);
                    System.out.print(Task.displayDescription());
                    System.out.print("\n");
                    l++;
                }
                p++;
                if (history != null) {
                	
            		budget c = history.get(key);
            		if (c != null) {
            			c.viewBudgetForList();
            		}
            	}
            }
        }
    }
    
    public void editEventName(Map<Task, ArrayList<Checklist>> fullList) {
        viewEvents(fullList);
        System.out.print("\n\nEnter a number to edit the event: ");
        int n = scan.nextInt();
        scan.nextLine();
        ArrayList<Task> eventSet = new ArrayList<>(fullList.keySet());
        if (n >= 1 && n <= eventSet.size()) {
        	int c = 0;
        	ArrayList<Checklist> connections = fullList.get(eventSet.get(n - 1));
        	Task temp1 = eventSet.get(n - 1);
        	do {
        		System.out.println("\nEvent Edit Menu for " + eventSet.get(n - 1).getName() + ":");
            	System.out.println("[1] Event name ");
            	System.out.println("[2] Event description ");
            	System.out.println("[3] Event date ");
            	System.out.println("[4] Event time ");
            	System.out.println("[5] Event priority ");
            	System.out.println("[6] Exit Edit Menu ");
            	System.out.print("\nEnter your choice: ");
                c = scan.nextInt();
                scan.nextLine();
                switch (c) {
                case 1:
                	System.out.print("\nInput new event name: ");
                    String sn = scan.nextLine();
                    fullList.remove(temp1);
                    temp1.setName(sn);
                    fullList.put(temp1, connections);
                    System.out.print("\nEvent name editted successfully! ");
                	break;
                	
                case 2:
                	System.out.print("\nInput new event description: ");
                    String sd = scan.nextLine();
                    fullList.remove(temp1);
                    temp1.setDescription(sd);
                    fullList.put(temp1, connections);
                    System.out.print("\nEvent description editted successfully! ");
                	break;
                	
                case 3:
                	System.out.print("\nEnter event due date [in dd/MM/yyyy (ex. 09/02/2023)]: ");
                    String d = scan.nextLine();
                    fullList.remove(temp1);
                    temp1.setDate(d);
                    fullList.put(temp1, connections);
                    System.out.print("\nEvent due date editted successfully! ");
                	break;
                
                case 4:
                	System.out.print("\nEnter event due time on that day [in HH:mm wherein HH is within 0-24 and mm is within 0-60 (ex. 08:35)]: ");
                    String t = scan.nextLine();
                    fullList.remove(temp1);
                    temp1.setTime(t);
                    fullList.put(temp1, connections);
                    System.out.print("\nEvent due time editted successfully! ");
                	break;
                	
                case 5:
                	System.out.print("\nEnter level of priority [High, Medium, Low]: ");
                    String p = scan.nextLine();
                    fullList.remove(temp1);
                    temp1.setPrio(p);
                    fullList.put(temp1, connections);
                    System.out.print("\nEvent priority editted successfully! ");
                	break;
                	
                case 6:
                	System.out.println("Exiting Edit Menu...");
                	break;
                	
                default:
                	System.out.println("Invalid choice.");
                	break;
                }
        	} while (c != 6);
        } else {
            System.out.println("Invalid event number.");
        }
    }

    public void deleteEvent(Map<Task, ArrayList<Checklist>> fullList) {
        viewEvents(fullList);
        System.out.print("\n\nEnter a number to delete: ");
        int n = scan.nextInt();
        scan.nextLine();
        ArrayList<Task> eventSet = new ArrayList<>(fullList.keySet());
        if (n >= 1 && n <= eventSet.size()) {
        	fullList.remove(eventSet.get(n - 1));
            System.out.println("Event deleted successfully!");
        } else {
            System.out.println("Invalid event number.");
        }
    }
    
    public Task getEvent(Map<Task, ArrayList<Checklist>> fullList) {
    	viewEvents(fullList);
        System.out.print("\n\nEnter a number to manage checklists: ");
        int n = scan.nextInt();
        scan.nextLine();
        ArrayList<Task> eventSet = new ArrayList<>(fullList.keySet());
        String b = "Unnamed";
        String c = "ad";
        String d = "01/01/2024";
        String e = "18:45";
        int f = 0;
        Task a = new Task(b, c, d, e, f);
        if (n >= 1 && n <= eventSet.size()) {
        	for (int i = 0; i < eventSet.size(); i++) {
        		if (i == (n - 1)) {
        			a = eventSet.get(i);;
        		}
            }
        } else {
            System.out.println("Invalid event number.");
        }
    	return a;
    }
    
    public ArrayList<Checklist> getChecklists(Task Task){
    		ArrayList<Checklist> existingChecklists = list.get(Task);
    	return existingChecklists;
    }
    
    public void addChecklist(Task Task, ArrayList<Checklist> connections) {
    	System.out.print("\nEnter checklist name: ");
        String n = scan.nextLine();
        String d = "N/A";
        System.out.print("\nEnter checklist due date [in dd/MM/yyyy (ex. 09/02/2023)]: ");
        String dd = scan.nextLine();
        System.out.print("\nEnter checklist due time on that day [in HH:mm wherein HH is within 0-24 and mm is within 0-60 (ex. 18:35)]: ");
        String t = scan.nextLine();
        System.out.print("\nEnter level of priority [High, Medium, Low]: ");
        String p = scan.nextLine();
        int pN = 0;
        if (p.equalsIgnoreCase("High")){
        	pN = 1;
    	} else if (p.equalsIgnoreCase("Medium")) {
    		pN = 2;
    	} else if (p.equalsIgnoreCase("Low")) {
    		pN = 3;
    	} else {
    		pN = 0;
            System.out.println("No priority set!");
    	}

        Checklist newChecklist = new Checklist(n, d, dd, t, pN);
        connections.add(newChecklist);
        list.put(Task, connections);
        System.out.println("Checklist added successfully!");
    }

    public void viewChecklists(ArrayList<Checklist> connections) {
        if (connections.isEmpty()) {
            System.out.println("No checklist available for this event.");
        } else {
            for (int i = 0; i < connections.size(); i++) {
                System.out.printf("\n%d. ", (i + 1));
                System.out.println(connections.get(i).displayDescription());
            }
        }
    }
    
    public void addChecklistComment(ArrayList<Checklist> connected) {
    	viewChecklists(connected);
        System.out.print("\n\nEnter a number to add a comment: ");
        int n = scan.nextInt();
        scan.nextLine();
        if (n >= 1 && n <= connected.size()) {
        	System.out.print("\nInput comment: ");
            String sd = scan.nextLine();
            connected.get(n - 1).setDescription(sd);
            System.out.print("\nComment added successfully! ");
        } else {
            System.out.println("Invalid checklist number.");
        }
    }
    
    public void editChecklist(Task Task, ArrayList<Checklist> connected) {
        viewChecklists(connected);
        System.out.print("\n\nEnter a number to edit the checklist: ");
        int n = scan.nextInt();
        scan.nextLine();
        if (n >= 1 && n <= connected.size()) {
        	int c = 0;
        	do {
        		System.out.println("\nChecklist Edit Menu for " + connected.get(n-1).getName() + ":");
            	System.out.println("[1] Checklist name ");
            	System.out.println("[2] Checklist comment ");
            	System.out.println("[3] Checklist date ");
            	System.out.println("[4] Checklist time ");
            	System.out.println("[5] Checklist priority ");
            	System.out.println("[6] Exit Edit Menu ");
            	System.out.print("\nEnter your choice: ");
                c = scan.nextInt();
                scan.nextLine();
                switch (c) {
                case 1:
                	System.out.print("\nInput new checklist name: ");
                    String sn = scan.nextLine();
                    connected.get(n - 1).setName(sn);
                    System.out.print("\nChecklist name editted successfully! ");
                	break;
                	
                case 2:
                	System.out.print("\nInput comment: ");
                    String sd = scan.nextLine();
                    connected.get(n - 1).setDescription(sd);
                    System.out.print("\nChecklist comment editted successfully! ");
                	break;
                	
                case 3:
                	System.out.print("\nEnter new checklist due date [in dd/MM/yyyy (ex. 09/02/2023)]: ");
                    String d = scan.nextLine();
                    connected.get(n - 1).setDate(d);
                    System.out.print("\nChecklist due date editted successfully!");
                	break;
                
                case 4:
                	System.out.print("\nEnter new checklist due time on that day [in HH:mm wherein HH is within 0-24 and mm is within 0-60 (ex. 08:35)]: ");
                    String t = scan.nextLine();
                    connected.get(n - 1).setTime(t);
                    System.out.print("\nChecklist due time editted successfully! ");
                	break;
                	
                case 5:
                	System.out.print("\nEnter level of priority [High, Medium, Low]: ");
                    String p = scan.nextLine();
                    connected.get(n-1).setPrio(p);
                    System.out.print("\nChecklist priority editted successfully! ");
                	break;
                	
                case 6:
                	list.put(Task, connected);
                	System.out.println("Exiting Edit Menu...");
                	break;
                	
                default:
                	System.out.println("Invalid choice.");
                	break;
                }
        	} while (c != 6);
        } else {
            System.out.println("Invalid checklist number.");
        }
    }
    
    public void markChecklistDone(Task Task, ArrayList<Checklist> connected) {
        viewChecklists(connected);
        System.out.print("\n\nEnter a number to mark as completed: ");
        int n = scan.nextInt();
        scan.nextLine();
        if (n >= 1 && n <= connected.size()) {
        	LocalTime currentT = LocalTime.now();
        	LocalDate currentD = LocalDate.now();
        	long minutes = 0;
        	long days = 0;
        	if (connected.get(n-1).getDate() != null && connected.get(n-1).getTime() != null) {
        		connected.get(n - 1).setCompleted(true);
        		minutes = ChronoUnit.MINUTES.between(connected.get(n - 1).getTime(), currentT);
                days = ChronoUnit.DAYS.between(connected.get(n - 1).getDate(), currentD);
                connected.get(n - 1).setFinishedDate(days);
                connected.get(n - 1).setFinishedTime(minutes);
                System.out.println("Checklist marked as completed!");
                
        	} else {
        		System.out.println("Date and time have not been set for this checklist so this cannot be marked complete.");
        		return;
        	}
        } else {
            System.out.println("Invalid checklist number.");
        }
    }
    
    public float completedChecker(Task Task, ArrayList<Checklist> connected) {
    	float count = 0;
    	
    	for (int i = 0; i < connected.size(); i++) {
            if ((connected.get(i).isCompleted())) {
            	count++;
            }
        }
    	return count;
    }

    public void deleteChecklist(ArrayList<Checklist> connected) {
        viewChecklists(connected);
        System.out.print("\n\nEnter a number to delete: ");
        int n = scan.nextInt();
        scan.nextLine();
        
        if (n >= 1 && n <= connected.size()) {
        	connected.remove(n - 1);
            System.out.println("Checklist deleted successfully!");
            
        } else {
            System.out.println("Invalid checklist number.");
        }
    }
    
    public void generatePostReport(Map<Task, ArrayList<Checklist>> fullList, Map<Task, budget> history) {
    	Task a = getEvent(fullList);
    	if (a.getName() == "Unnamed") {
    		return;
    	}
    	ArrayList<Checklist> b = getChecklists(a);
    	
    	if (fullList.containsKey(a) && a.isCompleted()) {
    		System.out.println("\nPOST REPORT for "+ a.getName() +":\n\n\t" + a.postReport(a.getFinishedDate(), a.getFinishedTime()) + "\n\n\tFor the checklist of this event:");
    		int l = 1;
            if (b.isEmpty()) {
                System.out.println("\n\t\tNo checklists available");
            }
            for (Task Task : fullList.get(a)) {
            	System.out.printf("\n\t\t%d. ", l);
                System.out.print(Task.postReport(Task.getFinishedDate(), Task.getFinishedTime()));
                System.out.print("\n");
                l++;
            }
            if (history != null) {
        		budget c = history.get(a);
        		if (c != null) {
        			System.out.println("\n\t\t"+c.postReport());
        		}
        	}
            
    	} else {
    		System.out.println("Event is incomplete!");
    	}
    }
    
    public void displayChecklistMenu(Task Task, ArrayList<Checklist> connected) {
        int choice = 0;
        connected = getChecklists(Task);

        do {
        	Task.setTotalC(completedChecker(Task, connected));
        	Task.setTotalI(connected.size());
        	Task.setProgress(Task.getTotalI(), Task.getTotalC());
        	
            System.out.println("\nChecklist Menu for " + Task.getName() + " [" + Task.getProgress() + "%]:");
            System.out.println("[1] Add a New Checklist");
            System.out.println("[2] View All Checklists");
            System.out.println("[3] Add a Comment");
            System.out.println("[4] Edit Checklists");
            System.out.println("[5] Mark a Checklist Done");
            System.out.println("[6] Delete a Checklist");
            System.out.println("[7] Exit Checklist Menu");
            System.out.print("Enter your choice: ");
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    addChecklist(Task, connected);
                    break;
                case 2:
                	viewChecklists(connected);
                    break;
                    
                case 3:
                	addChecklistComment(connected);
                    break;
                    
                case 4:
                	editChecklist(Task, connected);
                    break;
                    
                case 5:
                	markChecklistDone(Task, connected);
                    break;
                case 6:
                    deleteChecklist(connected);
                    break;
                    
                case 7:
                	LocalTime currentT = LocalTime.now();
                	LocalDate currentD = LocalDate.now();
                    long minutes = 0;
                	long days = 0;
                	if (Task.getDate() != null && Task.getTime() != null) {
                		Task.setTotalC(completedChecker(Task, connected));
                    	Task.setTotalI(connected.size());
                    	Task.setProgress(Task.getTotalI(), Task.getTotalC());
                    	minutes = ChronoUnit.MINUTES.between(Task.getTime(), currentT);
                        days = ChronoUnit.DAYS.between(Task.getDate(), currentD);
                		if (Task.getProgress() == 100) {
                        	Task.setFinishedDate(days);
                        	Task.setFinishedTime(minutes);
                        	Task.setCompleted(true);
                        } else {
                        	Task.setFinishedDate(0);
                        	Task.setFinishedTime(0);
                        	Task.setCompleted(false);
                        }
                	} else {
                		System.out.println("Date and time have not been set for this event so this event cannot be completed.");
                	}
                    System.out.println("Exiting the menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
    }

    public Map<Task, ArrayList<Checklist>> displayEventMenu() {
        int choice = 0;
        do {
            System.out.println("\nEvent Manager Menu:");
            System.out.println("[1] Add a New Event");
            System.out.println("[2] View All Events");
            System.out.println("[3] Edit Event");
            System.out.println("[4] Delete a Event");
            System.out.println("[5] Manage an Event's Checklist");
            System.out.println("[6] Exit Event Manager");
            System.out.print("Enter your choice: ");
            choice = scan.nextInt();
            scan.nextLine();
            
            switch (choice) {
                case 1:
                    addEvent(list);
                    break;
                case 2:
                    viewList();
                    break;
                case 3:
                	editEventName(list);
                    break;
                case 4:
                    deleteEvent(list);
                    break;
                case 5:
                	Task a = getEvent(list);
                    displayChecklistMenu(a, getChecklists(a));
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
        return list;
    }
    
}


