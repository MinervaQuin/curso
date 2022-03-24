
package model;

import com.google.gson.Gson;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonSql {
    
    
    public static JSONObject getListJson(List<calendar> objects) throws ParseException{
        JSONObject json = new JSONObject();
        
        objects.forEach((object) -> {
            json.put(object.toString(), object);
        });
        
        String jsonString = new Gson().toJson(json);
        JSONObject jsonList = (JSONObject) new JSONParser().parse(jsonString);
        return jsonList;
    }
    
}
