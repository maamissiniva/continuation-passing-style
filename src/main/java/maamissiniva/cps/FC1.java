package maamissiniva.cps;

import maamissiniva.function.coc.F1;
import maamissiniva.function.coc.F2;

/**
 * This makes explicit the nature of the conversion from functions 
 * ({@literal FC1<A,B>} represents a function from A to B) to continuations.
 * <p>
 * As this is FC1 is not equivalent to its expanded definition, this interface
 * is defined for information purpose.
 *  
 * @author vrd
 *
 * @param <A> argument type
 * @param <B> return type
 */
public interface FC1<A,B> extends F2<A,F1<B,Step>,Step> {
}
