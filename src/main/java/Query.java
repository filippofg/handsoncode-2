import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;

public class Query {
    // Contains multiple expressions which form a query
    private final LinkedList<Expression> list;

    public Query(LinkedList<Expression> list) {
        this.list = list;
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

        for (int i = 0; i < this.list.size(); i++) {
            out.append(this.list.get(i));
            /* This does not append an unnecessary whitespace
             * at the end of the generated string
             */
            if (i < this.list.size()-1)
                out.append(" ");
        }

        return out.toString();
    }
}
