import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Point2D {
	private int x;
	private int y;

	public String toString() {
		return "Point2D [x=" + x + ", y=" + y + "]";
	}

	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	

	public static void main(String[] args) {
		Point2D p1 = new Point2D();
		Point2D p2 = new Point2D();
		Point2D p3 = new Point2D();
		Point2D p4 = new Point2D();
		Point2D p5 = new Point2D();
		Point2D p6 = new Point2D();
		
		p1.x = 1;
		p1.y = 1;
		p2.x = 1;
		p2.y = 2;
		p3.x = 2;
		p3.y = 1;
		p4.x = 2;
		p4.y = 2;
		p5.x = 2;
		p5.y = 1;
		p6.x = 2;
		p6.y = 2;
		
		List<Point2D> listaPunktow = new ArrayList<>();
		listaPunktow.add(p1);
		listaPunktow.add(p2);
		listaPunktow.add(p3);
		listaPunktow.add(p4);
		listaPunktow.add(p5);
		listaPunktow.add(p6);
		
		Set<Point2D> listaPunktow2 = new HashSet<>(listaPunktow2);
		show(listaPunktow);
		
	}
	
	public static void show( Collection<Point2D> kolekcja) {
		System.out.println(kolekcja);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		System.out.println("Tu " + this + "HashCode()");
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Point2D))
			return false;
		Point2D other = (Point2D) obj;
		return x == other.x && y == other.y;
	}
};
	
	
	


