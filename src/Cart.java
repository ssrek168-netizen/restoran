import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> items = new ArrayList<>();

    public void add(Product product) {
        items.add(product);
    }

    public void remove(Product product) {
        items.remove(product);
    }

    public List<Product> getItems() {
        return items;
    }

    public int getTotalPrice() {
        int sum = 0;
        for (Product p : items) {
            sum += p.getPrice();
        }
        return sum;
    }

    public void clear() {
        items.clear();
    }
}