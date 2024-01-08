package org.example;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.json.JSONObject;
import org.json.XML;
import org.yaml.snakeyaml.Yaml;

public class Conversor {
    public String xmlToJson(String xml){
        JSONObject json = XML.toJSONObject(xml);
        return json.toString();
    }
    public String jsonToXml(String json){
        JSONObject jsonObj = new JSONObject(json);
        String xml = XML.toString(jsonObj);
        return xml;
    }
    public String jsonToYaml(String json){
        try {
            ObjectMapper jsonMap = new ObjectMapper();
            JsonNode jn = jsonMap.readTree(json);
            YAMLMapper yamlMap = new YAMLMapper();

            return yamlMap.writeValueAsString(jn);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public String yamlToJson(String yaml){
        try {
            ObjectMapper yamlMap = new ObjectMapper(new YAMLFactory());
            JsonNode jn = yamlMap.readTree(yaml);
            return jn.toPrettyString();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public String extensionArchivo(String nombreArchivo){
        int ultimaPosicion = nombreArchivo.lastIndexOf(".");
        return nombreArchivo.substring(ultimaPosicion+1);
    }
}
