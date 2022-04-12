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
                .and()
                .equal("role", "administrator")
                .and()
                .greater("age", 25)
        );

        System.out.println();
        System.out.println(f);
    }
}
