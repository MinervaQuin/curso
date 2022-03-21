package SqlDatabase;

import SqlDatabase.SqlConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONObject;


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
                        rs.getString("calendar") + "\t" +
                        rs.getString("login") 
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
                        rs.getString("calendar") + "\t" +
                        rs.getString("login") 
            );
            }
        } catch (SQLException e) {
            System.out.println("Error al seleccionar por id  en la tabla USERS: " + e.getMessage());
        }

    }            
 
    public void deleteUserById(int id) {
        String sql = "DELETE FROM USERS WHERE id=?";
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
                System.out.println("Error al eliminar por id en la tabla USERS: " + e.getMessage());
            }
    }
        
    public void insertUser(String name, String pswd, String email, JSONObject json, boolean login) {
        String sql = "INSERT INTO USERS(name, password, email, calendar, login) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, pswd);
            pstmt.setString(3, email);
            pstmt.setString(4, json.toJSONString());
            pstmt.setBoolean(5, login);
            pstmt.executeUpdate();
        } catch (SQLException e) {
                System.out.println("Error al insertar en la tabla USERS: " + e.getMessage());
            }
    }
    
}
