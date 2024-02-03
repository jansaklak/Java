public class Pojemnik {
    private int pojemnikV; // chcemy obiektowi innemu niż Pojemnik (prywatego) innej klasie, getter setter daje wszystkim a my chcemy tylko konkretnej klasie
    //W C++ jest friend - klasy zaprzyjaźnione w Java jest klasa wewnetrzna

    public class KlasaWewnetrzna{
        private int pojemnikKW;
        public void metodaKlasyWewnetrznej(){ // -- tu nie potrzeba referencji
            System.out.println(pojemnikV);
        }
    }

    public static class KlasaZagniezdzona {
        private int pojemnikKW;
        public void metodaKW(Pojemnik poj){
            System.out.println(poj.pojemnikV);
        }
    }

    public static Pojemnik zrobPojemnik(){ // tworzenie obiektów poprzez metody statyczne
        class LepszyPojemnik extends Pojemnik{
            @Override
            public String toString(){
                return "Oto ja lepszy pojemnik";
            }
        }
        return new LepszyPojemnik();
    }

    public static Pojemnik zrobPojemnikInaczej(){ // tworzenie obiektów poprzez metody statyczne
        return new Pojemnik(){ // klasa anonimowa nie ma nazwy -> nie może mieć konsktuktora
            @Override
            public String toString(){
                return "Oto pojemnik utworzony jako klasa anonimowa";
            }
        };
    }

    public String toString(){
        return "Oto ja pojemnik";
    }

    public void metodaKlasyPojemnik(KlasaWewnetrzna obiektKW){ // -- tu potrzeba referencje
        System.out.println(obiektKW.pojemnikKW); //problem, innny niż prawa dostępu (pojemnikKW -> obiektKW.pojemnikKW)
    }

    //potrzeba referencji zewnetrznej do wewnetrznej a nie trzeba wewnetrznej do zewnetrznej

    public static void main(String args[]){
        System.out.println(zrobPojemnik());
        System.out.println(zrobPojemnikInaczej());
        KlasaZagniezdzona obiektKZ = new KlasaZagniezdzona(); // ***
        //KlasaWewnetrzna ObiektKW = new KlasaWewnetrzna(); - nie działa - nie da sie obiektu klasy wewnetrznej bo ta klasa zachowuje sie jak pola niestatyczne -> nie ma obiektu pojemnika -> nie ma klas wewnetrzynch
        Pojemnik pojemnik = new Pojemnik();
        KlasaWewnetrzna ObiektKW = pojemnik.new KlasaWewnetrzna(); //teraz dzialą
        //dlatego nie trzeba referencji - konkretny obiekt klasy wewnetrznej uzywa konkretnej klasy pojemnika

        // *** zagdniezdznony mozna bez new Pojemnik
    }

// W out/production widać jak powstaja konskturktory

}
