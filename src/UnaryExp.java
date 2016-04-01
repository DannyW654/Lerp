/**
 * An abstract class to characterize the common data and methods
 * associated with unary expressions.
 *
 * @author Arthur Nunes-Harwitt
 */
public abstract class UnaryExp implements Expression {

    private Expression exp;

    /**
     * Initialize the operand.  This constructor can only be
     * invoked by a sub-class.
     *
     * @param exp the Expression that is the operand of the
     * unary expression
     */
    public UnaryExp(Expression exp){
        this.exp = exp;
    }

    /**
     * A selector for getting the operand from a unary expression.
     *
     * @return the Expression that is the operand of the unary expression
     */
    public Expression getExp(){
        return exp;
    }

    @Override
    public ANFExp toANF(){
        Triple<ANFVarExp, ANFOp, Expression> current = extract();
        if(current.third() instanceof Holder){
            return new ANFLetExp(current.first(), current.second(), current.first());
        }
        else{
            return new ANFLetExp(current.first(), current.second(), current.third().toANF());
        }
    }

    @Override
    public abstract Triple<ANFVarExp, ANFOp, Expression> extract();

    @Override
    public String toString(){
        return this.exp.toString();
    }
}
