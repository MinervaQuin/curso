
package SqlDatabase;


public class HerokuRelationalSqlConnection extends SqlConnection {
    
    private static HerokuRelationalSqlConnection instance;
    
    private HerokuRelationalSqlConnection(){}
    
    public static synchronized HerokuRelationalSqlConnection getInstance(){
        if(instance == null){
            instance = new HerokuRelationalSqlConnection();
            instance.getSqlConnection();
        }
        return instance;
    }    
    
}
