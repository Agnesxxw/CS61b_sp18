package Lab_01;
// 0 is the 1st element
public class Fib2 {
	public static int fib2(int n) { // Recursion
		if(n == 1) {
			return 0;
		}
		if (n <= 3){
            return 1;
        }
        return fib2(n-1) + fib2(n-2);
	}
	public static void main(String[] args) {
		int n = 10;
		System.out.println(fib2(n));
		
	}
}

