//WADA ŻĘ WCZYTUJE CAŁY PLIK NA RAZ DO PAMIĘĆI
import java.io.IOException;
import java.net.URL;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
public class DOMExample {
    public static void main(String[] args) throws ParserConfigurationException,SAXException,IOException {
            URL url = new URL(
            "http://www.uj.edu.pl/uniwersytet/aktualnosci/-/journal/rss/10172/36018?doAsGroupId=10172&refererPlid=10181"); // jakis RSS //Strumien(najlepiej url)
            // domyslna fabryka “obiektow tworzących dokumenty”
            DocumentBuilderFactory dbFactory =
            DocumentBuilderFactory.newInstance();
            // obiekt “tworzący dokumenty”
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            // tworzenie modelu DOM na podstawie zrodla XML (parsowanie XML'a)
            Document doc = dBuilder.parse(url.openStream());//
            // obiekt doc umozliwia dostep do wszystkich danych zawartych
            // w dokumencie XML
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName()); //W naszym pliku jest to RSS
            NodeList nList = doc.getElementsByTagName("item"); 
            for (int i = 0; i < nList.getLength(); i++) {
                // lista “dzieci” i-tego itema
                Node n = nList.item(i);
                // czy “dziecko” jest elementem?
                if (n.getNodeType() == Node.ELEMENT_NODE) {//Jesli node to na ekran
                    Element e = (Element) n;
                    System.out.println("Tytul : "        + getTagValue("title",      e));
                    System.out.println("Link : "         + getTagValue("link",       e));
                    System.out.println("dodane przez : " + getTagValue("dc:creator", e));
                }
            }
    }

 // zwraca wartosc zapisana w tagu s wewnatrz elementu e
 private static String getTagValue(String s, Element e) {

    // lista “dzieci” e o nazwie s
    NodeList nl = e.getElementsByTagName(s).item(0).getChildNodes();
    // pierwszy wpis z tej listy i bierzemy elementy pod spodem
    // to co on zawiera – jego “dzieci”
    // pierwsze z tych dzieci(elementów pod)
    Node n = (Node) nl.item(0);
    // zawartosc, ktora tam jest
    return n.getNodeValue();
    }
}

// pobieramy element title i zmieniamy go
if ("title".equals(node.getNodeName())) {
    node.setTextContent("Nowy tytul");
    }
// kasujemy link
if ("link".equals(node.getNodeName())) {
    itemElement.removeChild(node);
}
// tworzenie nowego dokumentu
Document doc = docBuilder.newDocument();
Element e = doc.createElement("root");
doc.appendChild(e);

//Zapis z powrotrem do XML za pomocą transformatorów
// domyslna fabryka transformatorow
TransformerFactory transformerFactory = TransformerFactory.newInstance();
// nowy tranformator
Transformer transformer = transformerFactory.newTransformer();
// wejscie transformatora (skad transformator bierze dane)
DOMSource source = new DOMSource(doc);
// wyjscie transformatora (gdzie “zmienione” dane zostana zapisane)
StreamResult result = new StreamResult(new File("file.xml"));
// uruchomienie transformatora – zapis DOM do pliku w formacie XML
transformer.transform(source, result);