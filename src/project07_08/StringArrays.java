package project07_08;

public class StringArrays {
// and returns the longest name
    public static String findLongestName(String[] names) {
        String longestName = "";
        for (String name : names) {
            if (name.length() > longestName.length()) {
                longestName = name;
            }
        }
        return longestName;
    }

// start with a given letter (case-insensitive)
    public static int countNamesStartingWith(String[] names, char letter) {
        int count = 0;
        for (String name : names) {
            if (name.toLowerCase().charAt(0) == Character.toLowerCase(letter)) {
                count += 1;
            }
        }
        return count;
    }

// Assume names are given as "First Last"
    public static String[] formatNames(String[] names) {
        String[] formattedNames = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            String[] parts = names[i].split(" ");
            formattedNames[i] = parts[1] + ", " + parts[0];
        }
        return formattedNames;
    }

    public static void main(String[] args) {
        String[] students = {"John Smith", "Alice Johnson", "Bob Brown", "Carol Davis", "David Wilson"};
        String longest = findLongestName(students);
        System.out.println("Longest name: " + longest);

        char letter = 'A';
        int count = countNamesStartingWith(students, letter);
        System.out.println("Number of names starting with '" + letter + "': " + count);

        String[] formatted = formatNames(students);
        System.out.println("Formatted names:");
        for (String name : formatted) {
            System.out.println(name);
        }
    }
}