public class EqualsStatement implements StringStatement {
    private String property;
    private String value;

    public EqualsStatement(String property, String value) {
        this.property = property;
        this.value    = value;
    }

    @Override
    public String toString() {
        return property + " = " + value;
    }
}
