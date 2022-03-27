package SqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class HerokuUsersSqlConnection extends SqlConnection{
        
    private static HerokuUsersSqlConnection instance;
    private String emailUser;
    
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
     
    public JSONObject getCalendarById(int id) throws ParseException {
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
            }
        
        return jsonObjectCalendar;
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
    
    public boolean login(String email, String pswd) throws SQLException {
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = this.connect();
        
        String sql = "SELECT email, password, login FROM USERS WHERE email=?";
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                System.out.println(rs.getString("email"));
                System.out.println(rs.getString("password"));
                if(rs.getString(2).equals(pswd)){ 
                    
                    String sqlUpdate = "UPDATE USERS SET login=? WHERE email=?";
                    
                    ps = conn.prepareStatement(sqlUpdate);
                    ps.setBoolean(1, true);
                    ps.setString(2, email);
                    ps.execute();
                    
                    emailUser = email;
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al autentificar en la tabla USERS: " + e.getMessage());
        }
        return false;
    }

    public boolean signOut() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = this.connect();
        
        String sql = "SELECT login FROM USERS WHERE email=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, emailUser);
            rs = ps.executeQuery();
            if (rs.next()) {
                String sqlUpdate = "UPDATE USERS SET login=? WHERE email=?";
                
                ps = conn.prepareStatement(sqlUpdate);
                ps.setBoolean(1, false);
                ps.setString(2, emailUser);
                ps.execute();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al autentificar en la tabla USERS: " + e.getMessage());
        }
        return false;
    }
}
