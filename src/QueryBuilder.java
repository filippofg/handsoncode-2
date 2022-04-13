
public class QueryBuilder {
    private final Query query;

    public QueryBuilder() {
        this.query = new Query();
    }

    public QueryBuilder equal(String property, String value) {
        this.query.insert(new StringExpression(property, value));
        return this;
    }

    public QueryBuilder greater(String property, int value) {
        this.query.insert(new NumericExpression(property, value, NumericExpressionType.GREATER_THAN));
        return this;
    }

    public QueryBuilder lesser(String property, int value) {
        this.query.insert(new NumericExpression(property, value, NumericExpressionType.LESSER_THAN));
        return this;
    }

    public QueryBuilder not() {
        this.query.enableNegation();
        return this;
    }

    public QueryBuilder and() {
        this.query.enableBooleanOperation(BooleanOperation.AND);
        return this;
    }

    public QueryBuilder or() {
        this.query.enableBooleanOperation(BooleanOperation.OR);
        return this;
    }

    public Query build() {
        // Performs trailing '.and()' '.or()' '.not()' removal
        // TODO ...
        // Return query object
        query.setQueryIterator();
        return this.query;
    }

    @Override
    public String toString() {
        return query.toString();
    }
}
