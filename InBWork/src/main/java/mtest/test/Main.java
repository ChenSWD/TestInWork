package mtest.test;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
	static final int COUNT_BITS = Integer.SIZE - 3;
	static final int CAPACITY = (1 << COUNT_BITS) - 1;

	// runState is stored in the high-order bits
	private static final int RUNNING = -1 << COUNT_BITS;
	private static final int SHUTDOWN = 0 << COUNT_BITS;
	private static final int STOP = 1 << COUNT_BITS;
	private static final int TIDYING = 2 << COUNT_BITS;
	private static final int TERMINATED = 3 << COUNT_BITS;

	// Packing and unpacking ctl
	private static int runStateOf(int c) {
		return c & ~CAPACITY;
	}

	private static int workerCountOf(int c) {
		return c & CAPACITY;
	}

	private static int ctlOf(int rs, int wc) {
		return rs | wc;
	}

	public static void main(String[] args) {
		String ss = "chen chen chen chen chen chen chen chen chen chen chen chen chen chen chen";

		System.out.println(Integer.toBinaryString(ss.hashCode() >>> 16));
		System.out.println(Integer.toBinaryString(ss.hashCode()));
		System.out.println(Integer.toBinaryString(ss.hashCode() ^ (ss.hashCode() >>> 16)));

		System.out.println(ss.hashCode() >>> 16);
		System.out.println(ss.hashCode());
		System.out.println(ss.hashCode() ^ (ss.hashCode() >>> 16));

		final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
		
		System.out.println(Integer.toBinaryString(-3));

		System.out.println("ctl.get() " + binary(ctl.get()) + "  " + ctl.get());
		System.out.println("CAPACITY " + binary(CAPACITY) + "  " + CAPACITY);
		System.out.println("RUNNING " + binary(RUNNING) + "  " + RUNNING);
		System.out.println("STOP " + binary(STOP) + "  " + STOP);
		System.out.println("TIDYING " + binary(TIDYING) + "  " + TIDYING);
		System.out.println("TERMINATED " + binary(TERMINATED) + "  " + TERMINATED);

		System.out.println("state " + binary(runStateOf(ctl.get())) + "  " + runStateOf(ctl.get()));
		ctl.getAndIncrement();
		System.out.println("count " + binary(workerCountOf(ctl.get())) + "  " + workerCountOf(ctl.get()));
	}

	private static String binary(int count) {
		String ss = Integer.toBinaryString(count);
		if (ss.length() < 32) {
			int c = 32 - ss.length();
			for (int i = 0; i < c; i++) {
				ss = "0" + ss;
			}
		}
		return ss;
	}
}