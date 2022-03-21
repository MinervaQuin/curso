
package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class task {
    
    @JsonProperty("name")
    private String name;
    
    public task () {}
   
    public task (String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }

}
