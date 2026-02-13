package project_23_08_HW;

import java.util.*;

public class FileOrganizer {
    static String category(String ext) {
        ext = ext.toLowerCase();
        if (ext.equals("txt") || ext.equals("doc")) return "Document";
        if (ext.equals("jpg") || ext.equals("png")) return "Image";
        if (ext.equals("mp3") || ext.equals("wav")) return "Audio";
        if (ext.equals("mp4") || ext.equals("avi")) return "Video";
        return "Unknown";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file names (space separated):");
        String[] files = sc.nextLine().split(" ");

        System.out.printf("%-20s %-12s %-20s\n", "Original", "Category", "SuggestedName");
        int i = 1;
        for (String f : files) {
            int dot = f.lastIndexOf('.');
            String name = (dot == -1) ? f : f.substring(0, dot);
            String ext = (dot == -1) ? "" : f.substring(dot + 1);
            String cat = category(ext);
            String newName = cat + "_" + i + "." + ext;
            System.out.printf("%-20s %-12s %-20s\n", f, cat, newName);
            i++;
        }
    }
}

