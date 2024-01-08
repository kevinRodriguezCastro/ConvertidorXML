package org.example;

import java.io.*;
import java.util.Scanner;


public class App {

    public static void main( String[] args ) throws IOException {
        Scanner myScan = new Scanner(System.in);
        String opcion;
        String ruta = "./src/archivo.yaml";
        String extension;
        File archivo = new File(ruta);
        if (!archivo.exists()){
            ruta = "./src/archivo.xml";
            archivo = new File(ruta);
            if (!archivo.exists()){
                ruta = "./src/archivo.json";
                archivo = new File(ruta);
            }
        }

        String linea;
        String contenido = "";
        Conversor c = new Conversor();


        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        while ((linea = br.readLine()) != null){
            contenido += linea+"\n";
            System.out.println(linea);
        }
        br.close();


        System.out.println("1 cambiar a xml");
        System.out.println("2 cambiar a json");
        System.out.println("3 cambiar a yaml");
        opcion = myScan.nextLine();
        FileWriter fw       = new FileWriter(archivo);
        BufferedWriter bw   = new BufferedWriter(fw);
        switch (opcion){
            case "1":
                extension = c.extensionArchivo(archivo.getName());
                if (extension.equalsIgnoreCase("xml"))break;
                if (extension.equalsIgnoreCase("yaml")) {
                    contenido = c.yamlToJson(contenido);
                }
                contenido = c.jsonToXml(contenido);
                bw.write(contenido);
                bw.close();
                fw.close();

                File xml = new File("./src/archivo.xml");
                archivo.renameTo(xml);

                /*
                xml.createNewFile();
                archivo.delete();
                archivo = xml;*/
                break;
            case "2":
                extension = c.extensionArchivo(archivo.getName());
                if (extension.equalsIgnoreCase("json"))break;
                if (extension.equalsIgnoreCase("yaml")) {
                    contenido = c.yamlToJson(contenido);
                }
                if (extension.equalsIgnoreCase("xml")){
                    contenido = c.xmlToJson(contenido);
                }
                bw.write(contenido);
                bw.close();
                fw.close();

                File json = new File("./src/archivo.json");
                archivo.renameTo(json);
                /*
                json.createNewFile();

                archivo.delete();
                archivo = json;*/
                break;
            case "3":
                extension = c.extensionArchivo(archivo.getName());

                if (extension.equalsIgnoreCase("yaml"))break;
                if (extension.equalsIgnoreCase("xml")) {
                    contenido = c.xmlToJson(contenido);
                }

                contenido = c.jsonToYaml(contenido);
                bw.write(contenido);
                bw.close();
                fw.close();

                File yaml = new File("./src/archivo.yaml");
                archivo.renameTo(yaml);
                break;
        }

    }
}
