import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class Query implements Statement {
    // Contains multiple expressions which form a query
    private LinkedList<Expression> query;
    private Iterator<Expression> queryIterator;
    // Auxiliary variables for the insertion of boolean expressions
    private BooleanOperation operation; // AND/OR
    private BooleanNegation not;        // NOT

    public Query() {
        this.query = new LinkedList<>();
        this.queryIterator = null;
        this.operation = null;
        this.not = null;
    }

    // Set auxiliary flag to AND/OR
    public void enableBooleanOperation(BooleanOperation operation) {
        if (this.operation != null)
            throw new IllegalStatementException();
        else
            this.operation = operation;
    }

    // Set auxiliary flag to NOT
    public void enableNegation() {
        if (this.not != null)
            throw new IllegalStatementException();
        else
            this.not = BooleanNegation.NOT;
    }

    /* Performs some sanity checks before inserting the new expression
     * into the dedicated data structure, namely:
     *  - boolean flags checks
     *  - expression order checks
     * If all checks are passed then the new expression is added to the data structure.
     */
    private boolean checkBeforeInsert(Expression expr) {
        // Boolean operations/negation checks
        if (this.operation != null) {
            expr.setBooleanOperation(this.operation);
            this.operation = null;
        }
        if (not != null) {
            expr.setNot(not);
            not = null;
        }

        // Expression order checks
        if (
            /* query is empty and new expression does not have a boolean operation AND/OR */
            (this.query.isEmpty() && !expr.hasBooleanOperation()) ||
            /* query is not empty and new expression has boolean operator AND/OR */
            (!this.query.isEmpty() && expr.hasBooleanOperation())
        ) {
            return true;
        }
        return false;
    }

    @Override
    public void insert(Expression expr) {
        if (checkBeforeInsert(expr))
            this.query.add(expr);
        else
            throw new IllegalStatementException();
    }

    // Set iterator once query is finished
    public void setQueryIterator() {
        this.queryIterator = query.descendingIterator();
    }

    /* Generate a string based on the current filter conditions.
     * This string can be parsed in the Filter constructor
     * to build a new object with the same conditions.
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (Expression expr: query) {
            out.append(expr.toString()).append(" ");
        }
        return out.toString().toString();
    }
}
