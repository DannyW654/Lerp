/**
 * An abstraction for data structures that represent expressions and
 * can be translated to A-Normal Form.
 *
 * @author Arthur Nunes-Harwitt
 */
public interface Expression {
    /**
     * Translate the expression represented by this object to A-Normal
     * Form.
     *
     * @return an ANFExp that is the translation of the expression
     */
    ANFExp toANF();

    /**
     * Extracts the innermost expression. It returns a triple related
     * to an ANF let expression which contains the innermost
     * expression, a variable associated with that expression, and the
     * remaining lerp expression.
     *
     * @return a triple containing an ANF variable, an ANF Operator,
     * and a lerp expression
     */
    Triple<ANFVarExp, ANFOp, Expression> extract();

    /**
     * Show the lerp expression using text so it can be understood by
     * a person.
     *
     * @return a short String describing the lerp expression.  
     */
    @Override
    String toString();
}
