import javax.security.auth.callback.TextInputCallback;

public class PomiarCzasu {
    public static final int POWTORKI = 10000000;
    private static final String WZORZEC = "alibaba";

    public static void main(String[] args) {
        ustawITestuj(new Konkatenacja());
        ustawITestuj(new TestStringBuffera());
        ustawITestuj(new TestStringRepeat());
        ustawITestuj(new TestStringBuildera());
    }

    private static void ustawWarunkiTestu(BudowaCiaguZnakow budowa){
        budowa.setLiczbaPowtorek(POWTORKI);
        budowa.setWzorzec(WZORZEC);
    }

    private static void ustawITestuj(BudowaCiaguZnakow budowa){
        ustawWarunkiTestu(budowa);
        mierzIPrezentujWynik(budowa);
    }
    public static void mierzIPrezentujWynik(Runnable programDoTestu){
        double tStart = TimeHelper.getTime();
        double tEnd = TimeHelper.getTime();
        double delta = tEnd - tStart;
        System.out.println(programDoTestu);
        System.out.println("Pomiar czasu: " + delta);
    }
}
