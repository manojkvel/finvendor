package com.finvendor.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		/*List<String> string = new ArrayList();
		
		string.add("rayulu");
		string.add("vemula");
		string.add("rayulu");
		
		HashSet<String> hashSet = new HashSet<String>();
		hashSet.addAll(string);
		
		hashSet.clear();
		string.addAll(hashSet);
		
		 Iterator<String> iterator= string.listIterator();
		 
		 while(iterator.hasNext()){
			 System.out.println("value--:" +iterator.next());
		 }
		
		*/
		
		
		/*Integer array[]={1,2,3,1,4,5,6,7,8,7,6,5,4,3,2,1};
		int numDupes = 0;
		
		for (int i = 0; i < array.length; i++) {
			
			for (int j = 0; j < array.length; j++) {
				if(j!=i && array[i].equals(array[j])){
					numDupes++;
				}
			}
		}
		
		System.out.println("Duplicate Elements----:" + numDupes);
*/
		
		
	boolean check=	isAlpha("rayulu123");
	
	System.out.println("check---:" + check);
	
		
	}
	
	public static boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}
	

}
