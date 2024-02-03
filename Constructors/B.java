public class B extends A{


    public B(){
        super(7.6);//referencja do czesci ktora pochodzi z dziedziczenia
        //this() - naredzie zeby z jednego konstruktora tej klasy wywolac inny konstrukotor tej klasy
        System.out.println("Tutaj konstruktor B"); //this/super musi byc pierwszą instrukcją,potem np print
    }
    public static void main(String[] args){
        System.out.println("Tu main B");
        A.metodaStatyczna();
        B b = new B(); //tworząc obiekt B, konstruktor A jest? - żeby sprawdzić daje println do konstruktora A
    }


    }

