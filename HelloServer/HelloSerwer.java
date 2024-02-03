import java.io.*;
import java.net.*;
public class ServerExample {
    private static ServerSocket seso;
    static int port = 8080;
    public static void main(String[] args) throws IOException{
        seso = new ServerSocket(port);
        while(true){
            Socket s = seso.accept();
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            int b;
            while((b=is.read())!=-1){
                os.write("Hello");
                os.println("Hello");
            }
            s.close();
            }
        }
    };