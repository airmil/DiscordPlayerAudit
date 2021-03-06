package ddo.argonnessen.argonauts.arch;

import java.io.Serializable;
import java.util.Objects;

import ddo.argonnessen.argonauts.utils.Utils;

/**
 * A pair of two objects, the left and right objects.
 * 
 * @param <L>
 *            Type of the left object.
 * @param <R>
 *            Type of the right object.
 */
public class Pair<L, R> implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * creates a new pair from the first two elements of an array.
	 *
	 * @param <T>
	 *            Type of elements.
	 * @param array
	 *            Array.
	 * @return Returns a pair.
	 */
	public static <T> Pair<T, T> pair(T[] array) {
		return new Pair<T, T>(array[0], array[1]);
	}

	/**
	 * left object.
	 */
	private L left;
	/**
	 * right object.
	 */
	private R right;

	/**
	 * Creates a new Pair object.
	 *
	 */
	public Pair() {
		super();
	}

	/**
	 * Creates a new Pair object.
	 *
	 * @param left
	 *            the left
	 * @param right
	 *            the right
	 */
	public Pair(L left, R right) {
		this();
		this.left = left;
		this.right = right;
	}

	/**
	 * Gets the left.
	 *
	 * @return Returns the left
	 */
	public L getLeft() {
		return left;
	}

	/**
	 * Assigns a new value to the left.
	 *
	 * @param left
	 *            the left to set
	 */
	public void setLeft(L left) {
		this.left = left;
	}

	/**
	 * Gets the right.
	 *
	 * @return Returns the right
	 */
	public R getRight() {
		return right;
	}

	/**
	 * Assigns a new value to the right.
	 *
	 * @param right
	 *            the right to set
	 */
	public void setRight(R right) {
		this.right = right;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Pair) {
			@SuppressWarnings("rawtypes")
			Pair that = (Pair) obj;
			return Utils.equals(this.getLeft(), that.getLeft()) && Utils.equals(this.getRight(), that.getRight());
		}
		return false;
	}

	@Override
	public int hashCode() {
		Object[] fields = { left, right };
		return Utils.generateHashCode(fields);
	}

	@Override
	@SuppressWarnings("nls")
	public String toString() {
		String l = Objects.toString(left);
		String r = Objects.toString(right);
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(l);
		sb.append(" , ");
		sb.append(r);
		sb.append("]");
		return sb.toString();
	}
}
