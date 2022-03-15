
package SqlDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class HerokuMySqlConnection extends SqlConnection{
        
    private static HerokuMySqlConnection instance;
    
    private final String url;
    private final String user;
    private final String pswd;
    
    private HerokuMySqlConnection(){
        url = "jdbc:mysql://eu-cdbr-west-02.cleardb.net:3306/heroku_fc7dd2b1a888efb";
        user = "b75ef6e13a3c31";
        pswd = "81304c03";    
        connect();
    }
    
    public static synchronized HerokuMySqlConnection getInstance(){
        if(instance == null){
            instance = new HerokuMySqlConnection();
        }
        return instance;
    }
    
    private Connection connect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pswd);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al conectarse: " + e.getMessage());
        }
        return con;
    }

    @Override
    public void selectAll() {
        String sql = "SELECT * FROM USERS";
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
            System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("password") + "\t" +
                        rs.getString("email") + "\t");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
      
    @Override
    public void selectById(int id) {
        
        String sql = "SELECT * FROM USERS WHERE id=" + Integer.toString(id);

        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
            System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("password") + "\t" +
                        rs.getString("email") + "\t");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }            
        
    public void insert(String name, String pswd, String email) {
        String sql = "INSERT INTO USERS(name, password, email) VALUES(?, ?, ?)";
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, pswd);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
                System.out.println("Error al insertar en USERS: " + e.getMessage());
            }
    }
        
}

