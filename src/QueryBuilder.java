
public class QueryBuilder {
    private Query query;
    private BooleanOperation operation;
    private BooleanNegation not;

    public QueryBuilder() {
        this.query = new Query();
        this.operation = null;
        this.not = null;
    }
    // property = value
    public QueryBuilder equal(String property, String value) {
        this.insert(new StringExpression(property, value));
        return this;
    }
    // property > value
    public QueryBuilder greater(String property, int value) {
        this.insert(new NumericExpression(property, value, NumericExpressionType.GREATER_THAN));
        return this;
    }
    // property < value
    public QueryBuilder lesser(String property, int value) {
        this.insert(new NumericExpression(property, value, NumericExpressionType.LESSER_THAN));
        return this;
    }
    // NOT <expression>
    public QueryBuilder not() {
        this.enableNegation();
        return this;
    }
    // <expression> AND <expression>
    public QueryBuilder and() {
        this.enableBooleanOperation(BooleanOperation.AND);
        return this;
    }
    // <expression> OR <expression>
    public QueryBuilder or() {
        this.enableBooleanOperation(BooleanOperation.OR);
        return this;
    }

    /* Performs last checks before building the expression
     * then returns the Query object
     */
    public Query build() {
        // Performs checks of remaining '.and()' '.or()' '.not()'
        if (operation != null || not != null) {
            throw new IllegalStatementException();
        }
        return this.query;
    }

    // Set auxiliary flag to AND/OR
    private void enableBooleanOperation(BooleanOperation operation) {
        if (this.operation != null)
            throw new IllegalStatementException();
        else
            this.operation = operation;
    }

    // Set auxiliary flag to NOT
    private void enableNegation() {
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
            (this.query.getList().isEmpty() && !expr.hasBooleanOperation()) ||
            /* query is not empty and new expression has boolean operator AND/OR */
            (!this.query.getList().isEmpty() && expr.hasBooleanOperation())
        ) {
            return true;
        }
        return false;
    }

    // Insert expression in the expression list
    private void insert(Expression expr) {
        if (checkBeforeInsert(expr))
            this.query.getList().add(expr);
        else
            throw new IllegalStatementException();
    }
}
