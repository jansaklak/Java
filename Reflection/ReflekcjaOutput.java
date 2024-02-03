import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class k = Class.forName("java.io.ObjectOutputStream");
        ArrayList< Method > metody = new ArrayList<>();
        metody.addAll(List.of(k.getMethods()));
        for(Method m : metody){
            System.out.println(m.getName());
        }
    }
}
