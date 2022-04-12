/* Expression:
 *  represents a generic expression, which can be specialised as
 *      - string (property is equal to value, property pattern matching)
 *      - numeric (property greater/lower than value)
 *  also keeps track of optional boolean expressions, for example <exp> OR <exp>
 */

public abstract class Expression {
    protected String property;
    protected BooleanAndOr andOr;
    protected BooleanNegation not;

    public Expression(String property) {
        this.property = property.toLowerCase();
        this.andOr    = null;
        this.not      = null;
    }

    public Expression(BooleanAndOr andOr) {
        this.andOr = andOr;
        this.not = null;
    }

    public Expression(BooleanNegation not) {
        this.not   = not;
        this.andOr = null;
    }

    // Get
    public String getProperty()  { return property; }
    public boolean hasAnd()      { return andOr == BooleanAndOr.AND; }
    public boolean hasOr()       { return andOr == BooleanAndOr.OR; }
    public boolean hasNegation() { return not == BooleanNegation.NOT; }
    public boolean hasBooleanAndOr() { return (andOr != null); }

    // Set
    public void setAndOr(BooleanAndOr opType)    { andOr = opType; }
    public void setNot(BooleanNegation negation) { not = negation; }

    // String generation
    protected String boolToString() {
        String out = "";
        if (andOr == BooleanAndOr.AND)
            out = "AND" + " ";
        if (andOr == BooleanAndOr.OR)
            out = "OR" + " ";
        if (not == BooleanNegation.NOT)
            out = out + "NOT" + " ";
        return out;
    }
    @Override
    public abstract String toString();
}
