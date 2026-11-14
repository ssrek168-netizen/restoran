public class Product {
    private String name;
    private int price; // цена за 100 грамм

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }

    @Override
    public String toString() {
        return name + " - " + price + " руб.";
    }
}

