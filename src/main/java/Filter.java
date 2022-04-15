/* Class Filter:
 *  represents a filter which determines whether
 *  a resource matches a given set of criteria.
 */

import java.util.Iterator;
import java.util.Map;

public class Filter implements FilterOperations {
    private final Query query;
    private Iterator<Expression> queryIterator;

    public Filter(Query query) {
        this.query = query;
        this.queryIterator = query.getDescendingIterator();
    }

    /* Query a resource with the specified filter conditions.
     * Returns `true` if there is a match,
     * otherwise returns `false`.
     */
    @Override
    public boolean matches(Map<String,String> resource) {
        // Get current expression
        Expression expr = queryIterator.next();
        // Property, value
        String property = expr.getProperty();
        // Partial result
        Boolean result = null;

        // If the specified property exists
        if (resource.containsKey(property)) {
            // String expression handling
            if (expr instanceof StringExpression) {
                String value = ((StringExpression) expr).getValue();
                result       = resource.get(property).equals(value);
            // Numeric expression handling
            } else if (expr instanceof NumericExpression) {
                int value           = ((NumericExpression) expr).getValue();
                int propertyNumeric = Integer.parseInt(resource.get(property));

                result = switch (((NumericExpression) expr).getNumericOperation()) {
                    case GREATER_THAN -> propertyNumeric > value;
                    case LESSER_THAN ->  propertyNumeric < value;
                };
            }
        } else {
            // If the specified property does not exist
            result = false;
        }
        // Negate the partial result if the expression contains a NOT
        if (expr.hasNegation())
            result = !result;
        // Boolean operation handling
        if (queryIterator.hasNext()) {
            if (expr.hasAnd())
                return result && matches(resource);
            else if (expr.hasOr())
                return result || matches(resource);
        }
        // If this is the last expression
        // reset the iterator (horrible)
        this.resetIterator();
        // simply return the partial result
        return result;
    }

    private void resetIterator() {
        this.queryIterator = this.query.getDescendingIterator();
    }

    @Override
    public String toString() {
        return this.query.toString();
    }
}
