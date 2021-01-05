/**
 * 
 */
package ddo.argonnessen.argonauts.arch;


/**
 * @param <A> argument
 * @param <R> from
 * 
 */
public interface Transformation<A, R> {

	/**
	 * convert from A to R
	 * 
	 * @param a
	 * @return R
	 */
	public R execute(A a);
}
