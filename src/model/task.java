package model;

import java.util.Date;

public class task {
    
    private Date d;
    private String name;
    
    public task (Date d, String name) {
        this.d = d;
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Date getDate() {
        return this.d;
    }
}
