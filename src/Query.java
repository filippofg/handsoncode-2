import java.util.Stack;

public class Query {
    private final ExpressionStack query;

    public Query() {
        query = new ExpressionStack();
    }

    public Query equal(String property, String value) {
        query.insert(new StringExpression(property, value));
        return this;
    }

    public Query greater(String property, int value) {
        query.insert(new NumericExpression(property, value, NumericExpressionType.GREATER_THAN));
        return this;
    }

    public Query lesser(String property, int value) {
        query.insert(new NumericExpression(property, value, NumericExpressionType.LESSER_THAN));
        return this;
    }

    public Query not() {
        query.enableNegation();
        return this;
    }

    public Query and() {
        query.enableAndOr(BooleanAndOr.AND);
        return this;
    }

    public Query or() {
        query.enableAndOr(BooleanAndOr.OR);
        return this;
    }

    public Stack<Expression> getStack() { return query.getStack(); }

    @Override
    public String toString() {
        return query.toString();
    }
}
