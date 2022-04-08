public class EqualsExpression implements StringExpression {
    private String property;
    private String value;

    public EqualsExpression(String property, String value) {
        this.property = property;
        this.value    = value;
    }

    @Override
    public String toString() {
        return property + " = " + value;
    }
}
