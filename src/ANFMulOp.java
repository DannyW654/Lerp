/**
 * A representation of the multiplication operation for
 * ANF-expressions.
 *
 * @author Arthur Nunes-Harwitt
 */
public class ANFMulOp extends ANFBinOp {

    /**
     * Construct an ANF multiplication operation.
     *
     * @param x1 the ANFVarExp that is the first operand of the
     * multiplication operation
     * @param x2 the ANFVarExp that is the second operand of the
     * multiplication operation
     */
    public ANFMulOp(ANFVarExp x1, ANFVarExp x2){
        super(x1, x2);
    }

    @Override
    public void compile(int dest, Machine m){
        m.addMul(dest, super.getX1().getN(), super.getX2().getN());
    }

    @Override
    public String toString(){
        return "(* " + super.toString() + ")";
    }
    
}
