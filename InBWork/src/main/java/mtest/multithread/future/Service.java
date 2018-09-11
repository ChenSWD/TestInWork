package mtest.multithread.future;

public class Service {
	   public IData getData(final String str){
	        final FutureData futureData = new FutureData();
	        new Thread(new Runnable() {
	            @Override
	            public void run() {
	                RealData realData = new RealData(str);
	                futureData.setReault(realData);
	            }
	        }).start();
	        return futureData;
	    }
}
