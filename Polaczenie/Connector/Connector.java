import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connector implements NetConnection {
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
    @Override
    public void connect(String host, int port) {
        final int PORT = port;
        Socket so = null;
        OutputStream wyjscie = null;
        BufferedReader br = null;
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
        try {
            br = new BufferedReader( new InputStreamReader( so.getInputStream() ) );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            wyjscie = so.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String line;
        int i = 0;
        liczona = 0;
        Licznik nowylicznik = new Licznik();
        while (true) {
            try {
                if (!(( line = br.readLine() ) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Nr" + i + "->" + line);
            i++;
            if(i==2) {
                try {
                    PrzedstawSie(wyjscie);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(i==4){
                //liczona = getNumericValue(line.charAt(line.length()-1));
                liczona = Integer.parseInt(line.replaceAll("[\\D]", ""));
            }
            if(i>4){
                if('E' == line.charAt(0)){
                    //System.out.println("bede odpowiadal");
                    try {
                        Odpowiedz(wyjscie, nowylicznik.Wynik());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                if(CzyRowne(liczona,Integer.parseInt(line.replaceAll("[\\D]", "")))){
                    nowylicznik.Zwieksz();
                }
            }
        }
        while (true) {
            try {
                if (!(( line = br.readLine() ) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("-> " + line);
        }
        try {
            so.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
}
