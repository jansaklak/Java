import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

public class FieldInspector implements FieldInspectorInterface{
    @Override
    public Collection<FieldInfo> inspect(String className) {
        Collection<FieldInfo> kolekcja = new ArrayList<>();
        try {
            Class klasa = Class.forName(className);
            Field[] pola = klasa.getDeclaredFields();
            for (Field pole : pola) {
                Field field = pole;
                field.setAccessible(true);
                Type typ;
                if (field.getType() == int.class) {
                    typ = Type.INT;
                } else if (field.getType() == double.class) {
                    typ = Type.DOUBLE;
                } else if (field.getType() == float.class) {
                    typ = Type.FLOAT;
                } else if (field.getType() == long.class) {
                    typ = Type.LONG;
                } else if (field.getType() == Long.class) {
                    typ = Type.LONG;
                } else if (field.getType() == Integer.class) {
                    typ = Type.INT;
                } else if (field.getType() == Double.class) {
                    typ = Type.DOUBLE;
                } else if (field.getType() == Float.class) {
                    typ = Type.FLOAT;
                } else if (field.getType() == Long.class) {
                    typ = Type.LONG;
                } else {typ = Type.OTHER;}
                if (field.isAnnotationPresent(FieldVersion.class)) {
                    int wersja = field.getAnnotation(FieldVersion.class).version();
                    FieldInfo w = new FieldInfo(typ, field.getName(), wersja);
                    kolekcja.add(w);
                } else {
                    FieldInfo w = new FieldInfo(typ, field.getName());
                    kolekcja.add(w);
                }
            }
        }
        catch ( Exception e ) { System.out.println(e);}
        return kolekcja;
    }
}
