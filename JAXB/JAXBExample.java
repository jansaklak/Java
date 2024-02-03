import java.io.File;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*; 
//pozwala serializować elementy javy do XML
@XmlRootElement // TAK JAK NA PODSTAWIE KOMENTARZY GENEROWANA JSET DOKUMENTACJA TO ADNOTACJE WYKOROZYTUJE JAXB ZEBY WIEDZIEC JAK WYGLADAĆ MA DOKUMENT XML
class Person {

    String name;
    int age;

    public String getName() {
        return name;
    }

    @XmlElement //  DLA KAŻDEGO ATRYBUTU PARA METOD GETSET XMLELEMENT MÓWI ŻE TO IMIE BĘDZIE ODWZOROWANE NA ELEMENT W PLIKU XML
    public void setName(String name) { //SET ASD this.name w XML BEDZIE SIE NAZYWAC ASD
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }

    @XmlElement
    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
    return this.name + " (" + this.age + ")";
    }
}

public class JAXBExample{
    public static void main(String[] args) throws JAXBException {
        Person p = new Person();
        p.setName("Barnaba");
        p.setAge(33);
        File f = new File("person.xml");
        JAXBContext ctx = JAXBContext.newInstance(Person.class); // obiekt wykrzystujacy tą klase
        Marshaller marshaller = ctx.createMarshaller(); // w oparciu o tą klase tworzymy fabryke jaxbuilder, klasa Marshaler wykorzystujemy do tego, mamy obiekt za pomocą którego będziemy exportowac istancje tej klasy do pliku xml
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); //xml będzie ładnie z wcięciami
        marshaller.marshal(p, System.out);
        marshaller.marshal(p, f);
        p = null; //kasujemy obiekt
        Unmarshaller unmarshaller = ctx.createUnmarshaller(); // UNmarshaller bez argumentow bo dalej dla tej amej klasy 
        p = (Person)unmarshaller.unmarshal(f); //zwraca obiekt w javie
        System.out.println(p);
        }
}
