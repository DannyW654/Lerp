/**
 * A representation of the negation lerp expression.
 *
 * @author Arthur Nunes-Harwitt
 */
public class NegExp extends UnaryExp {

    /**
     * Construct a negation expression.
     *
     * @param exp the Expression that is the first operand of the
     * negation expression
     */
    public NegExp(Expression exp){
        super(exp);
    }

    @Override
    public Triple<ANFVarExp, ANFOp, Expression> extract(){
        if (super.getExp() instanceof Holder){
            ANFVarExp ve = new ANFVarExp();
            Holder h = new Holder(ve);
            return new Triple(ve, new ANFNegOp(((Holder)super.getExp()).getVar()), h);
        }

        else{
            Triple<ANFVarExp, ANFOp, Expression> data = getExp().extract();
            return new Triple(data.first(), data.second(), new NegExp(data.third()));
        }
    }

    @Override
    public String toString(){
        return "(- " + super.toString() + ")";
    }
}
