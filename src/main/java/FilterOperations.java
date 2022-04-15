import java.util.Map;

public interface FilterOperations {
    public boolean matches(Map<String,String> resource);
    @Override
    public String toString();
}
