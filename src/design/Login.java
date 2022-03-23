package SqlDatabase;

import SqlDatabase.SqlConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.user;
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
    
    /*public boolean login(String email, String pswd) throws SQLException {
        // Por alguna razón, da error si el email contiene una '@'
        // Ejemplo: Si un usuario tiene de email 123@email, aunque exista en
        // la base de datos, la consulta da error.
        // Por tanto, probar sin caracteres especiales
        
        String sql = "SELECT id, email, password, login FROM USERS WHERE email=" + email + " AND password=" + pswd;
        
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                if(rs.getString(2).equals(email) && rs.getString(3).equals(pswd)){
                    String Ssql = "UPDATE USERS SET login=? WHERE email=" + email;
                    PreparedStatement prest = conn.prepareStatement(Ssql);
                    prest.setBoolean(5, true);
                    prest.setInt(5, true);
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al autentificar en la tabla USERS: " + e.getMessage());
        }
        return false;
    }*/
    
    public boolean login(String email, String pswd) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null; 
        Connection conn = this.connect();
        String sql = "SELECT id, email, password, login FROM USERS WHERE email= ?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                if(pswd.equals(rs.getString(3))){
                    String sqlUpdate = "UPDATE USERS SET login=? WHERE email= ?";
                    ps = conn.prepareStatement(sqlUpdate);
                    ps.setBoolean(1, rs.getBoolean(1));
                    ps.setString(2, email);
                    ps.execute();
                    
                    
                    /*
                    Cambiar también los atributos de la clase user
                    */
                    
                    return true;
                }else {
                    JOptionPane.showMessageDialog(null, "La contraseña no existe");
                    System.out.println("La contraseña no existe");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "El correo no existe");
                System.out.println("El correo no existe");
                return false;
            }
        }catch (SQLException e) {
            System.out.println("Error al autentificar en la tabla USERS: " + e.getMessage());
            return false;
        }
    }
    

    public boolean signOff(String email) throws SQLException {
        String sql = "SELECT DISTINCT id, email FROM USERS WHERE email=" + email;
        
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                System.out.println(rs.getString("email"));
                System.out.println(rs.getString("password"));
                if(rs.getString("email").equals(email)){ 
                    signOff2(email);
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al autentificar en la tabla USERS: " + e.getMessage());
        }
        return false;
    }

    private void signOff2(String email){
        String Ssql = "UPDATE USERS SET login=" + true + "WHERE email=" + email;
        try (Connection conn = this.connect()){
            PreparedStatement prest = conn.prepareStatement(Ssql);
            prest.setBoolean(5, false);
        } catch (SQLException ex) {
            Logger.getLogger(HerokuUsersSqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
