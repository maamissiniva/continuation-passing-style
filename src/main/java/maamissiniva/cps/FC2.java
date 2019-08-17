package maamissiniva.cps;

/**
 * Definition of a two argument function in its curried form.
 * <p>
 * This is for information as the FC2 is not equivalent to its 
 * expanded type. 
 *  
 * @author vrd
 *
 * @param <A>
 * @param <B>
 * @param <C>
 */
interface FC2<A,B,C> extends FC1<A,FC1<B,C>> {
}
