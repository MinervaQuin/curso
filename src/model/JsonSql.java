
package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonSql {
    
    
    public static JSONObject getListJson(List<Object> c) throws ParseException{
        JSONObject json = new JSONObject();
        
        for(Object c1: c){
            json.put(c1.toString(), c1);
        }
                
        String jsonString = new Gson().toJson(json);
        JSONObject jsonList = (JSONObject) new JSONParser().parse(jsonString);
        return jsonList;
    }
    
        
}
