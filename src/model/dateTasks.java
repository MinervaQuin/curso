
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class dateTasks {
    
    private final List<task> tasks = new ArrayList<>();
    private final Date d;
    
    public dateTasks (Date d, task t) {
        this.d = d;
    }
    
    public void addTask (task t) {
        tasks.add(t);
    }

    public List<task> getTask (task t) {
        return tasks;
    }
    
}
