package finalize.sample;

class MyObject {
	FinalizeTest main; // 记录Test对象，在finalize中时用于恢复可达性

	public MyObject(FinalizeTest t) {
		main = t; // 保存Test 对象
	}

	protected void finalize() {
		main.ref = this; // 恢复本对象，让本对象可达
		System.out.println("This is finalize"); // 用于测试finalize只运行一次
	}
}

public class FinalizeTest {
	MyObject ref;

	public static void main(String[] args) {
		FinalizeTest test = new FinalizeTest();
		test.ref = new MyObject(test);
		test.ref = null;
		System.gc();
		System.out.println("My Object还活着??" + test.ref);
	}

}
