package SqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class HerokuUserSqlConnection extends SqlConnection{
        
    private static HerokuUserSqlConnection instance;
    private String emailUser;
        
    private HerokuUserSqlConnection(){}
    
    public static synchronized HerokuUserSqlConnection getInstance(){
        if(instance == null){
            instance = new HerokuUserSqlConnection();
            instance.getSqlConnection();
        }
        return instance;
    }

    public void selectAllUsers() {
        String sql = "SELECT * FROM users";
        try (Connection conn = this.getSqlConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
            System.out.println(rs.getInt("user_id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("password") + "\t" +
                        rs.getString("email") + "\t" +
                        rs.getString("login") 
            );
            }
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo en la tabla USER: " + e.getMessage());
        }
    }

    public void selectUserById(int id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE user_id=" + Integer.toString(id));
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                
                System.out.println(rs.getInt("user_id") + "\t" +
                rs.getString("name") + "\t" +
                rs.getString("password") + "\t" +
                rs.getString("email") + "\t" +
                rs.getString("login") 
            );
            } else {
                //JOptionPane.showMessageDialog(null, "No existe ningún usuario con ese id");
                System.out.println("No existe ningún usuario con ese id");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al seleccionar por id  en la tabla USER: " + e.getMessage());
        }

    }            
 
    public void deleteUserById(int id) {
        
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM user WHERE user_id=?");
            ps.setInt(1, id);
            
            int res = ps.executeUpdate();
            
            if(res > 0){
                //JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
                System.out.println("Usuario eliminado correctamente");
            }else{
                //JOptionPane.showMessageDialog(null, "Usuario eliminado incorrectamente");
                System.out.println("Usuario eliminado incorrectamente");
            }
            
            conn.close();
            
        }catch (SQLException e) {
                System.out.println("Error al eliminar por id en la tabla USER: " + e.getMessage());
            }
    }
    
    
    public void insertUser(String name, String pswd, String email, boolean login) {
        Connection conn = getSqlConnection();        
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO user(name, password, email, login) VALUES(?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, pswd);
            ps.setString(3, email);
            ps.setBoolean(4, login);
            // ps.execute();  
            int res = ps.executeUpdate();
            
            if(res > 0){
                //JOptionPane.showMessageDialog(null, "Usuario insertado correctamente");
                System.out.println("Usuario insertado correctamente");
            }else{
                //JOptionPane.showMessageDialog(null, "Usuario insertado incorrectamente");
                System.out.println("Usuario insertado incorrectamente");
            }
            
            conn.close();
            
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Error al insertar en la tabla user: " + e.getMessage());
            System.out.println("Error al insertar en la tabla users: " + e.getMessage());
        }
    }
    
    public boolean login(User user) throws SQLException {
        
        Connection conn = getSqlConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT user_id, name, email, password, login FROM user WHERE email=?");
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if(user.getPwd().equals(rs.getString(4))){                     
                    ps = conn.prepareStatement("UPDATE users SET login=? WHERE email=?");
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
            System.out.println("Error al autentificar en la tabla user: " + e.getMessage());
        }
        return false;
    }

    public boolean signOut() throws SQLException {
        Connection conn = getSqlConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT user_id, name, email, password, login FROM users WHERE email=?");
            ps.setString(1, emailUser);
            ResultSet rs = ps.executeQuery();
            System.out.println("1");
            if (rs.next()) {
                System.out.println(rs.getString("email"));
                System.out.println(rs.getString("password"));
                System.out.println("2");
                     
                System.out.println("3");                    
                ps = conn.prepareStatement("UPDATE users SET login=? WHERE email=?");
                System.out.println("4");
                ps.setBoolean(1, false);
                ps.setString(2, emailUser);
                ps.execute();
                System.out.println("5");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al autentificar en la tabla USER: " + e.getMessage());
        }
        return false;
    }
}