import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String,String> user = new HashMap<>();
        user.put("firstname", "Joe");
        user.put("lastname", "Bloggs");
        user.put("role", "administrator");
        user.put("age", "35");

        StatementBuilder builder = new QueryBuilder();
        Filter f = new Filter(
                builder.lesser("age", 36)
                        .and()
                        .equal("age", "35")
                        .build()
        );

        Query q = builder.newQuery()
                .greater("age", 37)
                .or()
                .equal("lastname", "Bloggs")
                .build();
        Filter filter = new Filter(q);
        System.out.println("filter: " + filter);
        Filter filter1 = new Filter(new QueryBuilder()
                .not()
                .equal("role", "administratoR")
                .build()
        );
        System.out.println("filter1: "+filter1);
        System.out.println(f);
        System.out.println(f.matches(user));

        user.put("age", "25");
        System.out.println(f.matches(user));

    }
}
