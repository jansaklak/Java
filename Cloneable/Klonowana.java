import java.util.ArrayList;

public class Klonowana {
    public static void main(String[] args) throws CloneNotSupportedException {
        ArrayList<String> jakaslista = new ArrayList<>();
        jakaslista.add("qwe");
        jakaslista.add("asd");
        Klasa k1 = new Klasa(3,jakaslista);
        Klasa k2 = (Klasa) k1.clone();
        System.out.println("int" + k2.a + "list" + k2.l);
    }
}
class Klasa implements Cloneable{
    int a;
    ArrayList<String> l;
    public Klasa(int a, ArrayList<String> l) {
        this.a = a;
        this.l = l;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

