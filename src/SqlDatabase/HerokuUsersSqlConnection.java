package SqlDatabase;

import java.sql.Connection;
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
    private String emailUser;
    
    PreparedStatement ps;
    ResultSet rs;
    
    private HerokuUsersSqlConnection(){}
    
    public static synchronized HerokuUsersSqlConnection getInstance(){
        if(instance == null){
            instance = new HerokuUsersSqlConnection();
            instance.getConexion();
        }
        return instance;
    }

    public void selectAllUsers() {
        String sql = "SELECT * FROM USERS";
        try (Connection conn = this.getConexion();
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
        Connection conn = getConexion();
        
        try{
            ps = conn.prepareStatement("SELECT * FROM USERS WHERE id=" + Integer.toString(id));
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                
                System.out.println(rs.getInt("id") + "\t" +
                rs.getString("name") + "\t" +
                rs.getString("password") + "\t" +
                rs.getString("email") + "\t" +
                rs.getString("calendar") + "\t" +
                rs.getString("login") 
            );
            } else {
                JOptionPane.showMessageDialog(null, "No existe ningún usuario con ese id");
                System.out.println("No existe ningún usuario con ese id");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al seleccionar por id  en la tabla USERS: " + e.getMessage());
        }

    }            
 
    public void deleteUserById(int id) {
        
        Connection conn = getConexion();
        
        try{
            ps = conn.prepareStatement("DELETE FROM USERS WHERE id=?");
            ps.setInt(1, id);
            
            int res = ps.executeUpdate();
            
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
                System.out.println("Usuario eliminado correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "Usuario eliminado incorrectamente");
                System.out.println("Usuario eliminado incorrectamente");
            }
            
            conn.close();
            
        }catch (SQLException e) {
                System.out.println("Error al eliminar por id en la tabla USERS: " + e.getMessage());
            }
    }
     
    public JSONObject getCalendarById(int id) throws ParseException {
        String sql = "SELECT calendar FROM USERS WHERE id=" + Integer.toString(id);
        JSONObject jsonObjectCalendar = null;
        String stringCalendar = null;
        try (Connection conn = this.getConexion();
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
        Connection conn = getConexion();        
        try{
            ps = conn.prepareStatement("INSERT INTO USERS(name, password, email, calendar, login) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, pswd);
            ps.setString(3, email);
            ps.setString(4, json.toJSONString());
            ps.setBoolean(5, login);
            // ps.execute();  
            int res = ps.executeUpdate();
            
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Usuario insertado correctamente");
                System.out.println("Usuario insertado correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "Usuario insertado incorrectamente");
                System.out.println("Usuario insertado incorrectamente");
            }
            
            conn.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar en la tabla USERS: " + e.getMessage());
            System.out.println("Error al insertar en la tabla USERS: " + e.getMessage());
        }
    }
    
    public boolean login(user user) throws SQLException {
        
        Connection conn = getConexion();
        try{
            ps = conn.prepareStatement("SELECT id, name, email, password, login FROM USERS WHERE email=?");
            ps.setString(1, user.getEmail());
            rs = ps.executeQuery();
            if (rs.next()) {
                if(user.getPwd().equals(rs.getString(4))){                     
                    ps = conn.prepareStatement("UPDATE USERS SET login=? WHERE email=?");
                    ps.setBoolean(1, true);
                    ps.setString(2, user.getEmail());
                    ps.execute();
                    
                    user.setId(rs.getInt(1));
                    user.setName(rs.getString(2));
                    user.setEmail(rs.getString(3));
                    user.setPwd(rs.getString(4));
                    user.setLogin(rs.getBoolean(5));
                    
                    user.toString();
                    
                    emailUser = user.getEmail();
                    return true;
                }
            }
        }catch (SQLException e) {
            System.out.println("Error al autentificar en la tabla USERS: " + e.getMessage());
        }
        return false;
    }

    public boolean signOut() throws SQLException {
        Connection conn = getConexion();
        try{
            ps = conn.prepareStatement("SELECT id, name, email, password, login FROM USERS WHERE email=?");
            ps.setString(1, emailUser);
            rs = ps.executeQuery();
            System.out.println("1");
            if (rs.next()) {
                System.out.println(rs.getString("email"));
                System.out.println(rs.getString("password"));
                System.out.println("2");
                     
                System.out.println("3");                    
                ps = conn.prepareStatement("UPDATE USERS SET login=? WHERE email=?");
                System.out.println("4");
                ps.setBoolean(1, false);
                ps.setString(2, emailUser);
                ps.execute();
                System.out.println("5");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al autentificar en la tabla USERS: " + e.getMessage());
        }
        return false;
    }
}
