/* Class Filter:
 *  represents a filter which determines whether
 *  a resource matches a given set of criteria.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    /* Query a resource with the specified filter conditions.
     * Returns `true` if there is a match,
     * otherwise returns `false`.
     */
    public boolean matches(Map<String,String> resource) {
        boolean result = false;
        boolean negate = false;
        List<Boolean> results = new ArrayList<>();
        for (Expression exp: query.getList()) {
            if (exp instanceof StringExpression) {
                String key   = ((StringExpression) exp).getProperty();
                String value = ((StringExpression) exp).getValue();

                /* Return true if the resource contains the current property
                 * and is equal to the specified value
                 */
//                if (resource.containsKey(key) && resource.get(key).equals(value)) {
//                    results.add(true);
//                }
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return query.toString();
    }
}
