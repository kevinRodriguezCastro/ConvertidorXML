package org.example;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PersonaHandler extends DefaultHandler {
    private List<Persona> personas;
    private Persona personaActual;
    private StringBuilder contenido;

    public List<Persona> parse(String xml) throws Exception {

        InputStream xmlInput = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        personas = new ArrayList<>();
        saxParser.parse(xmlInput, this);
        return personas;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        contenido = new StringBuilder();
        if (qName.equalsIgnoreCase("persona")) {
            personaActual = new Persona();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        contenido.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("nombre")) {
            personaActual.setNombre(contenido.toString().trim());
        } else if (qName.equalsIgnoreCase("apellidos")) {
            personaActual.setApellidos(contenido.toString().trim());
        } else if (qName.equalsIgnoreCase("edad")) {
            personaActual.setEdad(Integer.parseInt(contenido.toString().trim()));
        } else if (qName.equalsIgnoreCase("persona")) {
            personas.add(personaActual);
        }
    }
}
