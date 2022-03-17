/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class dateTasks {
    
    private List<task> tasks = new ArrayList<task>();
    private Date d;
    
    public dateTasks (Date d, task t) {
        this.d = d;
        tasks.add(t);
    }
    
    public void addTask (task t) {
        tasks.add(t);
    }
}
