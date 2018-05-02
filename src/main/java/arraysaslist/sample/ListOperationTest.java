package arraysaslist.sample;

import java.util.ArrayList;
import java.util.List;

public class ListOperationTest {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		//will throw Exception in thread "main" java.util.ConcurrentModificationException
		for (String item : list) {
			if ("2".equals(item)) {
				list.remove(item);
			}
		}
		System.out.println(list);
	}

}
