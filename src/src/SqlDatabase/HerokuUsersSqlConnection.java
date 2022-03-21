package SqlDatabase;

import SqlDatabase.SqlConnection;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class HerokuUsersSqlConnection extends SqlConnection{
        
    private static HerokuUsersSqlConnection instance;
    
    private HerokuUsersSqlConnection(){}
    
    public static synchronized HerokuUsersSqlConnection getInstance(String url, String user, String pswd){
        if(instance == null){
            instance = new HerokuUsersSqlConnection();
            instance.SqlConnection(url, user, pswd);
        }
        return instance;
    }


    public void selectAllUsers() {
        String sql = "SELECT * FROM USERS";
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
            System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("password") + "\t" +
                        rs.getString("email") + "\t" +
                        rs.getString("calendar") 
            );
            }
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo en la tabla USERS: " + e.getMessage());
        }
    }

    public void selectUserById(int id) {
        
        String sql = "SELECT * FROM USERS WHERE id=" + Integer.toString(id);

        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
            System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("password") + "\t" +
                        rs.getString("email") + "\t" +
                        rs.getString("calendar") + "\t"
            );
            }
        } catch (SQLException e) {
            System.out.println("Error al seleccionar por id  en la tabla USERS: " + e.getMessage());
        }

    }            
 
    public void deleteUserById(int id) {
        String sql = "DELETE FROM USERS WHERE id=?";
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
            System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("password") + "\t" +
                        rs.getString("email") + "\t" +
                        rs.getString("calendar") 
            );
            }
        } catch (SQLException e) {
                System.out.println("Error al eliminar por id en la tabla USERS: " + e.getMessage());
            }
    }
    
    public JSONObject getCalendarById(int id) {
        String sql = "SELECT calendar FROM USERS WHERE id=" + Integer.toString(id);
        JSONObject jsonObjectCalendar = null;
        String stringCalendar = null;
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if(rs.next()){stringCalendar = rs.getString("calendar");}

            jsonObjectCalendar = (JSONObject) new JSONParser().parse(stringCalendar);
                        
        } catch (SQLException e) {
                System.out.println("Error al obtener calendario por id en la tabla USERS: " + e.getMessage());
            } catch (ParseException ex) {
            Logger.getLogger(HerokuUsersSqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return jsonObjectCalendar;
    }
    
    public void insertUser(String name, String pswd, String email, JSONObject json) {
        String sql = "INSERT INTO USERS(name, password, email, calendar) VALUES(?, ?, ?, ?)";

        
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, pswd);
            pstmt.setString(3, email);
            pstmt.setString(4, json.toJSONString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
                System.out.println("Error al insertar en la tabla USERS: " + e.getMessage());
            }
    }
    
}   