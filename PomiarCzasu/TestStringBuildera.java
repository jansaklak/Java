public class TestStringBuildera extends BudowaCiaguZnakow{
    public void doIt(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<liczbaPowtorek;i++){
            sb.append(wzorzec);
        }
        wynik = sb.toString();
    }

    public String toString(){
        return "Test String Buildera";
    }
}
