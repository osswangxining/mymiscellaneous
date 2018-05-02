package polygon.sample;

import com.snatik.polygon.Point;
import com.snatik.polygon.Polygon;

public class polygontest {

	public static void main(String[] args) {
		Polygon polygon = Polygon.Builder()
		        .addVertex(new Point(1, 2)) // polygon
		        .addVertex(new Point(1, 6))
		        .addVertex(new Point(8, 7))
		        .addVertex(new Point(8, 1))
		        .close() 
		        .addVertex(new Point(2, 3)) // hole one
		        .addVertex(new Point(5, 5))
		        .addVertex(new Point(6, 2))
		        .close() 
		        .addVertex(new Point(6, 6)) // hole two
		        .addVertex(new Point(7, 6))
		        .addVertex(new Point(7, 5))
		        .build();
		
		Point point = new Point(4.5f, 7);
		boolean contains = polygon.contains(point);
		System.out.println(contains);
		
		
		point = new Point(6f, 5);
		 contains = polygon.contains(point);
		System.out.println(contains);
	}

}
