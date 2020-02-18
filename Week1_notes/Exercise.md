# **exe 1b**
```java
public class YourClassNameHere {
    public static void main(String[] args) {
      drawTriangle(10);
    }
    public static void drawTriangle(int x) {
       int col = 1;
       int row = 1;
       int SIZE = x;
       while (row <= SIZE){
          col = 1;
          while (col <= row){
           System.out.print('*');
           col = col + 1;
          } 
          System.out.println();
          row = row + 1;
        }


    }
}
```
# **exe 2**
```java
public class ClassNameHere {
   public static int max(int[] m) {
     int curmax = 0;
     for(int x = 0; x < m.length; x++){
       if (m[x] >= curmax) curmax = m[x];
       }
       return curmax;
   }
   public static void main(String[] args) {
      int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6}; 
      int m;
      m = max(numbers);
   }
}
```
# **exe 3**
```java
public class ClassNameHere {
   public static int max(int[] m) {
     int curmax = 0;
     for(int x = 0; x < m.length; x++){
       if (m[x] >= curmax) curmax = m[x];
       }
       return curmax;
   }
   public static void main(String[] args) {
      int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6}; 
      int m;
      m = max(numbers);
   }
}
```
# **exe 4**
```java
public class BreakContinue {
  public static void windowPosSum(int[] a, int n) {
    int temp;
    for(int i = 0; i < a.length; i += 1){
      temp = a[i];
      if(temp < 0) continue;
      for(int j = i + 1; j <= i + n; j += 1){
        if(j > a.length - 1) break;        
        a[i] += a[j];}
      }
  }

  public static void main(String[] args) {
    int[] a = {1, 2, -3, 4, 5, 4};
    int n = 3;
    windowPosSum(a, n);
    // Should print 4, 8, -3, 13, 9, 4
    System.out.println(java.util.Arrays.toString(a));
  }
}
```
