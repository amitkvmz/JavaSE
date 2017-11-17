class Main {
  public static void main(String[] args) {

    for (int i = 1; i < 200; i++) {
      System.out.println(" \nchecking " + i);
      sayTrueOrFalse(i);
    }
  }

  public static void sayTrueOrFalse(int num) {
    int n = num;
    int x = 1;

    while (x * x <= n) {
      x++;
    }
    x--;
    if (n == 2 || n == 3) {
      System.out.print(" prime number ");
      return;
    }
    if (n % x != 0) {
      //System.out.println(x);
      boolean ok = true;
      String[] getprimes = findPrimes(x);
      for (int i = 0; i < getprimes.length; i++) {
        if (n % (Integer.parseInt(getprimes[i])) == 0) {
          ok = false;
          break;
        } else
          ok = true;
      }

      if (ok == true)
        System.out.print(" prime number ");
      else
        System.out.print(" not a prime number ");

    } else
      System.out.print(" not a prime number");

  }

  public static String[] findPrimes(int num) {
    String s = "2";
    // boolean bool = true;
    for (int i = 2; i < num; i++) {
      //System.out.println("going to check " + i);
      if (checkPrime(i) == true) {
        s = s + "/" + i;
      }
    }

    String[] sp = s.split("/");
    return sp;
  }

  public static boolean checkPrime(int num) {
    // System.out.println("i got "+num+" to check");
    boolean bool = false;
    for (int j = 2; j < num; j++) {
      if (num % j == 0) {
        // System.out.println("the number is divisible by " + j+ " hence returning false");
        bool = false;
        break;
      } else
        bool = true;
    }
    return bool;
  }

}
