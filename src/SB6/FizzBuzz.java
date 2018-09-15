package SB6;

public class FizzBuzz {
    private static final int MAX_NUMBER = 100;

    public static void main(String[] args) {
        for (int i = 1; i <= MAX_NUMBER; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                System.out.println("Fizz");
            } else if (i % 3 != 0 && i % 5 == 0) {
                System.out.println("Buzz");
            } else if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("SB6.FizzBuzz");
            } else {
                System.out.println(i);
            }
        }
    }
}
