public class Util {
    public static void print(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static void print(int[] arr, String sep){
        for(int i : arr){
            System.out.print(i + sep);
        }
        System.out.println();
    }
    public static void print(String title, int[] arr){
        System.out.print(title + " ");
        print(arr);
    }
    public static void print(String title, int[] arr, String sep){
        System.out.print(title + " ");
        print(arr, sep);
    }
}
