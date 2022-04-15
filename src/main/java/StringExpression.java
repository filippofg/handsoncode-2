/* StringExpression:
 *  represents an expression which compares a property
 *  to a string:
 *      property = string
 */

public class StringExpression extends Expression {
    private String value;

    public StringExpression(String property, String value) {
        super(property);
        this.value = value;
    }

    public String getValue() { return this.value; }

    @Override
    public String toString() {
        return boolToString() + this.property + " = " + this.value;
    }
}
