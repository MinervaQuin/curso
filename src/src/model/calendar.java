
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

public class calendar {
    
    @JsonProperty("dateTasks")
    private final List<dateTasks> dateTasks = new ArrayList<>();
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("prop")
    private String prop;
    
    @JsonProperty("editores")
    private final List<user>  editores = new ArrayList<>();
    
    @JsonProperty("lectores")
    private final List<user> lectores = new ArrayList<>();
    
    public calendar (){}
    
    public calendar (String name) {
        this.name = name;
    }
    
    public dateTasks getDayTask (Date date) {
        
        for (dateTasks dateTask : dateTasks) {
            if(date.equals(dateTask.getDate())) return dateTask;
        }
        return null;
    }
    
    public List<dateTasks> getAllTask () {
        return dateTasks;
    }
    
    public void setTask(String date, String name) {
        task t = new task(name);
        dateTasks dateTask = new dateTasks(date);
        dateTask.addTask(t);
        this.dateTasks.add(dateTask);
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public String toString(){
        String c = "";
        for (dateTasks i : dateTasks) {
            c += i.toString() + ", ";
        }
        c = c.substring(0, c.length() - 2);
        return "["+ "{Nombre: " + name + "}, {Tareas: " + c + "}]";
        
    }
    
}
