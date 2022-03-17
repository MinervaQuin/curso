
package SqlDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public abstract class SqlConnection {

    protected String url;
    protected String user;
    protected String pswd;
    
    public void SqlConnection (String url, String user, String pswd){
        this.url = url;
        this.user = user;
        this.pswd = pswd; 
    }
        
    public Connection connect() {
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
