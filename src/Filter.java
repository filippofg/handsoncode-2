/* Class Filter:
 *  represents a filter which determines whether
 *  a resource matches a given set of criteria.
 */

public class Filter {
    private Query query;

    public Filter(Query query) {
        this.query = query;
    }

    /* Query a resource with the specified filter conditions.
     * Returns `true` if there is a match,
     * otherwise returns `false`.
     */
    // public boolean match(Map<String,String> resource);


    @Override
    public String toString() {
        return query.toString();
    }
}
