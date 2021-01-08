/*******************************************************************************
 * Copyright (c) 2013 INTERAMERICAN PROPERTY AND CASUALTY INSURANCE COMPANY S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/copyleft/lesser.html
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 ******************************************************************************/
package ddo.argonnessen.argonauts.utils;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

/**
 * Utility class.
 */
public class Utils {

	/**
	 * Hidden constructor.
	 * 
	 * This is a utility class having only static methods. There is no need to
	 * create any instance of this class.
	 */
	private Utils() {
		/* empty */
	}


	/**
	 * Replaces a null with a default value.
	 * 
	 * @param <T>
	 *            Type of method arguments and return value.
	 * 
	 * @param val
	 *            Value checked.
	 * @param defaultVal
	 *            Default value
	 * @return Returns <code>val</code> if it is not null. Otherwise returns
	 *         <code>defaultVal</code>
	 */
	public static <T> T notNull(T val, T defaultVal) {
		return val != null ? val : defaultVal;
	}

	/**
	 * Returns true if the argument is null or if the argument is
	 * empty (applicable for arrays, collections and Strings)
	 * 
	 * TODO: Add other cases for which empty makes sense.
	 *
	 * @param obj
	 *            the obj
	 * @return Returns true if the argument is null or empty.
	 */
	public static Boolean isNullOrEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (Collection.class.isAssignableFrom(obj.getClass())) {
			return ((Collection<?>) obj).isEmpty();
		}
		if (Object[].class.isAssignableFrom(obj.getClass())) {
			return ((Object[]) obj).length == 0;
		}
		if (CharSequence.class.isAssignableFrom(obj.getClass())) {
			return ((CharSequence) obj).length() == 0;
		}
		return false;
	}

	/**
	 * Returns a default value if the argument is null or empty.
	 *
	 * @param <T>
	 *            the generic type
	 * @param val
	 *            the val
	 * @param defaultVal
	 *            the default val
	 * @return The val if it is not null, otherwise defaultVal.
	 * @see #isNullOrEmpty(Object)
	 */
	public static <T> T notNullOrEmpty(T val, T defaultVal) {
		if (isNullOrEmpty(val)) {
			return defaultVal;
		}
		return val;
	}

	/**
	 * Generates a hashcode from the hashcodes of the elements of an array.
	 *
	 * @param array
	 *            the array
	 * @return Returns a hashcode.
	 */
	public static int generateHashCode(Object[] array) {
		int hash = 0;
		int magicHash = 17;
		for (int i = 0; i < array.length; i++) {
			int elementHash = array[i] == null ? 0 : array[i].hashCode();
			hash = hash + magicHash + elementHash;
		}
		return hash;
	}

	/**
	 * Equality check.
	 *
	 * @param one
	 *            the one
	 * @param two
	 *            the two
	 * @return Returns true if one equals two.
	 */
	public static boolean equals(Object one, Object two) {
		if (one == two) {
			return true;
		}
		if (one == null || two == null) {
			return (one == null && two == null);
		}
		if (Iterable.class.isAssignableFrom(one.getClass())) {
			Iterable<?> iterOne = (Iterable<?>) one;
			Iterable<?> iterTwo = (Iterable<?>) two;
			return iterableEquals(iterOne, iterTwo);
		}
		if (Object[].class.isAssignableFrom(one.getClass())) {
			Object[] arrOne = (Object[]) one;
			Object[] arrTwo = (Object[]) two;
			return iterableEquals(Arrays.asList(arrOne), Arrays.asList(arrTwo));
		}
		if (one.getClass().isAssignableFrom(two.getClass())) {
			return one.equals(two);
		}
		if (two.getClass().isAssignableFrom(one.getClass())) {
			return two.equals(one);
		}
		return one.equals(two);
	}

	/**
	 * Checks equality between two collections.
	 * 
	 * Puts all objects of each collection to an OrderedSet and then checks
	 * equality of the two ordered sets.
	 * 
	 * @param one
	 *            First collection.
	 * @param two
	 *            Second collection.
	 * 
	 * @return Returns true if both collections have the same count of elements
	 *         and all corresponding elements of both collections are equal.
	 */
	private static boolean iterableEquals(Iterable<?> one, Iterable<?> two) {
		Object o1 = comparableCollection(one);
		Object o2 = comparableCollection(two);
		return o1.equals(o2);
	}

	/**
	 * Gets a hashmap that can be used to check equality of the
	 * elements of two iterables.
	 *
	 * @param iterable
	 *            the iterable
	 * @return Returns a hashmap.
	 */
	private static Object comparableCollection(Iterable<?> iterable) {
		HashMap<Integer, Object> map = new HashMap<Integer, Object>();
		for (Object object : iterable) {
			int hashcode = 0;
			if (object != null) {
				hashcode = object.hashCode();
			}
			map.put(hashcode, object);
		}
		return map;
	}

	/**
	 * Null safe comparison.
	 *
	 * @param <T>
	 *            the generic type
	 * @param left
	 *            the left
	 * @param right
	 *            the right
	 * @return If both arguments are null, otherwise returns the result of their comparison.
	 *         If one of the arguments is null, then it is assumed to be less than the other.
	 */
	public static <T extends Comparable<? super T>> int nullSafeCompare(T left, T right) {
		if (left == null & right == null) {
			return 0;
		}
		if (left == null) {
			return -1;
		}
		if (right == null) {
			return 1;
		}
		return left.compareTo(right);
	}

	/**
	 * Unsafe cast.
	 * 
	 * @param <T>
	 *            First type.
	 * @param <C>
	 *            Second type.
	 * @param var
	 *            Variable to cast.
	 * 
	 * @return Returns the variable cast.
	 */
	@SuppressWarnings("unchecked")
	public static <T, C> T cast(C var) {
		return (T) var;
	}
}
