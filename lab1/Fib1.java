package Lab_01;

public class Fibonacci {
	public static int fib(int n) {
		int n1 = 0;
		int n2 = 1;
		int n3 = 0, i = 3;
		if(n == 1) {return 0;}
		if(n == 2) {return 1;}
		while(i <= n ) {
			n3 = n1 + n2;
			n1 = n2;
			n2 = n3;
			i += 1;
		}
		return n3;
	}
	public static void main(String[] args) {
		int n = 10;
		System.out.println(fib(n));	
	}
}
