/**
 * An abstraction for data structures that represent expressions in 
 * A-Normal Form to be compiled.
 *
 * @author Arthur Nunes-Harwitt
 */
public interface ANFExp {

    /**
     * Compile the ANF-expression to target code.
     *
     * @param m the Machine where the instructions are being added
     */
    void compile(Machine m);

    /**
     * Show the ANF-expression using text so it can be understood by a
     * person.  The text is either of a variable or of a
     * let-expression.
     *
     * @return a short String describing the ANF-expression.  
     */
    @Override
    String toString();
}
