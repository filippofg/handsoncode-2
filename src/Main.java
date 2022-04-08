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
    }
}
