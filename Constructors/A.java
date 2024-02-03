public class A {
    private final double x;

    static{
        System.out.println("Tu statyczny z A"); //wykonuje się przed obiektami
    }

    {
        System.out.println("Tu niestatyczny z A"); // wykonuje się dopiero kiedy tworzymy obiekty, metoda na wepchanie kodu do konstruktorów które zostaną wykonane
    }

    public static void metodaStatyczna(){
        System.out.println("Tu metoda statyczna z A");
    }



    public A(int i) { //Jak dodam parametry to już się nie skomplikuje z B
        x = 12.345;
        System.out.println("Jestem konstruktorem z A " + i);
    }

    public A(double i) { //
        this((int) i);
    }
}
