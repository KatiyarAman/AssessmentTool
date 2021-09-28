package com.sterp.multitenant.tenant.assesmenttool.util;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


/**
 * Contains a public method which shuffles the given list of objects.
 * @author Sachin Singh
 *
 */
public class ListShuffleUtil {
	
	private static class TempObjectHolder <T> {
		
		private T object;
		private Integer number;
		
		public T getObject() {
			return object;
		}
		
		public Integer getNumber() {
			return number;
		}
		
		
		public TempObjectHolder(T object, Integer number) {
			super();
			this.object = object;
			this.number = number;
		}
		
		
	}
	
	private static Integer getRandomInteger() {
		return new Random().nextInt(Integer.MAX_VALUE);
	}
	
	
	/**
	 * changes the order of elements in the list and returns it back.
	 * Space O(n)
	 */
	public static <T> List<T> suffleList(List<T> list) {
		if (list == null)
			return null;
		
		else if (list.isEmpty() || list.size() == 1)
			return list;
		
		else return list.stream()
			.map(obj -> new TempObjectHolder<T>(obj, getRandomInteger()))
			.sorted((a, b) -> a.getNumber().compareTo(b.getNumber()))
			.map(obj -> obj.getObject())
			.collect(Collectors.toList());
	}
}
