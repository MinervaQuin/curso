package model;

public class user {
    
    private final int id;
    private final String name;
    private final String pwd;
    private final String email;
    
    public user (Integer id, String name, String email, String pwd) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pwd = pwd;       
    }
    
    public user (Integer id, String email, String pwd) {
        this.id = id;
        this.name = email;
        this.email = email;
        this.pwd = pwd;
    }

    
    
<<<<<<< Updated upstream
    @Override
    public String toString(){
        return "ID: " + id + ", Nombre: " + name + ", Email: " + email + ", Contraseña: " + pwd;
=======
    public user (int id, String name, String pwd, String email) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.email = email;
    }
 
    public user (int id, String pwd, String email) {
        this.id = id;
        this.name = email;
        this.pwd = pwd;
        this.email = email;
>>>>>>> Stashed changes
    }
    
}
