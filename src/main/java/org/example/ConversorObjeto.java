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
            return "<Personas>"+contenido+"</Personas";
        }else if (extension.equalsIgnoreCase("yaml")){
            for (Persona tmp : personas){
                 contenido += tmp.yaml()+"\n";
            }
            return contenido;
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
                }
                return "{["+contenido+"]}";
            }
        }else if (extension.equalsIgnoreCase("yaml")){
            for (Persona tmp : personas){
                contenido += tmp.yaml()+"\n";
            }
            return contenido;
        }
        return null;
    }
    public String yamlObjeto(String yaml, String extension){
        boolean esValor = false;
        int contador = 0;
        String valor = "";
        Persona p = new Persona();
        ArrayList<Persona> personas = new ArrayList<>();

        for (char c : yaml.toCharArray()){
            if (esValor){
                if (c == '\n'){
                    esValor = false;
                    switch (contador){
                        case 1:
                            p.setNombre(valor.strip());
                            contador++;
                            break;
                        case 2:
                            p.setApellidos(valor.strip());
                            contador++;
                            break;
                        case 3:
                            p.setEdad(Integer.parseInt(valor.strip()));
                            contador = 0;

                            valor = "";
                            personas.add(p);
                            p = new Persona();
                            break;
                    }
                }else {
                   valor += c;
                }
            }
            if (c == ':'){
                esValor = true;
                contador++;
            }
        }
        
        
        if (extension.equalsIgnoreCase("xml")){
            
        } else if (extension.equalsIgnoreCase("json")) {
            
        }
    }

    public String extensionArchivo(String nombreArchivo){
        int ultimaPosicion = nombreArchivo.lastIndexOf(".");
        return nombreArchivo.substring(ultimaPosicion+1);
    }
}
