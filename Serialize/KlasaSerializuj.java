import java.io.*;
import java.util.ArrayList;

public class KlasaSerializuj {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<String> as = new ArrayList<>();
        as.add("abc");
        as.add("def");
        Serializowana abc = new Serializowana(3,as);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("zserialozowana"));
        oos.writeObject(abc);
        oos.close();
        Serializowana xyz;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("zserialozowana"));
        xyz = (Serializowana)ois.readObject();
        System.out.println(xyz.abc + "a" + xyz.lista);
        ois.close();
    }
}

class Serializowana implements Serializable {
    int abc;
    ArrayList<String> lista;

    public Serializowana(int abc, ArrayList<String> lista) {
        this.abc = abc;
        this.lista = lista;
    }
}
