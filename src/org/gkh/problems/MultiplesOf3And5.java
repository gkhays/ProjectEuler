package org.gkh.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we
 * get 3, 5, 6 and 9. The sum of these multiples is 23.
 * 
 * Find the sum of all the multiples of 3 or 5 below 1000.
 * 
 * I was looking at the solutions forum and there are some optimizations.
 * 
 * @see <a href="https://projecteuler.net/thread=1></a>
 * 
 * @author Garve Hays
 */
public class MultiplesOf3And5 {

	public static final int ITERATIONS = 1000;
	
	static boolean isMultipleOfN(int constant, int n) {
		return ((n % constant) == 0);
	}	
	
	/**
	 * Brute-force, no optimization.
	 * 
	 * @return the sum of the multiples
	 */
	static int sumOfMultiples(int count) {
		List<Integer> multiples = new ArrayList<Integer>();
		for (int n = 0; n < count; n++) {
			if (isMultipleOfN(3, n)) {
				multiples.add(n);
			} else if (isMultipleOfN(5, n)) {
				multiples.add(n);
			}
		}

		// Prior to Java 8... See sumOfMultiples8() below.
		int sum = 0;
		for (int i : multiples) {
			sum += i;
		}
		return sum;
	}
	
	/**
	 * <b>Note</b>: Subsequent to Java 8 you may use {@code IntSream}, e.g.
	 * 
	 * <pre>
	 * <code>
	 *  int[] a = 10, 20, 30, 40, 50 };
	 *  int sum = IntStream.of(a).sum();
	 * </code> </pre>
	 * 
	 * However Eclipse complains about the static {@link IntStream#of} syntax
	 * and seems to expect {@link Arrays#stream}. Unless you set the workspace
	 * or project compliance level to 1.8. Apparently Guava has
	 * {@code Ints.toArray} while Apache Commons has
	 * {@code ArrayUtils.toPrimitive}.
	 * 
	 * @see <a
	 *      href="http://stackoverflow.com/questions/4550662/how-do-you-find-the-sum-of-all-the-numbers-in-an-array-in-java">How
	 *      do you find the sum of all the numbers in an array in java?</a>
	 * @see <a
	 *      href="http://stackoverflow.com/questions/25360127/intstream-strange-error">IntStream
	 *      strange error</a>
	 * @see <a
	 *      href="http://stackoverflow.com/questions/960431/how-to-convert-listinteger-to-int-in-java">How
	 *      to convert List<Integer> to int[] in Java?</a>
	 * 
	 * @param count
	 *            the upper bound
	 * @return the sum of all the multiples under the upper bound
	 */
	static int sumOfMultiples8(int count) {
		List<Integer> multiples = new ArrayList<Integer>();
		for (int n = 0; n < count; n++) {
			if (isMultipleOfN(3, n)) {
				multiples.add(n);
			} else if (isMultipleOfN(5, n)) {
				multiples.add(n);
			}
		}
		
		// (1) Convert ArrayList to Integer array.
		Integer[] intArray = multiples.toArray(new Integer[multiples.size()]);
		
		// (2) Get an Integer stream.
		Stream<Integer> intStream = Arrays.stream(intArray);
		
		// (3) Using a delegate, sum the elements of the Integer stream.
		int sum = intStream.mapToInt(b -> b.intValue()).sum();
		return sum;
	}
	
	public static void main(String[] args) {		
		System.out.printf(
				"The sum of all multiples of 3 or 5 below %d is %d\n",
				ITERATIONS, sumOfMultiples(ITERATIONS));
		System.out.println("Java 8 style: " + sumOfMultiples8(ITERATIONS));		
	}

	// Unit tests.
	
	/**
	 * (1) Tests for multiples of 3 and 5 under 10.
	 */
	public void testMultiplesOf3And5() {
		for (int n = 0; n < 10; n++) {
			System.out.printf("For 3, n = %d: %b\n", n, isMultipleOfN(3, n));
			System.out.printf("For 5, n = %d: %b\n", n, isMultipleOfN(5, n));
		}
	}
}
