import java.util.*;
class Product {
    private String id;
    private String name;
    private double price;
    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return id + ": " + name + " - $" + price;
    }
}
class CartItem {
    private Product product;
    private int quantity;
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
    @Override
    public String toString() {
        return product.getName() + " x " + quantity + " - $" + getTotalPrice();
    }
}
class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();
    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item = new CartItem(product, item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }
    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        double total = 0;
        for (CartItem item : items) {
            System.out.println(item);
            total += item.getTotalPrice();
        }
        System.out.println("Total: $" + total);
    }
    public double checkout() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        items.clear();
        return total;
    }
}
public class ECommercePlatform {
    private static Map<String, Product> productCatalog = new HashMap<>();
    private static ShoppingCart cart = new ShoppingCart();
    public static void main(String[] args) {
        productCatalog.put("P001", new Product("P001", "Laptop", 999.99));
        productCatalog.put("P002", new Product("P002", "Smartphone", 499.99));
        productCatalog.put("P003", new Product("P003", "Headphones", 89.99));
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- E-Commerce Platform ---");
            System.out.println("1. Browse Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  
            switch (choice) {
                case 1:
                    browseProducts();
                    break;
                case 2:
                    addToCart(scanner);
                    break;
                case 3:
                    cart.viewCart();
                    break;
                case 4:
                    double total = cart.checkout();
                    System.out.println("Thank you for your purchase! Total amount: $" + total);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void browseProducts() {
        System.out.println("\n--- Product Catalog ---");
        for (Product product : productCatalog.values()) {
            System.out.println(product);
        }
    }
    private static void addToCart(Scanner scanner) {
        System.out.print("Enter product ID to add to cart: ");
        String productId = scanner.nextLine();
        Product product = productCatalog.get(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        cart.addItem(product, quantity);
        System.out.println("Added to cart.");
    }
}
