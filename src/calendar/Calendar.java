
package calendar;

import SqlDatabase.HerokuCalendarPermitSqlConnection;
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
        HerokuCalendarPermitSqlConnection calPermitConnection = HerokuCalendarPermitSqlConnection.getInstance();
        
        //User user = new User("name", "pwd", "email");
        //Task task = new Task("Tarea");
        //CalendarTask calendar = new CalendarTask("Calendar");

        
        //userConnection.insertUser("name", "pwd", "email", false);
        //taskConnection.insertTask("Tarea");
        //calConnection.insertCalendar("Calendar");
        //calPermitConnection.insertCalendarPermit(1, 1, 1, "Admin");
        
        //userConnection.selectUserById(1);
        //taskConnection.selectTaskById(1);
        //calConnection.selectCalendarById(1);
        
        calPermitConnection.selectCalendarPermitByCalendarId(1);
        
        
    }
}