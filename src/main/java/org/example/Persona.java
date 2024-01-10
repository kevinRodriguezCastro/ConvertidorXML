package org.example;

public class Persona {
    private String nombre;
    private String apellidos;
    private int edad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String xml(){
        return "<Persona>\n\t<Nombre> "+this.getNombre()+" </Nombre>\n\t<Apellidos> "+this.getApellidos()+" </Apellidos>\n\t<Edad> "+this.getEdad()+" </Edad>\n</Persona>";
    }
    public String json() {
        return "{\n\t'Nombre':'"+this.getNombre()+"',\n\t'Apellidos':'"+this.getApellidos()+"',\n\t'Edad':'"+this.getEdad()+"'\n}";
    }
    public String yaml(){
        return "Persona:\n\tNombre:"+this.getNombre()+"\n\tApellidos:"+this.getApellidos()+"\n\tEdad:"+this.getEdad();
    }
}
