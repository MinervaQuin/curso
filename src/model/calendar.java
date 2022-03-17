package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class calendar {
    
    private List<dateTasks> dateTasks = new ArrayList<dateTasks>();
    private String name;
    private String prop;
    private List<user> editores = new ArrayList<user>();
    private List<user> lectores = new ArrayList<user>();
    
    public calendar (String name) {
        this.name = name;
    }
    
    public dateTasks getDayTask (Date date) {
        return new dateTasks(new Date(), new task(new Date(),""));
    }
}
