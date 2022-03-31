package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class dateTasks {
    
    @JsonProperty("tasks")
    private List<task> tasks;
    
    @JsonProperty("date")
    // @JsonFormat(pattern = "yyyy-MM-dd")
    private String date;
    
    public dateTasks () {}
    
    public dateTasks (String date) {
        this.date = date;
        this.tasks = new ArrayList<>();
    }
    
    public void addTask (task t) {
        tasks.add(t);
    }

    public List<task> getTask () {
        return tasks;
    }
    
    public String getDate () {
        return date;
    }
    
    public List<task> getAllTasks () {
        return tasks;
    }
    
    @Override
    public String toString(){
        String c = "";
        for (task i : this.getAllTasks()) {
            c += i.getName() + ", ";
        }
        c = c.substring(0, c.length() - 2);
        return "["+ "{Día: " + date + "}, {Descripción de tarea: " + c + "}]";
        
    }
    
}