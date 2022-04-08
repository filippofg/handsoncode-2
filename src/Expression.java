/* Interface Expression:
 *  represents a generic expression, which can be specialised as
 *      - string (property is equal to value, property pattern matching)
 *      - numeric (property greater/lower than value)
 *      - boolean (AND, OR, NOT)
 */

public interface Expression {
    // TODO
    @Override
    public String toString();
}
