
public class NumericExpression extends Expression {
    private int value;
    private NumericExpressionType type;

    public NumericExpression(String property, int value, NumericExpressionType type) {
        super(property);
        this.value = value;
        this.type = type;
    }

    public int getValue() { return value; }

    @Override
    public String toString() {
        String operation = null;
        switch (type) {
            case GREATER_THAN:
                operation = ">";
                break;
            case LESSER_THAN:
                operation = "<";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        return boolToString() + property + " " + operation + " " + value;
    }
}
