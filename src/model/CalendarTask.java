
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class CalendarTask extends JsonSql{
    
    @JsonProperty("dateTasks")
    private final Map<String, List<Task>> dateTasks = new HashMap<>();
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("idOwner")
    private Integer idOwner;
    
    
    // mapa clave(id usuario), valor(permiso:editor, lector)
    
    
    @JsonProperty("editores")
    private final List<Integer>  editores = new ArrayList<>();
    
    @JsonProperty("lectores")
    private final List<User> lectores = new ArrayList<>();
    
    public CalendarTask (){}
    
    public CalendarTask (String name) {
        this.name = name;
    }

    
    // Método para modificar una tarea
    public void setTask(String date, int position, Task newTask) {
        Iterator i = dateTasks.entrySet().iterator();
        List l = new ArrayList<>();
        while(i.hasNext()) {
            Map.Entry<String, List<Task>> entry = (Map.Entry)i.next();
            if (entry.getKey().equals(date)) {
                l = entry.getValue();
                l.set(position, newTask);
            }
        }
    }
    
    // Método para añadir una tarea
    public void addTask(String date, Task t) {
        Iterator i = dateTasks.entrySet().iterator();
        List l = new ArrayList<>();
        while (i.hasNext()) {
            Map.Entry<String, List<Task>> entry = (Map.Entry)i.next();
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
    public List<Task> getTasks(String date) {
        Iterator i = dateTasks.entrySet().iterator();
        List l = new ArrayList<>();
        while (i.hasNext()) {
            Map.Entry<String, List<Task>> entry = (Map.Entry)i.next();
            if (entry.getKey().equals(date)) {
                l = entry.getValue();
            }
        }
        Iterator j = l.iterator();
        while (j.hasNext()) {
            Task t = (Task)j.next();
        }
        return l;
    }
    
        
    public static CalendarTask getCalendarFromJson(JSONObject jsonObject, String name) throws ParseException, JsonProcessingException{
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JSONObject getJson = (JSONObject) jsonObject.get(name);
        
        CalendarTask mapped = (CalendarTask) objectMapper.readValue(getJson.toJSONString(), CalendarTask.class);
        return mapped;
        
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public String toString(){
        return name;
    }
    

           
    
}
