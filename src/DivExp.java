/**
 * A representation of the division lerp expression.
 *
 * @author Arthur Nunes-Harwitt
 */
public class DivExp extends BinaryExp {

    /**
     * Construct an division expression.
     *
     * @param exp1 the Expression that is the first operand of the
     * division expression
     * @param exp2 the Expression that is the second operand of the
     * division expression
     */
    public DivExp(Expression exp1, Expression exp2){
        super(exp1, exp2);
    }

    @Override
    public Triple<ANFVarExp, ANFOp, Expression> extract(){
        if (super.getExp1() instanceof Holder && super.getExp2() instanceof Holder){
            ANFVarExp ve = new ANFVarExp();
            ANFDivOp a = new ANFDivOp(((Holder) super.getExp1()).getVar(),((Holder)super.getExp2()).getVar());
            Holder h = new Holder(ve);
            return new Triple(ve, a, h);
        }
        else if (super.getExp1() instanceof Holder){
            Triple<ANFVarExp, ANFOp, Expression> data = super.getExp2().extract();
            return new Triple(data.first(), data.second(), new DivExp(super.getExp1(), data.third()));
        }
        else{
            Triple<ANFVarExp, ANFOp, Expression> data = super.getExp1().extract();
            return new Triple(data.first(), data.second(), new DivExp(data.third(), super.getExp2()));
        }
    }

    @Override
    public String toString(){
        return "(/ " + super.toString() + ")";
    }
}
