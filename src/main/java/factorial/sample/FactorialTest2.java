package factorial.sample;

public class FactorialTest2 {

	public static long factorial(int n) {

		long result = 1;

		for (int i = 1; i <= n; i++) {

			result = result * i;

		}
		return result;
	}

	public static void main(String[] args) {
		int n = 21;
		long result = factorial(n);
		System.out.println(Long.MAX_VALUE);
		System.out.println(Long.MIN_VALUE);
		System.out.println("n=" + n + ",result=" + result);
		System.out.println(result - Long.MAX_VALUE);
		
	}

}
