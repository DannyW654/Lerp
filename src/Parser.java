/**
 * A class that contains the static methods to parse lerp expressions.
 * 
 * @author Arthur Nunes-Harwitt
 */

public class Parser {

    private static int pos;
    private static String[] tokens;
    private static int numCloseParen = 0; // to be used in the helper methods.


    /**
     * Build a data structure representation of the lerp expression
     * written as a string.
     * 
     * @param s a String that contains the text of a lerp expression
     * @return an Expression data structure representing the lerp expression
     */
    public static Expression parse(String s){
        tokens = s.replace("(", " ( ").replace(")", " ) ").trim().split("( )+");
        pos = 0;
        try{
            Expression e = getExpression();
            if (pos < tokens.length){
                Errors.error("Too much input.", null);
            } else {
                return e;
            }
        } catch (NumberFormatException e) {
            Errors.error("Expression must start with a number or open parenthesis.", null);
        } catch (Exception e) {
            Errors.error("Unexpected error ", e);
        }

        return null; // Shouldn't get here. To satisfy Java.
    }

   private static Expression makeExpressionStart(int current){
    String token = tokens[pos];
    ++pos;
    switch (token){
        case "(":
            numCloseParen += 1;
            return makeCompoundExpression(pos);
        default:
            return new NumExp(Double.parseDouble(token));
    }
   }

    private static Expression makeCompoundExpression(int current){
        String token = tokens[pos];
        ++pos;
        switch(token){
            case "+":
                return new AddExp(makeExpressionStart(pos),makeExpressionStart(pos));
            case "-":
                Expression data = makeExpressionStart(pos);
                if (pos >= tokens.length || (data instanceof NumExp && tokens[pos].equals(")"))){
                    return new NegExp(data);
                }
                else {
                    if (tokens[pos].equals(")")) {
                        pos++;
                    }
                    return new SubExp(data, makeExpressionStart(pos));
                }
            case "*":
                return new MulExp(makeExpressionStart(pos),makeExpressionStart(pos));
            case "/":
                return new DivExp(makeExpressionStart(pos),makeExpressionStart(pos));
            case "sqrt":
                return new SqrtExp(makeExpressionStart(pos));
            default:
                return makeExpressionStart(pos);


        }
    }
    private static Expression getExpression(){
        Expression result = makeExpressionStart(pos);
        while(numCloseParen != 0) {
            pos += 1;
            numCloseParen -= 1;
        }
        return result;
    }

}



