
package calendar;

import SqlDatabase.HerokuUsersSqlConnection;


public class Calendar {


    public static void main(String[] args) {
        
        String url = "jdbc:mysql://eu-cdbr-west-02.cleardb.net:3306/heroku_fc7dd2b1a888efb";
        String user = "b75ef6e13a3c31";
        String pswd = "81304c03"; 
        
        HerokuUsersSqlConnection herokuConnection = HerokuUsersSqlConnection.getInstance(url, user, pswd);
        // herokuConnection.insertUser("fromMyPc", "fromMyPc", "fromMyPc");
        herokuConnection.selectAllUsers();
        // herokuConnection.deleteUserById(11);
        
    }
    
}
