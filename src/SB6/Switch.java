package SB6;

public class Switch {

    public static void main(String[] args) {
        int a = 5;
        int b = 7;

        a = a - b;
        b = b + a;
        a = b -a;

        System.out.println(a);
        System.out.println(b);
    }
}
