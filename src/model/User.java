package model;

public class User {
    
    private final int id;
    private final String name;
    private final String pwd;
    private final String email;
    
    public User (Integer id, String name, String email, String pwd) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pwd = pwd;       
    }
    
    public User (Integer id, String email, String pwd) {
        this.id = id;
        this.name = email;
        this.email = email;
        this.pwd = pwd;
    }

    
    
    @Override
    public String toString(){
        return "ID: " + id + ", Nombre: " + name + ", Email: " + email + ", Contraseña: " + pwd;
    }
    
}
