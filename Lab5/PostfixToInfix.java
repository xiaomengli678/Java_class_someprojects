import java.util.*;

public class PostfixToInfix {

    /**
     * Data structure for intermediate expressions on the stack.
     */
    private static class IntermediateExpr {
        /** Subexpression string */
        public final String expr;

        /** Operator used to create this expression. */
        public final String operator;

        public IntermediateExpr(String expr, String operator) {
            this.expr = expr;
            this.operator = operator;
        }
    }

    /** Stack for holding intermediate infix expressions */
    private Deque<IntermediateExpr> stack = new LinkedList<>();

    /** Map operators to precedence */
    private Map<String, Integer> opPrecedence = new HashMap<>();

    public void addOperator(String operator, int precedence) {
        opPrecedence.put(operator, precedence);
    }

    public String convertExpr(String postfixExpr) {
        for(String token : postfixExpr.split(" ")) {
            if(opPrecedence.containsKey(token)) {
                IntermediateExpr rightIntermediate = stack.pop();
                IntermediateExpr leftIntermediate = stack.pop();

                int precedence = opPrecedence.get(token);

                String rightExpr = rightIntermediate.expr;
                String rightOp = rightIntermediate.operator;
                if(rightOp != null && opPrecedence.get(rightOp) < precedence) {
                    rightExpr = "(" + rightExpr + ")";
                }

                String leftExpr = leftIntermediate.expr;
                String leftOp = leftIntermediate.operator;
                if(leftOp != null && opPrecedence.get(leftOp) < precedence) {
                    leftExpr = "(" + leftExpr + ")";
                }

                String newExpr = leftExpr + " " + token + " " + rightExpr;
                stack.push(new IntermediateExpr(newExpr, token));
            } else {
                stack.push(new IntermediateExpr(token, null));
            }
        }
        return stack.pop().expr;
    }

    public static void main(String[] args) {
        PostfixToInfix converter = new PostfixToInfix();
        converter.addOperator("+", 1);
        converter.addOperator("-", 1);
        converter.addOperator("*", 2);
        converter.addOperator("/", 2);
        converter.addOperator("^", 3);

        Scanner sc = new Scanner(System.in).useDelimiter("\n");        
        while(sc.hasNext()) {
            System.out.println(converter.convertExpr(sc.next()));
        }
    }
}