
/**
 * A representation of the let ANF-expression.
 *
 * @author Arthur Nunes-Harwitt
 */
public class ANFLetExp implements ANFExp {

    private ANFVarExp x;
    private ANFOp op;
    private ANFExp body;

    /**
     * Construct a let ANF-expression.
     *
     * @param x the ANFVarExp variable that is associated with the operation
     * @param op the ANFOp that is the operation that is performed
     * whose value is associated with the variable
     * @param body The ANFExp that uses the variable
     */
    public ANFLetExp(ANFVarExp x, ANFOp op, ANFExp body){
        this.x = x;
        this.op = op;
        this.body = body;
    }

    @Override
    public void compile(Machine m){
        op.compile(x.getN(),m);
        body.compile(m);
    }

    @Override
    public String toString(){
        return "let " + this.x + " = " + this.op + "\n" + this.body;
    }
}
