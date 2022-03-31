
package calendar;

import SqlDatabase.HerokuCalendarPermitSqlConnection;
import SqlDatabase.HerokuCalendarSqlConnection;
import SqlDatabase.HerokuTaskSqlConnection;
import SqlDatabase.HerokuUsersSqlConnection;
import design.Login;
import model.Task;
import model.User;
import model.CalendarTask;

public class Calendar {

    public static void main(String[] args) {
        // Login lg = new Login();
        // lg.setVisible(true);

        
        HerokuUsersSqlConnection userConnection = HerokuUsersSqlConnection.getInstance();
        HerokuTaskSqlConnection taskConnection = HerokuTaskSqlConnection.getInstance();
        HerokuCalendarSqlConnection calConnection = HerokuCalendarSqlConnection.getInstance();
        HerokuCalendarPermitSqlConnection calPermitConnection = HerokuCalendarPermitSqlConnection.getInstance();
        
        User user = new User("name", "pwd", "email");
        Task task = new Task("Tarea");
        CalendarTask calendar = new CalendarTask("Calendar");

        User user1 = new User("name1", "pwd1", "email1");
        Task task1 = new Task("Tarea1");
        CalendarTask calendar1 = new CalendarTask("Calendar1");
        
        User user2 = new User("name2", "pwd2", "email2");
        Task task2 = new Task("Tarea2");
        CalendarTask calendar2 = new CalendarTask("Calendar2");

        //userConnection.insertUser(user.getName(), user.getPwd(), user.getEmail(), false);
        //taskConnection.insertTask("Tarea");
        //calConnection.insertCalendar("Calendar");
        
        // insertCalendarPermit - Integer user_id, Integer calendar_id, Integer task_id, String rol
        
        //calPermitConnection.insertCalendarPermit(8, 1, 1, "Admin");
        //calPermitConnection.insertCalendarPermit(9, 1, 1, "Editor");

        //calPermitConnection.insertCalendarPermit(10, 2, 1, "Admin");
        //calPermitConnection.insertCalendarPermit(8, 2, 1, "Lector");
  
        //calPermitConnection.insertCalendarPermit(10, 3, 3, "Admin");
        //calPermitConnection.insertCalendarPermit(10, 1, 2, "Editor");
        
        //userConnection.selectUserById(1);
        //taskConnection.selectTaskById(1);
        //calConnection.selectCalendarById(1);
        
        System.out.println("Seleccionar todos permnisos de calendario:");        
        calPermitConnection.selectAllCalendarsPermits();
        System.out.println("-------------------------------------------------");                
        
        System.out.println("Seleccionar permnisos de calendario por id de calendario:");
        calPermitConnection.selectCalendarPermitByCalendarId(1);       
        System.out.println("-------------------------------------------------");                

        
        System.out.println("Seleccionar tareas de uno o varios calendarios por id de calendario:");
        calPermitConnection.selectTaskByCalendarId(1);
        System.out.println("-------------------------------------------------");                
        
        
        System.out.println("Seleccionar calendario(s) donde encuentre la id de una tarea:");
        calPermitConnection.selectCalendarByTaskId(2);
        System.out.println("-------------------------------------------------");                
    
        System.out.println("Seleccionar todos los permisos de los calendarios por id de usuario:");
        calPermitConnection.selectAllCalendarsPermitsByIdUser(10);
        System.out.println("-------------------------------------------------");                
        
        System.out.println("Seleccionar permisos usuarios por id de calendario:");
        calPermitConnection.selectUsersPermitsByCalendarId(1);
        System.out.println("-------------------------------------------------");                
        
    }
}