/**
 * An abstraction for data structures that represent operations in 
 * A-Normal Form to be compiled.
 *
 * @author Arthur Nunes-Harwitt
 */
public interface ANFOp {
    /**
     * Compile the ANF-operation to target code.
     *
     * @param dest the int that represents the destination register
     * @param m the Machine where the instructions are being added
     */
    void compile(int dest, Machine m);

    /**
     * Show the ANF-operation using text so it can be understood by a
     * person.
     *
     * @return a short String describing using parenthesized prefix
     * notation what this operation is
     */
    @Override
    String toString();
}
