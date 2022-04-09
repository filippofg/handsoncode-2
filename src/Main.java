import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String,String> user = new HashMap<>();
        user.put("firstname", "Joe");
        user.put("lastname", "Bloggs");
        user.put("role", "administrator");
        user.put("age", "35");

        /*
        // Create a filter which matches all administrators older than 30:
        Filter filter = new Filter()
                .equalsTo("role", "administrator")
                .and()
                .greaterThan("age", 30);
        */
        Filter filter = null;
        try {
            filter = new Filter()
                    .equalsTo("Role", "administrator")
                    .or()
                    .not()
                    .greaterThan("age", 35);
        } catch (IllegalStatementException e) {
            System.err.println(e);
            System.exit(1);
        }

        Filter filter1 = null;
        try {
            filter1 = new Filter()
                    .equalsTo("firstname", "joe");
        } catch (IllegalStatementException e) {
            System.err.println(e);
            System.exit(1);
        }

        System.out.println(filter1);
        System.out.println(filter1.matches(user));

        System.out.println();
        System.out.println(filter);
    }
}
