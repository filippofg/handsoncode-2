/* EqualsExpression:
 *  represents an expression which compares a property
 *  to a string.
 *      <property> = <string>
 */
public class EqualsExpression implements StringExpression {
    private String property;
    private String value;

    public EqualsExpression(String property, String value) {
        this.property = property.toLowerCase();
        this.value    = value;
    }

    public String getProperty() { return property; }
    public String getValue()    { return value; }

    @Override
    public String toString() {
        return property + " = " + value;
    }
}
