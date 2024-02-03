import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collection;
public interface FieldInspectorInterface {
	public enum Type {
		INT, LONG, FLOAT, DOUBLE, OTHER;
	}
	public class FieldInfo {
		public final Type type;
		public final String name;
		public final int version;
		public FieldInfo(Type type, String name, int version) {
			this.type = type;
			this.name = name;
			this.version = version;
		}
		public FieldInfo(Type type, String name) {
			this(type, name, -1);
		}
	}
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface FieldVersion {
		public int version();
	}
	public Collection<FieldInfo> inspect(String className);
}
