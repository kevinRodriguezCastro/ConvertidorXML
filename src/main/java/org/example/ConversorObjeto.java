package org.example;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class ConversorObjeto {

    // Sax
    // gson
    public ConversorObjeto() {

    }

    public String jsonObjeto(String json,String extension){
        Gson g = new Gson();
        Persona[] personas = g.fromJson(json,Persona[].class);

        switch (extension){
            case "xml":
                return xml(personas);
            case "yaml":
                return yaml(personas);
        }
        return null;
    }

    public String xmlObjeto(String xml, String extension){
        PersonaHandler handler = new PersonaHandler();
        ArrayList<Persona> personas;

        try {
            personas = (ArrayList<Persona>) handler.parse(xml);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        switch (extension){
            case "json":
                return json(personas);
            case "yaml":
                return yaml(personas);
        }
        return null;
    }
    public String yamlObjeto(String yaml, String extension){
        Persona personaActual = null;
        ArrayList<Persona> personas = new ArrayList<>();

        for (String linea : yaml.split("\n")) {
            if (linea.startsWith("-")) {
                if (personaActual != null) {
                    personas.add(personaActual);
                }
                personaActual = new Persona();
                if (personaActual != null) {
                    String[] partes = linea.trim().split(":");
                    if (partes.length == 2) {
                        String valor = partes[1].trim();
                        personaActual.setNombre(valor);
                    }
                }
            } else if (linea.trim().startsWith("apellidos:")) {
                if (personaActual != null) {
                    String valor = linea.trim().substring("apellidos:".length()).trim();
                    personaActual.setApellidos(valor);
                }
            } else if (linea.trim().startsWith("edad:")) {
                if (personaActual != null) {
                    String valor = linea.trim().substring("edad:".length()).trim();
                    if (esNumero(valor)) {
                        personaActual.setEdad(Integer.parseInt(valor));
                    }
                }
            }
        }

        if (personaActual != null) {
            personas.add(personaActual);
        }

        switch (extension){
            case "xml":
                return xml(personas);
            case "json":
                return json(personas);
        }
        return null;
    }



    private String xml(ArrayList<Persona> personas){
        String contenido = "";
        for (Persona tmp : personas){
            contenido += tmp.xml()+"\n";
        }
        return "<personas>\n"+contenido+"</personas>";
    }
    private String xml(Persona[] personas){
        String contenido = "";
        for (Persona tmp : personas){
            contenido += tmp.xml()+"\n";
        }
        return "<personas>\n"+contenido+"</personas>";
    }
    private String json(ArrayList<Persona> personas){
        String contenido = "";
        if (personas.size()==1){
            return personas.get(0).json();
        }else {
            for (Persona tmp : personas) {
                contenido += tmp.json();
                contenido += ",\n";
            }
            if (!contenido.isEmpty()) {
                contenido = contenido.substring(0, contenido.length() - 2);
            }
            return "[\n" + contenido + "\n]";
        }
    }
    private String yaml(ArrayList<Persona> personas){
        String contenido = "";
        for (Persona tmp : personas){
            contenido += tmp.yaml()+"\n";
        }
        return "persona:\n"+contenido;
    }
    private String yaml(Persona[] personas){
        String contenido = "";
        for (Persona tmp : personas){
            contenido += tmp.yaml()+"\n";
        }
        return "persona:\n"+contenido;
    }
    public String extensionArchivo(String nombreArchivo){
        int ultimaPosicion = nombreArchivo.lastIndexOf(".");
        return nombreArchivo.substring(ultimaPosicion+1);
    }

    private boolean esNumero(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
