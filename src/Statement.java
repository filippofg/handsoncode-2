/* Interface Statement:
 *  represents a generic statement, which can be specialised as
 *      - string (property is equal to value, property pattern matching)
 *      - numeric (property greater/lower than value)
 *      - boolean (AND, OR, NOT)
 */

public interface Statement {

    @Override
    public String toString();
}
