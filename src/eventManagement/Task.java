package eventManagement;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.io.Serializable;
import java.lang.Math;

public class Task implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int priority;
	private long finishedDate;
	private long finishedTime;
	private float progress;
	private float totalI;
	private float totalC;
	private String name;
    private String description;
    private boolean done;
    private transient DateTimeFormatter type;
    private LocalDate date;
    private LocalTime time;
    

    public Task(String name, String description, String date, String time, int priority) {
    	this.priority = priority;
    	this.finishedDate = 0;
    	this.finishedTime =0;
    	this.progress = 0;
    	this.totalI = 0;
    	this.totalC = 0;
        this.name = name;
        this.description = description;
        this.done = false;
        this.date = dateConverter(date);
        this.time = timeConverter(time);
    }

    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public LocalDate getDate() {
    	return date;
    }
    
    public LocalTime getTime() {
    	return time;
    }
    
    public float getTotalI() {
    	return totalI;
    }
    
    public float getTotalC() {
    	return totalC;
    }
    
    public float getProgress() {
    	return Math.round(progress*100);
    }
    
    public int getPrio() {
    	return priority;
    }
    
    public long getFinishedDate() {
    	return finishedDate;
    }
    
    public long getFinishedTime() {
    	return finishedTime;
    }
    
    public void setDate(String d) {
    	this.date = dateConverter(d);
    }
    
    public void setTime(String t) {
    	this.time = timeConverter(t);
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setTotalI(float d) {
    	this.totalI = d;
    }
    
    public void setTotalC(float c) {
    	this.totalC = c;
    }
    
    public void setProgress(float d, float c) {
    	if (d != 0) {
    		this.progress = c/d;
    	}
    }
    
    public void setPrio(String p) {
		if (p.equalsIgnoreCase("High")){
    		this.priority = 1;
    	} else if (p.equalsIgnoreCase("Medium")) {
    		this.priority = 2;
    	} else if (p.equalsIgnoreCase("Low")) {
    		this.priority = 3;
    	} else {
    		this.priority = 0;
            System.out.println("No priority set!");
    	}
    }
    
    public void setFinishedDate(long d) {
    	this.finishedDate = d*(-1);
    }
    
    public void setFinishedTime(long d) {
    	this.finishedTime = d*(-1);
    }

    public boolean isCompleted() {
        return done;
    }

    public void setCompleted(boolean completed) {
        done = completed;
    }
    
    public LocalDate dateConverter(String d) {
		try {
    		type = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    		date = LocalDate.parse(d, type);
    	} catch (Exception e) {
            System.out.println("Invalid date format for date!");
        }
        return date;
    }
    
    public LocalTime timeConverter(String t) {
		try {
    		type = DateTimeFormatter.ofPattern("HH:mm");
            time = LocalTime.parse(t, type);
    	} catch (Exception e) {
            System.out.println("Invalid date format for time!");
        }
        return time;
    }

    public void displayTask() {
        System.out.println("[Due on "+ getDate() + ", " + getTime() + "] | " + name + " [" + this.getProgress() + "%]");
    }
    
    public String postReport(long date, long minutes){
		long timeD = date;
		long time = minutes;
		long timeM = time;
    	long timeH = timeM/60;
    	long nTimeD = timeD*(-1);
    	long nTimeM = timeM*(-1);
    	String result = "AH";
    	String goodD = "The preparations for the event was finished " + timeD + " days before the due date. Congratulations! Punctuality is key to life!";
    	String midD = "The preparations for the event was finished " + timeD + " days before the due date. Try to do your preparations earlier for a better peace of mind next time but nevertheless good work!";
    	String badD = "The preparations for the event was finished " + nTimeD + " days after the due date. Did the event even turn out well?";
    	String goodH = "The preparations for the event was finished " + timeH + " hours before the due date. On the same day? Congrats but aren't you rather brave?";
    	String midH = "The preparations for the event was finished " + timeH + " hours before the due date. Only a couple hours from the due date? Good work but couldn't you have done it earlier?";
    	String midM = "The preparations for the event was finished " + timeM + " minutes before the due date. So close to the due date? Good work but couldn't you have done it earlier?";
    	String badM = "The preparations for the event was finished " + nTimeM + " minutes after the due date. Damn you should have done it much earlier.";
    	if (this.isCompleted()) {
    		if (timeD >= 7) {
    			result = goodD;
    		} else if (timeD >= 1) {
    			result = midD;
    		} else if (timeD < 0) {
    			result = badD;
    		} else {
    			if (timeH >= 3 && timeH <= 24) {
    				result = goodH;
    			} else if (timeH > 1 && timeH <= 2) {
    				result = midH;
    			} else if (timeM > 0 && timeM < 61){
    				result = midM;
    			} else {
    				result = badM;
    			}
    		}
    	}
    	return result;
    }
    
    public String overDue() {
    	String colorPrio = "\033[31m\033[1m";
    	String colorPrio2 = "\033[30m";
    	
        String reset = "\033[0m";
        String s = colorPrio +"\t\t[" + getProgress() +"%]"+ name + "\n\t[Due on "+ getDate() + ", " + getTime() + "]\n\tEvent description: " + description + reset + colorPrio2 +"\n\t\tChecklist: \n" + reset;
        return s;
    }
    
    public String displayDescription() {
    	String colorPrio;
    	String colorPrio2 = "\033[30m";
    	if (this.getProgress() == 100){
    		colorPrio = "\033[38;5;245m";
    		colorPrio2 = "\033[38;5;245m";
    	} else if (priority == 1) {
    		colorPrio = "\033[31m";
    	} else if (priority == 2) {
    		colorPrio = "\033[38;5;166m";
    	} else if (priority == 3) {
    		colorPrio = "\033[33m";
    	} else {
    		colorPrio = "\033[30m";
    	}
        String reset = "\033[0m";
        String s = colorPrio + name + "\n\t[Due on "+ getDate() + ", " + getTime() + "]\n\tEvent description: " + description + reset + colorPrio2 +"\n\t\tChecklist: \n" + reset;
        return s;
    }
    
    public boolean equals(Object saved) {
        if (this == saved) {
        	return true;
        }
        if (saved == null || getClass() != saved.getClass()) {
        	return false;
        }
        Task real = (Task) saved;
        return Objects.equals(name, real.name) &&
               Objects.equals(date, real.date) &&
               Objects.equals(time, real.time);
    }

    public int hashCode() {
        return Objects.hash(name, date, time);
    }
}


