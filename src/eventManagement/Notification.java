package eventManagement;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Map;

public class Notification {
	public void rtNotify(Map<Task, ArrayList<Checklist>> list) {
		String colorPrio = "\033[1m";
		String reset = "\033[0m";
		System.out.println(colorPrio + "\nOVERDUE TASKS:\n" +  reset);
		
		LocalDate currentD;
		LocalTime currentT;
        
        for (Task key : list.keySet()) {
        	int t = 0;
        	if (key.getDate() == null || key.getTime() == null) {
        		System.out.println("\nThere is no due date/time inputted in one of the events.");
        		return;
        	}
        	currentT = LocalTime.now();
        	currentD = LocalDate.now();
        	long seconds = ChronoUnit.SECONDS.between(key.getTime(), currentT);
            long days = ChronoUnit.DAYS.between(key.getDate(), currentD);
            if (days > 0 && !(key.isCompleted())) {
            	System.out.println(key.overDue());
            	t++;
            }
            if (seconds > 0 && !(key.isCompleted()) && t == 0) {
            	System.out.println(key.overDue());
            }
            for (Task Task : list.get(key)) {
            	int c = 0;
            	if (Task.getDate() == null || Task.getTime() == null) {
            		System.out.println("\nThere is no due date/time inputted in one of the checklists.");
            		return;
            	}
            	long secondsC = ChronoUnit.SECONDS.between(Task.getTime(), currentT);
                long daysC = ChronoUnit.DAYS.between(Task.getDate(), currentD);
                if (daysC > 0 && !(Task.isCompleted())) {
                	System.out.println(Task.overDue());
                	c++;
                } 
                if (secondsC > 0 && !(Task.isCompleted()) && c == 0) {
                	System.out.println(Task.overDue());
                }
            }
        }
    }
}