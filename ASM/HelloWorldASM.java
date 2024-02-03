
public class HelloWorldASM extends ClassLoader{
    public static void main(final String args[]) throws Exception {
    HelloWorldASM loader = new HelloWorldASM();
    byte[] code = MainDump.dump();
    Class cl = loader.defineClass("Main", code, 0, code.length);
    cl.getMethods()[0].invoke(null, new Object[] { null });
    }
}
   
