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

    /* Filter with a StringExpression and a NumericExpression;
     * call 'matches' on the resource, then modify it and
     * call the method again.
     */
    @Test
    void matchesReusability() {
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

    /* Complex filter:
     * property = value OR NOT property = value AND property < value AND property > value
     */
    @Test
    void matchesComplex() {
        initResource();
        Filter filter = new Filter(new QueryBuilder()
                .not()
                .equal("firstname", "John")
                .or()
                .not()
                .equal("role", "user")
                .and()
                .lesser("age", 40)
                .and()
                .greater("age", 25)
                .build()
        );
        assertTrue(filter.matches(user));
    }

    // String generation: StringExpression
    @Test
    void generateStringFromStringExpression() {
        String property = "role";
        String value    = "administrator";
        Query query = new QueryBuilder()
                .equal(property, value)
                .build();
        assertEquals(property + " = " + value, query.toString());
    }

    // String generation: NumericExpression
    @Test
    void generateStringFromNumericExpression() {
        String property = "age";
        int value       = 25;
        Query queryGreater = new QueryBuilder()
                .greater(property, value)
                .build();
        Query queryLesser = new QueryBuilder()
                .lesser(property, value)
                .build();
        assertEquals(property + " > " + value, queryGreater.toString());
        assertEquals(property + " < " + value, queryLesser.toString());
    }

    // String generation: query with boolean operators AND, OR, NOT
    @Test
    void generateStringFromBooleanExpression() {
        Query query = new QueryBuilder()
                .not()
                .equal("role", "administrator")
                .and()
                .equal("firstname", "Joe")
                .or()
                .greater("age", 30)
                .build();
        assertEquals("NOT role = administrator AND firstname = Joe OR age > 30", query.toString());
    }

    // Empty query
    @Test
    void emptyQuery() {
        assertThrowsExactly(IllegalStatementException.class, () -> { new QueryBuilder().build(); });
    }
}