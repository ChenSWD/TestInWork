package mtest.multithread.future;

public class FutureData implements IData {
	private RealData realData;
	private volatile boolean isReal = false;

	@Override
	public synchronized String getResult() {
		while (!isReal) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return realData.getResult();
	}

	public synchronized void setReault(RealData realData) {
		if (isReal) {
			return;
		}

		this.realData = realData;
		isReal = true;
		notifyAll();

	}
}
