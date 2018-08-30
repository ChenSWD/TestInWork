package mtest.atomic;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//AtomicInteger ≤‚ ‘
public class AtomicTest {
	public static void main(String[] args) {
		AtomicInteger integer = new AtomicInteger(0);
		integer.getAndSet(2);
		System.out.println(integer.get());
		integer.compareAndSet(0, 6);
		System.out.println(integer.get());
	}
}
