import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String,String> user = new HashMap<>();
        user.put("firstname", "Joe");
        user.put("lastname", "Bloggs");
        user.put("role", "administrator");
        user.put("age", "35");

        Filter f = new Filter(new Query()
                .greater("age", 25)
                .or()
                .not()
                .equal("firstname", "Joe")
                .and() /* this should not work */
        );

        Filter filter = new Filter(new Query()
                .equal("role", "administrator")
                .and()
                .greater("age", 30)
        );

        System.out.println(filter);
        System.err.println(filter.match(user));

        user.put("age", "25");
        // FIXME: Filter.match method makes Filter object unusable beacuse of empty stack
        // TODO: Stack is deprecated (?)
        // System.err.println(filter.match(user));


    }
}
