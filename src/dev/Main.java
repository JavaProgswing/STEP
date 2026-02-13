package dev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Transaction {
    int id;
    int amount;
    long time;

    Transaction(int id, int amount, long time) {
        this.id = id;
        this.amount = amount;
        this.time = time;
    }
}

public class Main {

    public static List<int[]> twoSum(List<Transaction> tx, int target) {
        Map<Integer, Transaction> map = new HashMap<>();
        List<int[]> res = new ArrayList<>();

        for (Transaction t : tx) {
            int comp = target - t.amount;
            if (map.containsKey(comp)) {
                res.add(new int[]{map.get(comp).id, t.id});
            }
            map.put(t.amount, t);
        }
        return res;
    }

    public static void main(String[] args) {
        List<Transaction> tx = List.of(new Transaction(1, 500, 1), new Transaction(2, 300, 2), new Transaction(3, 200, 3));

        var pairs = twoSum(tx, 500);
        for (int[] p : pairs)
            System.out.println(p[0] + "," + p[1]);
    }
}
