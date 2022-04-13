/* Class Filter:
 *  represents a filter which determines whether
 *  a resource matches a given set of criteria.
 */

import java.util.Map;

public class Filter implements FilterOperations {
    private Query query;

    public Filter(Query query) {
        this.query = query;
    }

    /* Query a resource with the specified filter conditions.
     * Returns `true` if there is a match,
     * otherwise returns `false`.
     */
    public boolean matches(Map<String,String> resource) {
        // Current expression
        Expression expr = this.query
        // Retrieve property
        String property = expr.getProperty();
        // Partial result
        Boolean result = null;
        // String comparison
        if (expr instanceof StringExpression) {
            // Retrieve value
            String value = ((StringExpression) expr).getValue();
            // If property does not exists, then
            // result = false. This ensures compatiblity
            // with boolean AND/OR if present
            if (resource.containsKey(property)) {
                result = resource.get(property).equals(value);
            } else
                result = false;

        } else if (expr instanceof NumericExpression) {
            int value = ((NumericExpression) expr).getValue();
            if (resource.containsKey(property)) {
                result = switch (((NumericExpression) expr).getNumericOperation()) {
                    case GREATER_THAN -> Integer.parseInt(resource.get(property)) > value;
                    case LESSER_THAN -> Integer.parseInt(resource.get(property)) < value;
                };
            } else
                result = false;
        }

        // Apply logic NOT
        if (expr.hasNegation())
            result = !result;
        // Recursive call based on the specified boolean operation (AND/OR)
        if (expr.hasAnd())
            return result && matches(resource);
        else if (expr.hasOr())
            return result || matches(resource);
        // Last element, when stack is empty, simply returns the partial result
        return result;
    }

    @Override
    public String toString() {
        return this.query.toString();
    }
}
