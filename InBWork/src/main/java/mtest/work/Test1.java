package mtest.work;

public class Test1 {

	public static void main(String[] args){
		Integer aa = 200000000;
		Integer bb = 200000000;
		System.out.println(aa==bb);
		TestEntity entity = gettt();
		System.out.println(entity.getA());
	}
	
	public static <T extends TestEntity1> T gettt() {
		return (T) new TestEntity();
	}
	
	private static class TestEntity extends TestEntity1{
		private int mainI = 10;
		private static int mm = 20;
		public TestEntity() {
		}
		private String aa = "test_test";
		public String getA() {
			mm = mainI;
			getB();
			return aa;
		}
		public void getB() {
			
		}
	}
	
	private static class TestEntity1 {
		
	}
	
	public <T,E> T get(T t,E e) {
		return t;
	}
}
