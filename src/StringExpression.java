/* Interface StringExpression:
 *  represents an expression which compares a property
 *  to a string:
 *      property = string
 *  or performs pattern matching with a regular expression:
 *      property ~ regex
 */

interface StringExpression extends Expression {
}
