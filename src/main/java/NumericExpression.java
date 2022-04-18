
public class NumericExpression extends Expression {
    private int value;
    private NumericExpressionType numericOperation;

    public NumericExpression(String property, int value, NumericExpressionType numericOperation) {
        super(property);
        this.value = value;
        this.numericOperation = numericOperation;
    }

    public int getValue() { return this.value; }
    public NumericExpressionType getNumericOperation() { return this.numericOperation; }

    @Override
    public String toString() {
        String op = "";
        switch (this.numericOperation) {
            case GREATER_THAN:
                op = ">";
                break;
            case LESSER_THAN:
                op = "<";
                break;
        };
        return boolToString() + this.property + " " + op + " " + this.value;
    }
}
