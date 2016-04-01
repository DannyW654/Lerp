
/**
 * A representation of the square root operation for ANF-expressions.
 *
 * @author Arthur Nunes-Harwitt
 */
public class ANFSqrtOp extends ANFUnOp {

    /**
     * Construct an ANF square root operation.
     *
     * @param x the ANFVarExp that is the operand of the square root
     * operation
     */
    public ANFSqrtOp(ANFVarExp x){
        super(x);
    }

    @Override
    public void compile(int dest, Machine m){
        m.addSqrt(dest, super.getX().getN());
    }

    @Override
    public String toString(){
        return "(sqrt " + super.toString() + ")";
    }
    
}
