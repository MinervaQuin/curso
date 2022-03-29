
package SqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import model.CalendarTask;
import model.User;


public class HerokuCalendarPermitSqlConnection extends SqlConnection {
    
    private static HerokuCalendarPermitSqlConnection instance;
    
    private HerokuCalendarPermitSqlConnection(){}
    
    public static synchronized HerokuCalendarPermitSqlConnection getInstance(){
        if(instance == null){
            instance = new HerokuCalendarPermitSqlConnection();
            instance.getSqlConnection();
        }
        return instance;
    }    
    
    
    public void selectAllCalendarsPermits() {
        String sql = "SELECT * FROM calendar_permit";
        try (Connection conn = this.getSqlConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
            System.out.println(
                    rs.getInt("user_id") + "\t" +
                    rs.getString("calendar_id") + "\t" +
                    rs.getString("task_id") + "\t" +
                    rs.getString("rol")       
            );
            }
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo en la tabla CALENDAR_PERMIT: " + e.getMessage());
        }
    }
    
    public void insertCalendarPermit(Integer user_id, Integer calendar_id, Integer task_id, String rol) {
        Connection conn = getSqlConnection();        
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO calendar_permit(user_id, calendar_id, task_id, rol) VALUES(?,?,?,?)");
            ps.setInt(1, user_id);
            ps.setInt(2, calendar_id);
            ps.setInt(3, task_id);
            ps.setString(4, rol);
            // ps.execute();  
            int res = ps.executeUpdate();
            
            if(res > 0){
                //JOptionPane.showMessageDialog(null, "Permiso de calendario al usuario insertado correctamente");
                System.out.println("Permiso de calendario al usuario insertado correctamente");
            }else{
                //JOptionPane.showMessageDialog(null, "Permiso de calendario al usuario insertado incorrectamente");
                System.out.println("Permiso de calendario al usuario insertado incorrectamente");
            }
            
            conn.close();
            
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Error al insertar en la tabla CALENDAR_PERMIT: " + e.getMessage());
            System.out.println("Error al insertar en la tabla CALENDAR_PERMIT: " + e.getMessage());
        }
    }

    
    public void selectCalendarPermitByCalendarId(int id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM calendar_permit WHERE calendar_id=" + Integer.toString(id));
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                
                System.out.println(
                        "Id de usuario: " +
                        rs.getInt("user_id") + "\t" +
                        "Id de calendario: " +
                        rs.getInt("calendar_id") + "\t" +
                        "Rol: " +
                        rs.getString("rol") + "\t"
            );
            } else {
                //JOptionPane.showMessageDialog(null, "No existe ningún permiso para ese calendario");
                System.out.println("No existe ningún permiso para ese calendario");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al seleccionar por id del calendario en la tabla CALENDAR_PERMIT: " + e.getMessage());
        }

    }            
 
    public void deleteCalendarPermitById(int id) {
        
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM calendar_permit WHERE calendar_id=?");
            ps.setInt(1, id);
            
            int res = ps.executeUpdate();
            
            if(res > 0){
                //JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
                System.out.println("Tarea eliminado correctamente");
            }else{
                //JOptionPane.showMessageDialog(null, "Usuario eliminado incorrectamente");
                System.out.println("Tarea eliminado incorrectamente");
            }

            conn.close();
            
        }catch (SQLException e) {
                System.out.println("Error al eliminar por id en la tabla CALENDAR_PERMIT: " + e.getMessage());
            }
    }
    
    
}
