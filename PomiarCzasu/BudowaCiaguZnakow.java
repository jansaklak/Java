abstract class BudowaCiaguZnakow implements Runnable {
    protected int liczbaPowtorek;
    protected String wzorzec;
    protected String wynik;

    public String getWynik(){
        return wynik;
    }
    public void setLiczbaPowtorek(int liczbaPowtorek){
        this.liczbaPowtorek = liczbaPowtorek;
    }
    public void setWzorzec(String wzorzec){
        this.wzorzec = wzorzec;
    }

    public void run(){
        wynik = "";
        doIt();
        System.out.println("Długość ciagu wynikowego: " + wynik.length());
    }

    abstract protected void doIt();


}
