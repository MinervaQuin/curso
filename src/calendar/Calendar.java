
package calendar;

import SqlDatabase.HerokuUsersSqlConnection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.calendar;
import model.dateTasks;
import model.task;
import org.json.simple.JSONObject; 
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Calendar {


    public static void main(String[] args) throws ParseException, JsonProcessingException, java.text.ParseException {
 
        String url = "jdbc:mysql://i54jns50s3z6gbjt.chr7pe7iynqr.eu-west-1.rds.amazonaws.com/iy5jkxqnaep9jd04";
        String user = "e3uv1gsfsu6r8v6j";
        String pswd = "ud6g593tlbsh3knw"; 
        
        
        HerokuUsersSqlConnection herokuConnection = HerokuUsersSqlConnection.getInstance(url + "?useSSL=false", user, pswd);
                
        calendar c = new calendar("calendarTest");

        String stringDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
                    
        Date date = formatter.parse(stringDate);

        System.out.println("(Date type) -> " + date + " (String type)-> " + stringDate); 
        
        
        c.setTask(stringDate, "Mi tarea");
        
        c.setTask(stringDate, "Mi tarea 2");

        c.setTask(stringDate, "Mi tarea 3");       
        
        
        
        JSONObject json = new JSONObject();
        json.put("calendar", c);
        String jsonString = new Gson().toJson(c);
        JSONObject jsonCalendar = (JSONObject) new JSONParser().parse(jsonString);
                

        herokuConnection.selectAllUsers();
        
        herokuConnection.insertUser("user_name", "user_pswd", "user_email", jsonCalendar, false);
  
        
        
        JSONObject getJsonCalendar = herokuConnection.getCalendarById(105);
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        calendar c_1 = (calendar) objectMapper.readValue(getJsonCalendar.toJSONString(), calendar.class);
        System.out.println("Calendario obtenido: " + c_1.toString());
        
    }
    
}
    