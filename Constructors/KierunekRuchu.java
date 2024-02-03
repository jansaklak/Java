import java.util.Random;

public enum KierunekRuchu implements ZmianaPolozenia{ // enum bo nie mozna ich zmienic

    PRAWO(1,0){
        @Override
        public String toString() {
            return ">";
        }
    },
    LEWO(-1,0){
        @Override
        public String toString() {
            return "<";
        }
    },
    GORA(0,1){
        @Override
        public String toString() {
            return "^";
        }
    },
    DOL(0,-1){
        @Override
        public String toString() {
            return "|";
        }
    },;
    private final int dx,dy; //konstruktor moze zapisac wartosc do stalych
    private static final Random rnd;
    static {
        rnd = new Random();
    }
    private KierunekRuchu(int dx,int dy){
        this.dx = dx;
        this.dy = dy;
    }
    public Polozenie zmiana(Polozenie aktualne){
        return new Polozenie(
                aktualne.x() + dx,
                aktualne.y() + dy);
    }
    public static KierunekRuchu losujKierunek(){
        int i = rnd.nextInt(KierunekRuchu.values().length);
        return KierunekRuchu.values()[i];

    }

    public static void main(String[] args) {
        Polozenie aktualne = new Polozenie(0,0);
        Polozenie nowe;
        KierunekRuchu kierunek;
        for(int i = 0;i<10;i++){
            kierunek = losujKierunek();
            nowe = kierunek.zmiana(aktualne);
            System.out.println("Stare: " + aktualne + "->" + nowe +" DIR: "+ kierunek);
            aktualne = nowe;
        }
    }

}

//mAP POINT INT - INT W ILU ODCINKACH MOGE DTRZEC DO CZEGO
