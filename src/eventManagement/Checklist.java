package eventManagement;

public class Checklist extends Task{
	
	public Checklist(String name, String description, String date, String time, int priority) {
		super(name, description, date, time, priority);
	}
	
	public String overDue() {
    	String colorPrio = "\033[31m\033[1m";
    	
        String reset = "\033[0m";
        String status = super.isCompleted() ? "Completed" : "Pending";
        String s = colorPrio + "\t\t\t [" + status + "] " + super.getName() + "\n\t\t\t[Due on "+ getDate() + ", " + getTime() + "]\n\t\t\tComments: " + super.getDescription() + reset;
        return s;
    }
	
	public String postReport(long date, long minutes){
		long timeD = date;
		long time = minutes;
		long timeM = time;
    	long timeH = timeM/60;
    	long nTimeD = timeD*(-1);
    	long nTimeM = timeM*(-1);
    	String result = "AH";
    	String goodD = "The checklist [" + super.getName()+ "] was finished " + timeD + " days before the due date. Congratulations! Punctuality is key to life!";
    	String midD = "The checklist [" + super.getName()+ "] was finished " + timeD + " days before the due date. Try to do your preparations earlier for a better peace of mind next time but nevertheless good work!";
    	String badD = "The checklist [" + super.getName()+ "] was finished " + nTimeD + " days after the due date. Did the event even turn out well?";
    	String goodH = "The checklist [" + super.getName()+ "] was finished " + timeH + " hours before the due date. On the same day? Congrats but aren't you rather brave?";
    	String midH = "The checklist [" + super.getName()+ "] was finished " + timeH + " hours before the due date. Only a couple hours from the due date? Good work but couldn't you have done it earlier?";
    	String midM = "The checklist [" + super.getName()+ "] was finished " + timeM + " minutes before the due date. So close to the due date? Good work but couldn't you have done it earlier?";
    	String badM = "The checklist [" + super.getName()+ "] was finished " + nTimeM + " minutes after the due date. Damn you should have done it much earlier.";
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
	
	public String displayDescription() {
		String colorPrio;
		if (super.isCompleted()){
    		colorPrio = "\033[38;5;245m";
    	} else if (super.getPrio() == 1) {
    		colorPrio = "\033[31m";
    	} else if (super.getPrio() == 2) {
    		colorPrio = "\033[38;5;166m";
    	} else if (super.getPrio() == 3) {
    		colorPrio = "\033[33m";
    	} else {
    		colorPrio = "\033[30m";
    	}
        String reset = "\033[0m";
        String status = super.isCompleted() ? "Completed" : "Pending";
        String s = colorPrio + " [" + status + "] " + super.getName() + "\n\t\t\t[Due on "+ super.getDate() + ", " + super.getTime() + "]\n\t\t\tComments: " + super.getDescription() +  reset;
        return s;
    }
    
}
