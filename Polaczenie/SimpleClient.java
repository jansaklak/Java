import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import static java.lang.Character.getNumericValue;


public class SimpleClient {


    public static void PrzedstawSie(OutputStream out) throws RuntimeException, IOException {
        PrintWriter writer = new PrintWriter(out, true);
        writer.println("program");
        //System.out.println("Odpowiadam program");
    }

    public static void Odpowiedz(OutputStream out,int wynik) throws RuntimeException, IOException {
        PrintWriter writer = new PrintWriter(out, true);
        writer.println(wynik);
        //System.out.println("Zgaduje ze " + wynik);
    }

    public static class Licznik{
        int Licznik;
        public Licznik() {
            Licznik = 0;
        }
        public void Zwieksz(){
            Licznik++;
            //System.out.println("Jest juz: " + Licznik);
        }
        public int Wynik(){
            return Licznik;
        }
    }

    public static boolean CzyRowne(int liczona, int obecna){
        //System.out.println("Liczona" + liczona + "obecna" + obecna);
        if(liczona == obecna){

            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args) throws IOException {

        final int PORT = 8080;
        Socket so = null;
        OutputStream wyjscie = null;
        BufferedReader br = null;
        String host = "172.30.24.101";
        PrintWriter przedstawsie = null;
        int liczona;
        try {
            so = new Socket( host, PORT ); // tworzymy gniazdo sieciowe
        }
        catch ( UnknownHostException exc ) {
            System.out.println( "Nie znam takiego hosta " + host );
        }
        catch ( ConnectException exc ) {
            System.out.println( "Blad polaczenia z serwerem " + host );
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }

        br = new BufferedReader( new InputStreamReader( so.getInputStream() ) );
        wyjscie = so.getOutputStream();
        String line;
        int i = 0;
        liczona = 0;
        Licznik nowylicznik = new Licznik();
        while ( ( line = br.readLine() ) != null ) {
            System.out.println("Nr" + i + "->" + line);
            i++;
            if(i==2) PrzedstawSie(wyjscie);
            if(i==4){
                //liczona = getNumericValue(line.charAt(line.length()-1));
                liczona = Integer.parseInt(line.replaceAll("[\\D]", ""));
            }
            if(i>4){
                if('E' == line.charAt(0)){
                    //System.out.println("bede odpowiadal");
                    Odpowiedz(wyjscie, nowylicznik.Wynik());
                    break;
                }
                if(CzyRowne(liczona,Integer.parseInt(line.replaceAll("[\\D]", "")))){
                    nowylicznik.Zwieksz();
                }
            }
        }

        while ( ( line = br.readLine() ) != null ) {
            System.out.println("-> " + line);
        }


        try {
            so.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
