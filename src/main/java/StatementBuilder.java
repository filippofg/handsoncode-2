public interface StatementBuilder {
    public StatementBuilder equal(String property, String value);
    public StatementBuilder greater(String property, int value);
    public StatementBuilder lesser(String property, int value);
    public StatementBuilder not();
    public StatementBuilder or();
    public StatementBuilder and();
    public Query build();
    public StatementBuilder newQuery();
}
