package eventManagement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class fileManagement {

    public void saveDataForLoghistory(Map<Task, budget> logHistory, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(logHistory);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    
    public void saveDataForEventList(Map<Task, ArrayList<Checklist>> eventList, String fName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fName))) {
            out.writeObject(eventList);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    
    public Map<Task, budget> loadDataForLoghistory(String fName) {
        Map<Task, budget> logHistory = new LinkedHashMap<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fName))) {
            logHistory = (Map<Task, budget>) in.readObject();
            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
        return logHistory;
    }
    
    public Map<Task, ArrayList<Checklist>> loadDataForEventList(String fName) {
    	Map<Task, ArrayList<Checklist>> eventList = new LinkedHashMap<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fName))) {
        	eventList = (Map<Task, ArrayList<Checklist>>) in.readObject();
            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
        return eventList;
    }
}
