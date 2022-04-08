/* Class Filter:
 *  represents a filter which determines whether
 *  a resource matches a given set of criteria.
 */

public class Filter {
    private ExpressionList query;

    public Filter() {
        query = new ExpressionList();
    }

    // String expressions:
    // Add a new "property = string" condition
    public Filter equalsTo(String property, String value) throws IllegalStatementException {
        query.append(new EqualsExpression(property, value));
        return this;
    }

    // Numerical expressions:
    // Add a new "property > num" condition
    public Filter greaterThan(String property, int number) throws IllegalStatementException {
        query.append(new GreaterExpression(property, number));
        return this;
    }
    // Add a new "property < num" condition
    public Filter lesserThan(String property, int number) throws IllegalStatementException {
        query.append(new LesserExpression(property, number));
        return this;
    }

    // Boolean expressions:
    // AND
    public Filter and() throws IllegalStatementException {
        query.append(new AndOrExpression(AndOrExpression.AndOr.AND));
        return this;
    }
    // OR
    public Filter or() throws IllegalStatementException {
        query.append(new AndOrExpression(AndOrExpression.AndOr.OR));
        return this;
    }
    // NOT
    public Filter not() throws IllegalStatementException {
        query.append(new NotExpression());
        return this;
    }


//    public Boolean matches(Map<String,String> resource) {
//        return true;
//    }

    @Override
    public String toString() {
        return query.toString();
    }
}
