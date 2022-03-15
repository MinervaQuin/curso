
package calendar;

import SqlDatabase.HerokuMySqlConnection;


public class Calendar {


    public static void main(String[] args) {
        
        HerokuMySqlConnection herokuConnection = HerokuMySqlConnection.getInstance();
        
        herokuConnection.selectAll();
        
    }
    
}
