package project11_08_practise;

public class PracticeProblem1 {
    public static void main(String[] args) {
        String str1 = "Java Programming";
        String str2 = new String("Java Programming");
        char[] arr = {'J','a','v','a',' ','P','r','o','g','r','a','m','m','i','n','g'};
        String str3 = new String(arr);

        System.out.println("== comparison:");
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(".equals() comparison:");
        System.out.println(str1.equals(str2));
        System.out.println(str1.equals(str3));

        String quote = "Programming Quote:\n\t\"Code is poetry\" - Unknown\n\tPath: C:\\Java\\Projects";
        System.out.println(quote);
    }
}
