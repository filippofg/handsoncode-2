/* ExpressionStack:
 *  represents a statement formed by multiple expressions.
 */

import java.util.Stack;

public class ExpressionStack {
    // Contains multiple expressions which form a query
    private final Stack<Expression> stack;
    // Auxiliary variables for the insertion of boolean expressions
    private BooleanOperation operation; // AND/OR
    private BooleanNegation not;        // NOT

    public ExpressionStack() {
        stack = new Stack<>();
        operation = null;
        not   = null;
    }

    public Stack<Expression> getStack() { return stack; }

    // Set auxiliary flag to AND/OR
    public void enableBooleanOperation(BooleanOperation operation) {
        if (this.operation != null) {
            String errorMessage = "Illegal expression order! Valid sequences are:\n" +
                    "\t [NOT] String/Numeric\n" +
                    "\t [NOT] String/Numeric AND/OR [NOT] String/Numeric AND/OR [NOT] ...";
            throw new IllegalStatementException(errorMessage);
        } else
            this.operation = operation;
    }
    // Set auxiliary flag to NOT
    public void enableNegation() {
        if (not != null) {
            String errorMessage = "Illegal expression order! Valid sequences are:\n" +
                    "\t [NOT] String/Numeric\n" +
                    "\t [NOT] String/Numeric AND/OR [NOT] String/Numeric AND/OR [NOT] ...";
            throw new IllegalStatementException(errorMessage);
        } else
            not = BooleanNegation.NOT;
    }

    /* Performs some sanity checks before inserting the new expression
     * into the dedicated data structure.
     */
    private boolean checkBeforeInsert(Expression expr) {
        // andOr, negation checks
        if (this.operation != null) {
            expr.setBooleanOperation(this.operation);
            this.operation = null;
        }
        if (not != null) {
            expr.setNot(not);
            not = null;
        }

        // Expression order checks
        if (    /* stack is empty and new expression does not have a boolean operation AND/OR */
                (stack.isEmpty() && !expr.hasBooleanOperation()) ||
                        (!stack.isEmpty() && expr.hasBooleanOperation())
        ) {
            return true;
        }
        return false;
    }

//
//        if (    /* Stack is empty and new expression doesn't have a boolean operation AND/OR */
//                (stack.isEmpty() && (!expr.hasBooleanAndOr())) ||
//                /* Stack is not empty and new expression has a boolean operation AND/OR */
//                ((!stack.isEmpty() && expr.hasBooleanAndOr())) ||
//
//        )
//            return false;

    public void insert(Expression expr) {
        if (checkBeforeInsert(expr)) {
            stack.push(expr);
        } else {
            String errorMessage = "Illegal expression order! Valid sequences are:\n" +
                    "\t [NOT] String/Numeric\n" +
                    "\t [NOT] String/Numeric AND/OR [NOT] String/Numeric AND/OR [NOT] ...";
            throw new IllegalStatementException(errorMessage);
        }
    }

    /* Performs a sanity check before the append operation
     * to prevent an inconsistent order of expressions.
     * Accepted sequences of expressions are:
     *      [NOT] String/Numeric
     *      [NOT] String/Numeric AND/OR [NOT] String/Numeric AND/OR [NOT] ...
     */
    /* TODO FIXME
     * this "instanceof" approach doesn't feel much scalable.
     * Maybe use abstract classes and check attributes?
     */
//    private boolean checkBeforeAppend(Expression e) {
//        if (    /* list is empty and new expression isn't "AND"/"OR" */
//                (stack.isEmpty() && !(e instanceof AndOrExpression)) ||
//                /* new expression is string and the previous one is boolean */
//                (e instanceof StringExpression && stack.get(stack.size()-1) instanceof BooleanExpression) ||
//                /* new expression is numeric and the previous one is boolean*/
//                (e instanceof NumericExpression && stack.get(stack.size()-1) instanceof BooleanExpression) ||
//                /* new expression is "AND"/"OR" and last one is string*/
//                (e instanceof AndOrExpression && stack.get(stack.size()-1) instanceof StringExpression) ||
//                /* new expression is "AND"/"OR" and last one is numeric */
//                (e instanceof AndOrExpression && stack.get(stack.size()-1) instanceof NumericExpression) ||
//                /* new expression is "NOT" and last one is "AND"/"OR"*/
//                (e instanceof NotExpression && stack.get(stack.size()-1) instanceof AndOrExpression)
//        ) {
//            return true;
//        }
//        return false;
//    }

    /* Generate a string based on the current filter conditions.
     * This string can be parsed in the Filter constructor
     * to build a new object with the same conditions.
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (Expression exp: stack) {
            if (exp == null) {
                out = new StringBuilder("null")
                        .append(exp.toString())
                        .append(" ");
            } else {
                out = out.append(exp.toString())
                        .append(" ");
            }
        }
        return out.toString();
    }
}
