import java.util.ArrayList;
import java.util.List;

public class Filter {
    private List<Statement> query;

    public Filter() {
        query = new ArrayList<>();
    }

    // Add a new "property = string" statement
    public Filter equalsTo(String property, String value) {
        query.add(new EqualsStatement(property.toLowerCase(), value));
        return this;
    }

    // Add a new "property > num" statement
    public Filter greaterThan(String property, int num) {
        ;
        // query.add( new GreaterStatement ( property, num ) );
        return this;
    }

    public Filter lowerThan(String property, int num) {
        // query.add(new LowerStatement(property, num));
        return this;
    }

    //
    public Filter and() {
        query.add(new Statement() {
        });
        return this;
    }


}
