package project_23_08_HW;

import java.util.*;

public class CSVAnalyzer {
    public static List<String[]> parseCSV(String text) {
        List<String[]> rows = new ArrayList<>();
        int start = 0;
        List<String> row = new ArrayList<>();
        for (int i = 0; i <= text.length(); i++) {
            if (i == text.length() || text.charAt(i) == ',' || text.charAt(i) == '\n') {
                row.add(text.substring(start, i).trim());
                start = i + 1;
                if (i == text.length() || text.charAt(i) == '\n') {
                    rows.add(row.toArray(new String[0]));
                    row.clear();
                }
            }
        }
        return rows;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CSV data:");
        String data = sc.useDelimiter("\\Z").next();

        List<String[]> rows = parseCSV(data);

        for (String[] r : rows) {
            for (String f : r) System.out.printf("%-15s", f);
            System.out.println();
        }
        System.out.println("Records: " + rows.size());
    }
}
