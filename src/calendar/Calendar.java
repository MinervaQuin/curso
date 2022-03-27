
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
import model.CalendarTask;
import model.DateTasks;
import model.Task;
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
                
        /*
        Una columna para cada permiso de calendario -> json owner, json lector, json editor ¿?
        
        repetir los json por cada usuario invitado no es una vía
        pues si lo modifica un editor, hay que modificarlo para todos los usurios accediendo
        a la tabla tantas veces como usuario tenga acceso
        
        -> por tanto es mejor tener una tabla separar con punteros desde los
        usuarios que tenga acceso
        
        -> una columna para los json owner, json editor, json lector
        y en cada columa el id del usuario
        
        -> p.e: usuario con id en clumna json editor apunta al mismo calendario
        que otro tenga en su columna json owner
        
        */
        
        CalendarTask calendar1 = new CalendarTask("calendar n1");
        CalendarTask calendar2 = new CalendarTask("calendar n2");
        CalendarTask calendar3 = new CalendarTask("calendar n3");

        String stringDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = formatter.parse(stringDate);


        // System.out.println("(Date type) -> " + date + " (String type)-> " + stringDate); 
        
        calendar1.addTask(stringDate, new  Task("Mi tarea n1 para calendar1"));
        calendar1.addTask(stringDate, new  Task("Mi tarea n2 para calendar1"));
        calendar2.addTask(stringDate, new  Task("Mi tarea n1 para calendar2"));
        calendar3.addTask(stringDate, new  Task("Mi tarea n1 para calendar3"));

        
        List<Object> test = new ArrayList<>();
        test.add(calendar3);
        

        JSONObject writeJsonCalendar = (JSONObject) JsonSql.getListJson(test);
        herokuConnection.insertUser("listJson5", "listJson5", "listJson5", writeJsonCalendar, false);
                        
        JSONObject getJsonCalendar = herokuConnection.getCalendarById(44);
        
        
        CalendarTask c_1 = (CalendarTask) CalendarTask.getCalendarFromJson(getJsonCalendar, "calendar n3");
        System.out.println("Calendario obtenido: " + c_1.getName());
        
        
        
        // encapsular json en CalendarTask
        
        // herokuConnection.insertUser("listJson4", "listJson4", "listJson4", jsonCalendarTest, false);
        
        // herokuConnection.selectAllUsers();
        
    }
    
}
    