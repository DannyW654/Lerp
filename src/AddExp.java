/**
 * A representation of the addition lerp expression.
 *
 * @author Arthur Nunes-Harwitt
 */
public class AddExp extends BinaryExp {

    /**
     * Construct an addition expression.
     *
     * @param exp1 the Expression that is the first operand of the
     * addition expression
     * @param exp2 the Expression that is the second operand of the
     * addition expression
     */
    public AddExp(Expression exp1, Expression exp2){
        super(exp1, exp2);
    }

    @Override
    public Triple<ANFVarExp, ANFOp, Expression> extract(){
        if (super.getExp1() instanceof Holder && super.getExp2() instanceof Holder){
            ANFVarExp ve = new ANFVarExp();
            ANFAddOp a = new ANFAddOp(((Holder) super.getExp1()).getVar(),((Holder)super.getExp2()).getVar());
            Holder h = new Holder(ve);
         return new Triple(ve, a, h);
        }
        else if (super.getExp1() instanceof Holder){
                Triple<ANFVarExp, ANFOp, Expression> data = super.getExp2().extract();
                return new Triple(data.first(), data.second(), new AddExp(super.getExp1(), data.third()));
        }
        else{
                Triple<ANFVarExp, ANFOp, Expression> data = super.getExp1().extract();
                return new Triple(data.first(), data.second(), new AddExp(data.third(), super.getExp2()));
        }
    }

    @Override
    public String toString(){
        return "(+ " + super.toString() + ")";
    }
}
