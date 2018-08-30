package mtest.test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		String ss="chen chen chen chen chen chen chen chen chen chen chen chen chen chen chen";

		System.out.println(Integer.toBinaryString(ss.hashCode()>>>16));
		System.out.println(Integer.toBinaryString(ss.hashCode()));
		System.out.println(Integer.toBinaryString(ss.hashCode()^(ss.hashCode()>>>16)));
		
		System.out.println(ss.hashCode()>>>16);
		System.out.println(ss.hashCode());
		System.out.println(ss.hashCode()^(ss.hashCode()>>>16));
		
	}
}