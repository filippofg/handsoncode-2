import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String,String> user = new HashMap<>();
        user.put("firstname", "Joe");
        user.put("lastname", "Bloggs");
        user.put("role", "administrator");
        user.put("age", "35");

        Filter f = new Filter(new QueryBuilder()
                .greater("age", 25)
                .or()
                .not()
                .equal("firstname", "Joe")
                .and() /* this should not work */
                .build()
        );

        Filter filter = new Filter(new QueryBuilder()
                .equal("role", "administrator")
                .and()
                .greater("age", 30)
                .build()
        );

        System.out.println(filter);
        System.err.println(filter.matches(user));

        Filter f1 = new Filter(null);
        //System.err.println(f1);
        System.err.println(f1.matches(user));

        user.put("age", "25");
        // FIXME: Filter.match method makes Filter object unusable beacuse of empty stack
        // TODO: Stack is deprecated (?)
        // System.err.println(filter.match(user));


    }
}
