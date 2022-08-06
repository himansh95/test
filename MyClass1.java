/*

3
*******
 * * *
  * *
4
***********
 *   *   *
  * * * *
   *   *  
5
***************
 *     *     *
  *   * *   *
   * *   * *
    *     *
6
*******************
 *       *       *
  *     * *     *
   *   *   *   *
    * *     * *
     *       *

*/



public class MyClass1 {
    public static void main(String args[]) {
      int n = 6;
      int n1 = 2 * (n - 3) - 1;
      int n2 = 1;
      for (int i = 0; i < n; i++) {
          if (i == 0) {
              for (int j = 1; j <= (4 * (n - 2) + 3); j++) {
                  System.out.print("*");
              }
          } else if (i == 1) {
              for (int j = 1; j <= i; j++) {
                  System.out.print(" ");
              }
              System.out.print("*");
              for (int j = 1; j <= (2 * (n - 2) - 1); j++) {
                  System.out.print(" ");
              }
              System.out.print("*");
              for (int j = 1; j <= (2 * (n - 2) - 1); j++) {
                  System.out.print(" ");
              }
              System.out.print("*");
          } else if (i == n - 1) {
              for (int j = 1; j <= i; j++) {
                  System.out.print(" ");
              }
              System.out.print("*");
              for (int j = 1; j <= (2 * (n - 2) - 1); j++) {
                  System.out.print(" ");
              }
              System.out.print("*");
          } else {
              for (int j = 1; j <= i; j++) {
                  System.out.print(" ");
              }
              System.out.print("*");
              for (int j = 1; j <= n1; j++) {
                  System.out.print(" ");
              }
              System.out.print("*");
              for (int j = 1; j <= n2; j++) {
                  System.out.print(" ");
              }
              System.out.print("*");
              for (int j = 1; j <= n1; j++) {
                  System.out.print(" ");
              }
              System.out.print("*");
              n1 -= 2;
              n2 += 2;
          }
          System.out.println();
      }
    }
}
