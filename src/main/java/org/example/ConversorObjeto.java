package org.example;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ConversorObjeto {

    // Sax
    // gson
    public ConversorObjeto() {

    }

    public String jsonObjeto(String json,String extension){
        Gson g = new Gson();
        Persona[] personas = g.fromJson(json,Persona[].class);

        String contenido = "";

        if (extension.equalsIgnoreCase("xml")){
            for (Persona tmp : personas){
                contenido += tmp.xml()+"\n";
            }
            return "<personas>\n"+contenido+"</personas>";
        }else if (extension.equalsIgnoreCase("yaml")){
            for (Persona tmp : personas){
                 contenido += tmp.yaml()+"\n";
            }
            return "persona:\n"+contenido;
        }
        return null;
    }

    public String xmlObjeto(String xml, String extension){
        PersonaHandler handler = new PersonaHandler();
        ArrayList<Persona> personas;

        String contenido = "";

        try {
            personas = (ArrayList<Persona>) handler.parse(xml);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (extension.equalsIgnoreCase("json")){
            if (personas.size()==1){
                return personas.get(0).json();
            }else {
                for (Persona tmp:personas){
                    contenido += tmp.json();
                    contenido +=",";
                }
                if (!contenido.isEmpty()) {
                    contenido = contenido.substring(0, contenido.length() - 1);
                }
                return "["+contenido+"]";
            }
        }else if (extension.equalsIgnoreCase("yaml")){
            for (Persona tmp : personas){
                contenido += tmp.yaml()+"\n";
            }
            return "persona:\n"+contenido;
        }
        return null;
    }
    public String yamlObjeto(String yaml, String extension){
        boolean esValor = false;
        int contador = 0;
        //String valor = "";
        Persona personaActual = null;
        String contenido = "";
        ArrayList<Persona> personas = new ArrayList<>();

        for (String linea : yaml.split("\n")) {
            if (linea.startsWith("-")) {
                // Nueva persona, agregar la persona actual a la lista y crear una nueva
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

        // Agregar la última persona a la lista
        if (personaActual != null) {
            personas.add(personaActual);
        }

        if (extension.equalsIgnoreCase("xml")){
            for (Persona tmp : personas){
                contenido += tmp.xml()+"\n";
            }
            return "<personas>\n"+contenido+"</personas>";
        } else if (extension.equalsIgnoreCase("json")) {
            if (personas.size()==1){
                return personas.get(0).json();
            }else {
                for (Persona tmp:personas){
                    contenido += tmp.json();
                    contenido +=",";
                }
                if (!contenido.isEmpty()) {
                    contenido = contenido.substring(0, contenido.length() - 1);
                }
                return "["+contenido+"]";
            }
        }
        return null;
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
