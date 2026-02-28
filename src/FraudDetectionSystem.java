import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FraudDetectionSystem {
    private final List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        FraudDetectionSystem system = new FraudDetectionSystem();
        long now = System.currentTimeMillis();
        system.addTransaction(new Transaction(1, 500, "StoreA", now));
        system.addTransaction(new Transaction(2, 300, "StoreB", now + 1000));
        system.addTransaction(new Transaction(3, 200, "StoreC", now + 2000));
        system.addTransaction(new Transaction(4, 500, "StoreA", now + 3000));

        system.findTwoSum(500);
        system.findTwoSumWithWindow(500, 3600000);
        system.detectDuplicates();
        system.findKSum(3, 1000);
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void findTwoSum(int target) {
        Map<Integer, Transaction> map = new HashMap<>();
        for (Transaction t : transactions) {
            int complement = target - t.amount;

            if (map.containsKey(complement)) {
                System.out.println("Two-Sum Found: " +
                        map.get(complement).id + " + " + t.id);
                return;
            }

            map.put(t.amount, t);
        }

        System.out.println("No Two-Sum found.");
    }

    public void findTwoSumWithWindow(int target, long windowMillis) {
        Map<Integer, List<Transaction>> map = new HashMap<>();
        for (Transaction t : transactions) {
            int complement = target - t.amount;
            if (map.containsKey(complement)) {
                for (Transaction other : map.get(complement)) {
                    if (Math.abs(t.timestamp - other.timestamp)
                            <= windowMillis) {

                        System.out.println("Time-window Two-Sum: "
                                + other.id + " + " + t.id);
                        return;
                    }
                }
            }

            map.computeIfAbsent(t.amount,
                    k -> new ArrayList<>()).add(t);
        }

        System.out.println("No time-window Two-Sum found.");
    }

    public void detectDuplicates() {
        Map<String, List<Transaction>> map = new HashMap<>();
        for (Transaction t : transactions) {
            String key = t.amount + "_" + t.merchant;
            map.computeIfAbsent(key,
                    k -> new ArrayList<>()).add(t);
        }

        for (Map.Entry<String, List<Transaction>> entry : map.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println("Duplicate detected for key: "
                        + entry.getKey());
            }
        }
    }

    public void findKSum(int k, int target) {
        backtrack(0, k, target, new ArrayList<>());
    }

    private void backtrack(int start, int k,
                           int target, List<Transaction> path) {
        if (k == 0 && target == 0) {
            System.out.print("K-Sum Found: ");
            for (Transaction t : path) {
                System.out.print(t.id + " ");
            }
            System.out.println();
            return;
        }

        if (k <= 0 || target < 0) return;

        for (int i = start; i < transactions.size(); i++) {
            path.add(transactions.get(i));
            backtrack(i + 1,
                    k - 1,
                    target - transactions.get(i).amount,
                    path);
            path.remove(path.size() - 1);
        }
    }

    static class Transaction {
        int id;
        int amount;
        String merchant;
        long timestamp;

        Transaction(int id, int amount, String merchant, long timestamp) {
            this.id = id;
            this.amount = amount;
            this.merchant = merchant;
            this.timestamp = timestamp;
        }
    }
}