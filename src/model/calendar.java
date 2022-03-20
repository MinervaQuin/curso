
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class calendar {
    
    private final List<dateTasks> dateTasks = new ArrayList<>();
    private final String name;
    private String prop;
    private final List<user> editores = new ArrayList<>();
    private final List<user> lectores = new ArrayList<>();
    
    public calendar (String name) {
        this.name = name;
    }
    
    public dateTasks getDayTask (Date date) {
        return new dateTasks(new Date(), new task(new Date(),""));
    }
}
