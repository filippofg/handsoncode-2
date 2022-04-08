/* Class ExpressionList:
 *  represents a statement formed by multiple expressions.
 */

import java.util.ArrayList;
import java.util.List;

public class ExpressionList {
    private List<Expression> list;

    public ExpressionList() {
        list = new ArrayList<>();
    }

    /* Performs a sanity check before the append operation
     * to prevent an inconsistent order of expressions.
     * Accepted sequences of expressions are:
     *      [NOT] String/Numeric
     *      [NOT] String/Numeric AND/OR [NOT] String/Numeric AND/OR [NOT] ...
     */
    public boolean insertionCheck(Expression e) {
        if (    /* list is empty and new expression isn't "AND"/"OR" */
                (list.isEmpty() && !(e instanceof AndOrExpression)) ||
                /* new expression is string and the previous one is boolean */
                (e instanceof StringExpression && list.get(list.size()-1) instanceof BooleanExpression) ||
                /* new expression is numeric and the previous one is boolean*/
                (e instanceof NumericExpression && list.get(list.size()-1) instanceof BooleanExpression) ||
                /* new expression is "AND"/"OR" and last one is string*/
                (e instanceof AndOrExpression && list.get(list.size()-1) instanceof StringExpression) ||
                /* new expression is "AND"/"OR" and last one is numeric */
                (e instanceof AndOrExpression && list.get(list.size()-1) instanceof NumericExpression) ||
                /* new expression is "NOT" and last one is "AND"/"OR"*/
                (e instanceof NotExpression && list.get(list.size()-1) instanceof AndOrExpression)
        ) {
            return true;
        }
        return false;
    }

    // Attempt to append new expresson to the list
    public void append(Expression e) throws IllegalStatementException {
        if (this.insertionCheck(e)) {
            list.add(e);
        } else {
            String message = "Illegal expression order! Valid sequences are:\n" +
                    "\t [NOT] String/Numeric\n" +
                    "\t [NOT] String/Numeric AND/OR [NOT] String/Numeric AND/OR [NOT] ...";
            throw new IllegalStatementException(message);
        }
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (Expression exp: list)
            out.append(exp.toString()).append(" ");
        return out.toString();
    }
}
