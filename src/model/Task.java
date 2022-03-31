package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class Task {
    
    @JsonProperty("name")
    private String name;
    
    public Task () {}
   
    public Task (String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }

}