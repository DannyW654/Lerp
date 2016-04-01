/**
 * A representation of a number lerp expression.
 *
 * @author Arthur Nunes-Harwitt
 */
public class NumExp implements Expression {

    private double num;

    /**
     * Construct a number expression.
     *
     * @param num the double that is the number
     */
    public NumExp(double num){
       this.num = num;
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
    public Triple<ANFVarExp, ANFOp, Expression> extract(){
        ANFVarExp ve = new ANFVarExp();
        Holder h = new Holder(ve);
        return new Triple(ve,new ANFConstOp(num),h);
    }

    @Override
    public String toString(){
        return ""+this.num;
    }
}
