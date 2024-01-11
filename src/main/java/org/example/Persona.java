package org.example;

public class Persona {
    private String nombre;
    private String apellidos;
    private int edad;

    public Persona() {
    }

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
        return "\t<persona>\n\t\t<nombre> "+this.getNombre()+" </nombre>\n\t\t<apellidos> "+this.getApellidos()+" </apellidos>\n\t\t<edad> "+this.getEdad()+" </edad>\n\t</persona>";
    }
    public String json() {
        return "{\"nombre\":\"" + this.getNombre() + "\",\"apellidos\":\"" + this.getApellidos() + "\",\"edad\":\"" + this.getEdad() + "\"}";
        //return "{\"persona\": {\"nombre\":\"" + this.getNombre() + "\",\"apellidos\":\"" + this.getApellidos() + "\",\"edad\":\"" + this.getEdad() + "\"}}";
    }
    public String yaml(){
        return "-\tnombre:"+this.getNombre()+"\n\tapellidos:"+this.getApellidos()+"\n\tedad:"+this.getEdad();
    }
}
