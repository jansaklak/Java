public class Konkatenacja extends BudowaCiaguZnakow{
    protected void doIt(){
        for(int i=0; i<liczbaPowtorek;i++){
            wynik += wzorzec;
        }
    }

    public String toString(){
        return "Zwykła konkatenacja ciągu znaków";
    }
}
