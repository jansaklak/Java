public class TestStringBuffera extends BudowaCiaguZnakow{
    public void doIt(){
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<liczbaPowtorek;i++){
            sb.append(wzorzec);
        }
        wynik = sb.toString();
    }

    public String toString(){
        return "Test String Buildera";
    }
}
