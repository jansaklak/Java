import org.objectweb.asm.*;
public class MainDump implements Opcodes {
    public static byte[] dump() throws Exception {
    ClassWriter cw = new ClassWriter(0);
    MethodVisitor mv;
    cw.visit(V1_7, ACC_PUBLIC + ACC_SUPER, "Main", null,
    "java/lang/Object", null);
    mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
    mv.visitCode();
    mv.visitVarInsn(ALOAD, 0);
    mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", 
    "()V");
    mv.visitInsn(RETURN);
    mv.visitMaxs(1, 1);
    mv.visitEnd();
    mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main",
    "([Ljava/lang/String;)V", null, null);
    mv.visitCode();
    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
    "Ljava/io/PrintStream;");
    mv.visitLdcInsn("Hello world!");
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", 
    "println", "(Ljava/lang/String;)V");
    mv.visitInsn(RETURN);
    mv.visitMaxs(2, 1);
    mv.visitEnd();
    cw.visitEnd();
    return cw.toByteArray();
    }
}
