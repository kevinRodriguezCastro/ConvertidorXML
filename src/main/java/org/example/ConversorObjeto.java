package org.example;

import com.google.gson.Gson;

public class ConversorObjeto {

    // Sax
    // gson
    private Persona persona;
    public void jsonObjeto(String json){
        Gson g = new Gson();
        persona = g.fromJson(json,Persona.class);
    }
    public void xmlObjeto(String xml){
        PersonaHandler handler = new PersonaHandler();
        try {
            handler.parse(xml);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void yamlObjeto(String yaml){

    }

    public String xml(){
        return "<Persona>\n\t<Nombre> "+persona.getNombre()+" </Nombre>\n\t<Apellidos> "+persona.getApellidos()+" </Apellidos>\n\t<Edad> "+persona.getEdad()+" </Edad>\n</Persona>";
    }
    public String json() {
        return "{\n\t'Nombre':'"+persona.getNombre()+"',\n\t'Apellidos':'"+persona.getApellidos()+"',\n\t'Edad':'"+persona.getEdad()+"'\n}";
    }
    public String yaml(){
        return "Persona:\n\tNombre:"+persona.getNombre()+"\n\tApellidos:"+persona.getApellidos()+"\n\tEdad:"+persona.getEdad();
    }
}
