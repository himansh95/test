public class MyClass {
    public static void handleRow(int i) {
        int j;
        for (j = 1; j <= i; j++) {
            System.out.print(j);
        }
        for (j -= 2; j >= 1; j--) {
            System.out.print(j);
        }
        System.out.println();
    }
    public static void main(String args[]) {
        int n = 5;
        int i;
        for (i = 1; i <= n; i++) {
            handleRow(i);
        }
        for (i -= 2; i >= 1; i--) {
            handleRow(i);
        }
    }
}

/*

1
121
12321
1234321
123454321
1234321
12321
121
1

*/
