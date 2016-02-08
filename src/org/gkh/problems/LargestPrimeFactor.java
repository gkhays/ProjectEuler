package org.gkh.problems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * One tries various values of a, hoping that a^2-N = b^2, a square.
 * 
 * <pre>
 * <code>
 * FermatFactor(N): // N should be odd 
 * a ← ceil(sqrt(N)) 
 * b2 ← a*a - N 
 * while b2 isn't a square: 
 *   a ← a + 1 // equivalently: b2 ← b2 + 2*a + 1 
 *   b2 ← a*a - N // a ← a + 1 
 * endwhile 
 * return a - sqrt(b2) // or a + sqrt(b2)
 * </code>
 * </pre>
 * 
 * For example, to factor N = 5959, the first try for a is the square root of
 * 5959 rounded up to the next integer, which is 78. Then, b^2 = 78^2-5959 =
 * 125. Since 125 is not a square, a second try is made by increasing the value
 * of a by 1. The second attempt also fails, because 282 is again not a square.
 * 
 * Further research:
 * 
 * How can I find the Square Root of a Java BigInteger?
 * 
 * http://stackoverflow.com/questions/4407839/how-can-i-find-the-square-root-of-a-java-biginteger
 * 
 * @author ghays
 */
public class LargestPrimeFactor {

	public static final BigInteger N = BigInteger.valueOf(600851475143L);
	
	static List<Long> getNaivePrimeList(long n) {
		List<Long> primeList = new ArrayList<Long>();
		double limit = Math.ceil(Math.sqrt(n));
		for (long l = 3; l <= limit; l += 2) {

		}
		return primeList;
	}
	
	static void getPrimesByFermatsFactorization(long n2) {
		double maxLoop = Math.ceil(Math.sqrt(n2));

		for (long l = 3; l <= maxLoop; l += 2) {

		}

		// Fermat's factorization
		double a = Math.ceil(Math.sqrt(n2));
		System.out.printf("Is %f a perfect square? %b\n", a,
				isPerfectSquare(Math.round(a)));

		boolean isPerfect = false;

		double bSquared = Math.pow(a, 2) - a;
		System.out.printf("b^2 = %f\n", bSquared);
		double x = a % 1;
		System.out.printf("x = %f\n", x);
		x = a - Math.floor(a);
		// If x > 0 then false
		System.out.printf("x = %f\n", x);
		// while ()
	}
	
	static boolean isDivisibleBy2(long n) {
		return (n % 2 == 0);
	}
	
	/**
	 * <pre>
	 * <code>
	 * bool IsPerfectSquare(long input) {
	 * 		double root = Math.Sqrt(input);
	 * 
	 * 		long rootBits = BitConverter.DoubleToInt64Bits(root);
	 * 		long lowerBound = (long) BitConverter.Int64BitsToDouble(rootBits - 1);
	 * 		long upperBound = (long) BitConverter.Int64BitsToDouble(rootBits + 1);
	 * 
	 * 		for (long candidate = lowerBound; candidate <= upperBound; candidate++) {
	 * 			if (candidate * candidate == input) {
	 * 				return true;
	 * 			}
	 * 		}
	 * 		return false;
	 * 	}
	 * </code>
	 * </pre>
	 * 
	 * @see <a href=
	 *      "http://stackoverflow.com/questions/343852/whats-a-good-algorithm-to-determine-if-an-input-is-a-perfect-square"
	 *      >What's a good algorithm to determine if an input is a perfect
	 *      square? [duplicate]</a>
	 * @see <a
	 *      href="http://stackoverflow.com/questions/14657905/perfect-square-if-else">Perfect
	 *      square if,else [closed]</a>
	 * 
	 * @param n
	 * @return
	 */
	static boolean isPerfectSquare(long n)
	{
	    long squareRoot = (long) Math.sqrt(n);
	    return ((Math.pow(squareRoot, 2)) == n);
	}
	
	/**
	 * <pre>
	 * Input: an integer n > 1
	 *  
	 * Let A be an array of Boolean values, indexed by integers 2 to n,
	 * initially all set to true.
	 *  
	 *  for i = 2, 3, 4, ..., not exceeding √n:
	 *   if A[i] is true:
	 *     for j = i2, i2+i, i2+2i, i2+3i, ..., not exceeding n :
	 *       A[j] := false
	 *  
	 * Output: all i such that A[i] is true.
	 * 
	 * @see <a href="https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes">Sieve of Eratosthenes</a>
	 * </pre>
	 */
	static List<Boolean> primeSieveCache(int n) {
		
		// Instead of looping through the list, use the collections API to
		// pre-populate it. Get rid of a loop.
		List<Boolean> isPrimeList = new ArrayList<Boolean>(n + 1);
		isPrimeList.add(0, false);
		isPrimeList.add(1, false);
		for (int i = 2; i <= n; i++) {
			isPrimeList.add(i, true);
		}
		// To initialize the prime list, we must convert a long to an int to
		// match the arguments for Collections.nCopies. Remember the 2 extra
		// elements.
		int limit = 0;
		try {
			limit = Math.toIntExact(Math.round(Math.ceil(Math.sqrt(n))));
			System.out.printf("The square root of N = %d\n", limit);
		} catch (ArithmeticException e) {
			e.printStackTrace();
		}
//		List<Boolean> isPrimeList = new ArrayList<Boolean>(Collections.nCopies(
//				limit + 2+5, true));
//		isPrimeList.add(0, false);
//		isPrimeList.add(1, false);
		
		// Optimization: limit to square root of N; otherwise use p^2 < N.
		for (int p = 2; p * p <= n; /* Math.sqrt(n); */p++) {
			if (isPrimeList.get(p).equals(true)) {
				for (int j = p; p * j <= n; j++) {
					isPrimeList.set(p * j, false);
				}
			}
		}
		
		return isPrimeList;
	}
	
	public static void main(String[] args) {
		testPrimeSieve();
		
		long n = 600851475143L;

		System.out.println("Divisible by 2: " + isDivisibleBy2(n));

		// Sieve output:
//		List<Boolean> isPrimeList = primeSieveCache(n);
//		System.out.printf("The size of the prime list is %d\n", isPrimeList.size());
//		System.out.println(isPrimeList.size() - 1);
	}

	/**
	 * Max value of Java int: 2,147,483,647.
	 */
	static void testPrimeSieve() {
		int n = 1000000;
		List<Boolean> isPrimeList = primeSieveCache(n);
		int size = isPrimeList.size();
		System.out.printf("Size of prime list: %d\n", size,
				isPrimeList.get(size - 1));
		System.out.printf("isPrimeList[%d] = %b\n", size - 1,
				isPrimeList.get(size - 1));
		
		int numberOfPrimes = 0;
		for (int p = 2; p < isPrimeList.size(); p++) {
			if (isPrimeList.get(p)) {
//				System.out.printf("Prime: p^2 = %d, p = %d: %b\n", (p * p), p,
//						isPrimeList.get(p));
				if (isPrimeList.get(p))
					numberOfPrimes++;
			}
		}
		System.out.printf("The number of primes <= %d is %d\n", n, numberOfPrimes);
	}
}
