
package SqlDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public abstract class SqlConnection {
    
    public void SqlConnection (){

    }
    
    public abstract void selectById(int id);
    public abstract void selectAll();
    
    public Connection connect(String url, String user, String pswd) {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pswd);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al conectarse: " + e.getMessage());
        }
        return con;
    }
    
}
