
package calendar;

import SqlDatabase.HerokuUsersSqlConnection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.JsonSql;
import model.calendar;
import model.dateTasks;
import model.task;
import org.json.JSONArray;
import org.json.simple.JSONObject; 
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Calendar {


    public static void main(String[] args) throws ParseException, JsonProcessingException, java.text.ParseException {
 
        String url = "jdbc:mysql://i54jns50s3z6gbjt.chr7pe7iynqr.eu-west-1.rds.amazonaws.com/iy5jkxqnaep9jd04";
        String user = "e3uv1gsfsu6r8v6j";
        String pswd = "ud6g593tlbsh3knw"; 
        
        HerokuUsersSqlConnection herokuConnection = HerokuUsersSqlConnection.getInstance(url + "?useSSL=false", user, pswd);
                
        calendar calendar1 = new calendar("calendar n1");
        calendar calendar2 = new calendar("calendar n2");

        String stringDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = formatter.parse(stringDate);
<<<<<<< Updated upstream

        System.out.println("(Date type) -> " + date + " (String type)-> " + stringDate); 
       
        task t = new task("");
        
        c.setTask(stringDate, 0, t);
        
        c.setTask(stringDate, 0, t);

        c.setTask(stringDate, 0, t);
        
        
=======
        // System.out.println("(Date type) -> " + date + " (String type)-> " + stringDate); 
>>>>>>> Stashed changes
        
        calendar1.setTask(stringDate, "Mi tarea n1 para calendar1");
        calendar1.setTask(stringDate, "Mi tarea n2 para calendar1");
        calendar2.setTask(stringDate, "Mi tarea n1 para calendar2");       
        /* 
        JSONObject json = new JSONObject();
        json.put(calendar1.getName(), calendar1);
        json.put(calendar2.getName(), calendar2);
        
        String jsonString = new Gson().toJson(json);
        JSONObject jsonCalendar = (JSONObject) new JSONParser().parse(jsonString);
        */
        List<calendar> objects =  new ArrayList<>();
        objects.add(calendar1);
        objects.add(calendar2);
        JSONObject jsonCalendar = JsonSql.getListJson(objects);
        
        herokuConnection.selectAllUsers();
        // herokuConnection.insertUser("listJson1", "listJson1", "listJson1", jsonCalendar, false);
          
        JSONObject getJsonCalendar = herokuConnection.getCalendarById(17);
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                
        JSONObject getJsonCalendarArray = (JSONObject) getJsonCalendar.get("calendar n2");
        System.out.println(getJsonCalendar); 
        calendar c_1 = (calendar) objectMapper.readValue(getJsonCalendarArray.toJSONString(), calendar.class);
        System.out.println("Calendario obtenido: " + c_1.toString());
        
        
        // encapsular json en calendar

        
    }
    
}
    