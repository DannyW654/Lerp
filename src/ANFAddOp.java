/**
 * A representation of the addition operation for ANF-expressions.
 *
 * @author Arthur Nunes-Harwitt
 */
public class ANFAddOp extends ANFBinOp {

    /**
     * Construct an ANF addition operation.
     *
     * @param x1 the ANFVarExp that is the first operand of the
     * addition operation
     * @param x2 the ANFVarExp that is the second operand of the
     * addition operation
     */
    public ANFAddOp(ANFVarExp x1, ANFVarExp x2){
       super(x1, x2);
    }

    @Override
    public void compile(int dest, Machine m){
        m.addAdd(dest, super.getX1().getN(), super.getX2().getN());
    }

    @Override
    public String toString(){
        return "(+ " + super.toString() + ")";
    }
    
}
