/* Expression:
 *  represents a generic expression, which can be specialised as
 *      - string (property is equal to value, property pattern matching)
 *      - numeric (property greater/lower than value)
 *  also keeps track of optional boolean expressions, for example <exp> OR <exp>
 */

public abstract class Expression {
    protected String property;
    protected BooleanOperation operation;
    protected BooleanNegation not;

    public Expression(String property) {
        this.property = property.toLowerCase();
        this.operation = null;
        this.not = null;
    }

    // Get
    public String getProperty()  { return this.property; }
    public boolean hasAnd()      { return this.operation == BooleanOperation.AND; }
    public boolean hasOr()       { return this.operation == BooleanOperation.OR; }
    public boolean hasNegation() { return this.not == BooleanNegation.NOT; }
    public boolean hasBooleanOperation() { return this.operation != null; }

    // Set
    public void setBooleanOperation(BooleanOperation operation) { this.operation = operation; }
    public void setNot(BooleanNegation not) { this.not = not; }

    // String generation
    protected String boolToString() {
        String out = "";
        if (this.operation == BooleanOperation.AND)
            out = "AND" + " ";
        if (this.operation == BooleanOperation.OR)
            out = "OR" + " ";
        if (this.not == BooleanNegation.NOT)
            out = out + "NOT" + " ";
        return out;
    }
    @Override
    public abstract String toString();
}
