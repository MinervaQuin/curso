
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

public class calendar {
    
    @JsonProperty("dateTasks")
    private final Map<String, List<task>> dateTasks = new HashMap<>();
    
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
    
    // Método para modificar una tarea
    public void setTask(String date, int position, task newTask) {
        Iterator i = dateTasks.entrySet().iterator();
        List l = new ArrayList<>();
        while(i.hasNext()) {
            Map.Entry<String, List<task>> entry = (Map.Entry)i.next();
            if (entry.getKey().equals(date)) {
                l = entry.getValue();
                l.set(position, newTask);
            }
        }
    }
    
    // Método para añadir una tarea
    public void addTask(String date, task t) {
        Iterator i = dateTasks.entrySet().iterator();
        List l = new ArrayList<>();
        while (i.hasNext()) {
            Map.Entry<String, List<task>> entry = (Map.Entry)i.next();
            if (entry.getKey().equals(date)) {
                l = entry.getValue();
                l.add(t);
                break;
            }
        }
        if (l.isEmpty()) {
            l.add(t);
        }
        dateTasks.put(date, l);
    }
    
    // Método para obtener todas las tareas de una fecha
    public List<task> getTasks(String date) {
        Iterator i = dateTasks.entrySet().iterator();
        List l = new ArrayList<>();
        while (i.hasNext()) {
            Map.Entry<String, List<task>> entry = (Map.Entry)i.next();
            if (entry.getKey().equals(date)) {
                l = entry.getValue();
            }
        }
        Iterator j = l.iterator();
        while (j.hasNext()) {
            task t = (task)j.next();
        }
        return l;
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public String toString(){
        String c = "";
        /*for (dateTasks i : dateTasks) {
            c += i.toString() + ", ";
        }
        c = c.substring(0, c.length() - 2);
        return "["+ "{Nombre: " + name + "}, {Tareas: " + c + "}]";*/
        return c;
    }
    
}
