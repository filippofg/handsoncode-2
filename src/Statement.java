public interface Statement {
    // Preliminar check + expression insertion
    public void insert(Expression expr);

    // Get the next expression
    //public Expression next();

    // Get the previous expression
    //public Expression prev();

    // Generate a parsable string
    @Override
    public String toString();
}
