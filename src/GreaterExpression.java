/* Class GreaterExpression:
 *  represents an expression of type
 *      property > number
 */
// TODO maybe merge GreaterExpression and LesserExpression into NumericExpression (same as AndOrExpression)?
public class GreaterExpression implements NumericExpression {
    private String property;
    private int number;

    public GreaterExpression(String property, int number) {
        this.property = property.toLowerCase();
        this.number   = number;
    }

    @Override
    public String toString() {
        return property + " > " + number;
    }
}
