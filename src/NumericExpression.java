
public class NumericExpression extends Expression {
    private int value;
    private NumericExpressionType operationType;

    public NumericExpression(String property, int value, NumericExpressionType operationType) {
        super(property);
        this.value = value;
        this.operationType = operationType;
    }

    public int getValue() { return value; }
    public NumericExpressionType getOperationType() { return operationType; }

    @Override
    public String toString() {
        String operation = null;
        switch (operationType) {
            case GREATER_THAN:
                operation = ">";
                break;
            case LESSER_THAN:
                operation = "<";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operationType);
        }

        return boolToString() + property + " " + operation + " " + value;
    }
}
