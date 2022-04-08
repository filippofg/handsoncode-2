/* Class AndOrExpression:
 *  represents a boolean expression "AND"/"OR".
 */

public class AndOrExpression implements BooleanExpression {
    enum AndOr {
        AND,
        OR
    };

    private AndOr type;

    public AndOrExpression(AndOr type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String out = null;
        if (type == AndOr.AND)
            out = "AND";
        else if (type == AndOr.OR)
            out = "OR";

        return out;
    }
}
