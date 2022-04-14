import java.util.LinkedList;
import java.util.Iterator;

public class Query {
    // Contains multiple expressions which form a query
    private final LinkedList<Expression> list;

    public Query() {
        this.list = new LinkedList<>();
    }

    public Iterator<Expression> getDescendingIterator() { return this.list.descendingIterator(); }
    public LinkedList<Expression> getList() { return this.list; }

    /* Generate a string based on the current filter conditions.
     * This string can be parsed in the Filter constructor
     * to build a new object with the same conditions.
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (Expression expr: list) {
            out.append(expr.toString()).append(" ");
        }
        return out.toString().toString();
    }
}
