
package calendar;

import SqlDatabase.HerokuUsersSqlConnection;
import org.json.simple.JSONObject; 

public class Calendar {


    public static void main(String[] args) {
        
        String url = "jdbc:mysql://eu-cdbr-west-02.cleardb.net:3306/heroku_fc7dd2b1a888efb";
        String user = "b75ef6e13a3c31";
        String pswd = "81304c03"; 
 
        String url_alternative = "jdbc:mysql://i54jns50s3z6gbjt.chr7pe7iynqr.eu-west-1.rds.amazonaws.com/iy5jkxqnaep9jd04";
        String user_alternative = "e3uv1gsfsu6r8v6j";
        String pswd_alternative = "ud6g593tlbsh3knw"; 
        
        
        HerokuUsersSqlConnection herokuConnection = HerokuUsersSqlConnection.getInstance(url_alternative + "?useSSL=false", user_alternative, pswd_alternative);
        // herokuConnection.insertUser("fromMyPc", "fromMyPc", "fromMyPc");
        
        // herokuConnection.deleteUserById(11);
        
        JSONObject json = new JSONObject();
        json.put("Json test", "This is a Json test");
        
        for (int i = 0; i < 125; i++) {
            herokuConnection.insertUser("testingUsersSize", "testingUsersSize", "testingUsersSize", json);
        }
        
        herokuConnection.selectAllUsers();
        
    }
    
}
    