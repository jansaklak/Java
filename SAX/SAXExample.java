import java.io.IOException;
import java.net.URL;
import javax.xml.parsers.*;
import org.xml.sax.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXExample {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException{
        URL url = new URL("http://www.uj.edu.pl/...");
        SAXParserFactory f = SAXParserFactory.newInstance();
        SAXParser saxParser = f.newSAXParser();
        DefaultHandler handler = new ExampleSAXHandler(); //WAŻNE, przekazujemy strumień którty ma parsować oraz Handlera, 
        saxParser.parse(url.openStream(), handler); // TA klasa ma reagować na zdarzenia parsera
        //PARSER SAXOWY GENERUJE KOMUNIKATY -> TRZEBA MU DOSTARCZYĆ ODBIORCE TYCH KOMUNIKATÓW, DOKĄD WYSYŁAĆ
    }
}

class ExampleSAXHandler extends DefaultHandler {//implementujemy reakcje tylko na te zdarzenia które sa dla nas istotne
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("Element :" + qName);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("Koniec elementu :" + qName);
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        System.out.println("zawartosc : "+ new String(ch, start, length));
    }
}

void characters(char[] ch, int start, int length){}            // wywoływane przyodczycie znaku wewnątrz elementu,
void endDocument()                                           // wywoływane gdy koniec dokumentu
void startElement(String uri, String localName, String qName,Attributes attributes) // początek elementu
void endElement(String uri, String localName, String qName)  // koniec elementu,
void error(SAXParseException e)                              // błąd (możliwy do naprawienia),
void fatalError(SAXParseException e)                         // błąd,
void ignorableWhitespace(char[] ch, int start, int length)   // ignorowany pusty znak,
void startDocument()                                         // początek dokumentu,
void warning(SAXParseException e)                            // ostrzeżenie parsera.