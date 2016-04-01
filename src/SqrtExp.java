/**
 * A representation of the square root lerp expression.
 *
 * @author Arthur Nunes-Harwitt
 */
public class SqrtExp extends UnaryExp {

    /**
     * Construct a square root expression.
     *
     * @param exp the Expression that is the first operand of the
     * square root expression
     */
    public SqrtExp(Expression exp){
        super(exp);
    }

    @Override
    public Triple<ANFVarExp, ANFOp, Expression> extract(){
        if (super.getExp() instanceof Holder){
            ANFVarExp ve = new ANFVarExp();
            Holder h = new Holder(ve);
            return new Triple(ve, new ANFSqrtOp(((Holder)super.getExp()).getVar()), h);
        }
        else{
            Triple<ANFVarExp, ANFOp, Expression> data = getExp().extract();
            return new Triple(data.first(), data.second(), new SqrtExp(data.third()));
        }
    }

    @Override
    public String toString(){
        return "(sqrt " + super.toString() + ")";
    }
}
