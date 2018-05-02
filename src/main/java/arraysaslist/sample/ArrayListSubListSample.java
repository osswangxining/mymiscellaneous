package arraysaslist.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListSubListSample {

	public static void main(String[] args) {
		// List<String> list = new ArrayList<>();
		// list.add("a");
		// list.add("b");
		// list.add("c");
		//
		// List<String> subList = list.subList(0, 1);
		// list.remove(0);
		// System.out.println(subList);
		// subList.forEach(s -> System.out.println(s));
		// subList.remove(0);
		// System.out.println(subList);
		// System.out.println(list);

		String[] str = new String[] { "you", "wu" };
		List list = Arrays.asList(str);//fixed-size list
		//will throw Exception in thread "main" java.lang.UnsupportedOperationException
		list.add("yangguanbao");
	}

}
