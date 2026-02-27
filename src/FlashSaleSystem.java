import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FlashSaleSystem {
    private final Map<String, AtomicInteger> inventory = new ConcurrentHashMap<>();
    private final Map<String, Queue<Integer>> waitingList = new ConcurrentHashMap<>();

    // Demo Main
    public static void main(String[] args) throws InterruptedException {

        FlashSaleSystem system = new FlashSaleSystem();
        system.addProduct("IPHONE15_256GB", 5);

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 1; i <= 10; i++) {
            final int userId = i;
            executor.submit(() -> {
                String result = system.purchaseItem("IPHONE15_256GB", userId);
                System.out.println("User " + userId + ": " + result);
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Final Stock: " + system.checkStock("IPHONE15_256GB"));
    }

    // Add Product
    public void addProduct(String productId, int stock) {
        inventory.put(productId, new AtomicInteger(stock));
        waitingList.put(productId, new ConcurrentLinkedQueue<>());
    }

    // Check Stock (O(1))
    public int checkStock(String productId) {
        AtomicInteger stock = inventory.get(productId);
        return stock == null ? 0 : stock.get();
    }

    // Purchase Item
    public String purchaseItem(String productId, int userId) {

        AtomicInteger stock = inventory.get(productId);

        if (stock == null) {
            return "Product does not exist";
        }

        while (true) {
            int currentStock = stock.get();

            if (currentStock > 0) {
                if (stock.compareAndSet(currentStock, currentStock - 1)) {
                    return "Success! Remaining stock: " + (currentStock - 1);
                }
            } else {
                Queue<Integer> queue = waitingList.get(productId);
                queue.add(userId);
                return "Out of stock. Added to waiting list. Position: " + queue.size();
            }
        }
    }

    // Process Cancellation (Optional)
    public String restock(String productId, int quantity) {

        AtomicInteger stock = inventory.get(productId);
        Queue<Integer> queue = waitingList.get(productId);

        if (stock == null) return "Product not found";

        for (int i = 0; i < quantity; i++) {
            Integer nextUser = queue.poll();

            if (nextUser != null) {
                System.out.println("User " + nextUser + " fulfilled from waiting list.");
            } else {
                stock.incrementAndGet();
            }
        }

        return "Restock complete. Current stock: " + stock.get();
    }
}