package test;

import SB6.Fibonacci;
import org.junit.Test;

public class FibonacciTest {

	@Test
	public void testKonstruktor() {
		Fibonacci fibonacci = new Fibonacci();
	}

	@Test
	public void testFib() {
		Fibonacci fibonacci = new Fibonacci();
		//FIXME: Ausgabe testen!?
		fibonacci.berechneFibonacciBisElement(10);
	}
}
