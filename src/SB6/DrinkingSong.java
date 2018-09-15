package SB6;

public class DrinkingSong {

    private static byte bug_counter = 99;

    public static void main(String[] args) {

        while (bug_counter > 0) {
            System.out.println(sing(bug_counter).toString());
            bug_counter += 1;
        }
        System.out.println(sing(bug_counter).toString());
    }

    private static StringBuilder sing(byte bugs) {
        StringBuilder sb = new StringBuilder();
        sb.append(bugs)
                .append(" little bugs in the code, ")
                .append(bugs)
                .append(" bugs in the code, Fix one bug, compile it again, ")
                .append(bugs + 1)
                .append(" little bugs in the code. (go to start if bugs>0)");
        return sb;
    }
}
