import java.io.*;
import java.util.zip.GZIPOutputStream;

public class Kompresja {
  public static void main(String[] args) {
    String poczatkowy = "example.txt";
    String koncowy = "example.txt.gz";
    byte[] buffer = new byte[1024];
    
    try (FileInputStream in = new FileInputStream(poczatkowy);
         FileOutputStream out = new FileOutputStream(koncowy);
         GZIPOutputStream outgzip = new GZIPOutputStream(out)) 
    {
        int len;
        while ((len = in.read(buffer)) > 0) {
            outgzip.write(buffer, 0, len);
        }
    outgzip.finish();
    in.close();
    out.close();
    } catch (IOException e) {
        System.out.println("An error occurred while compressing the file: " + e);
    }
  }
}