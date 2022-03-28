
package calendar;

import SqlDatabase.HerokuCalendarSqlConnection;
import SqlDatabase.HerokuTaskSqlConnection;
import SqlDatabase.HerokuUserSqlConnection;
import design.Login;
import model.Task;
import model.User;
import model.CalendarTask;

public class Calendar {

    public static void main(String[] args) {
        // Login lg = new Login();
        // lg.setVisible(true);

        
        HerokuUserSqlConnection userConnection = HerokuUserSqlConnection.getInstance();
        HerokuTaskSqlConnection taskConnection = HerokuTaskSqlConnection.getInstance();
        HerokuCalendarSqlConnection calConnection = HerokuCalendarSqlConnection.getInstance();
        
        //userConnection.insertUser("name", "pwd", "email", false);
        //taskConnection.insertTask("Tarea");
        //calConnection.insertCalendar("Calendar");
 
        userConnection.selectUserById(1);
        taskConnection.selectTaskById(1);
        calConnection.selectCalendarById(1);
        
        
    }
}