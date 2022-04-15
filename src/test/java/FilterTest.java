import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class FilterTest {

    static Map<String,String> user = new HashMap<>();

    private void initResource() {
        user.put("firstname", "Joe");
        user.put("lastname", "Bloggs");
        user.put("role", "administrator");
        user.put("age", "35");
    }

    // Filter with wrong property case (provided: uppercase, needed: lowercase)
    @Test
    void matchesWrongPropertyCase() {
        initResource();
        Filter filter = new Filter(new QueryBuilder()
                .equal("FIRSTNAME", "Joe")
                .build()
        );
        assertTrue(filter.matches(user));
    }

    // Filter with wrong value case
    @Test
    void matchesWrongValueCase() {
        initResource();
        Filter filter = new Filter(new QueryBuilder()
                .equal("firstname", "JOE")
                .build()
        );
        assertFalse(filter.matches(user));
    }

    // Filter with both property and value in wrong case
    @Test
    void matchesCaseInsensitive() {
        initResource();
        Filter filter = new Filter(new QueryBuilder()
                .equal("FIRSTnAmE", "JOE")
                .build()
        );
        assertFalse(filter.matches(user));
    }

    // Filter with a StringExpression "property = value"
    @Test
    void matchesEqual() {
        initResource();
        Filter filter = new Filter(new QueryBuilder()
                .equal("firstname", "Joe")
                .build()
        );
        assertTrue(filter.matches(user));
    }

    /* Filter with a StringExpression and a NumericExpression
     *  property = value
     *  AND
     *  property > value
     */
    @Test
    void matchesEqualAndGreater() {
        initResource();
        Filter filter = new Filter(new QueryBuilder()
                .equal("role", "administrator")
                .and()
                .greater("age", 30)
                .build()
        );

        assertTrue(filter.matches(user));
        user.put("age", "25");
        assertFalse(filter.matches(user));
    }

}