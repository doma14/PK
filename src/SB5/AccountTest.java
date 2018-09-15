package SB5;

public class AccountTest {

	public static void main(String[] args) {
//		Account a = new Account(2147483646, 200);
//		Account a = new Account(-1, 0);
		Account a = new Account(-12, 34);
//		System.out.println(a.draw(2, 36));
//		System.out.println(a.getEuro());
//		System.out.println(a.getCent());
//		System.out.println(a.getDispo());
//		System.out.println("--------------------------------- draw: 23€ 10ct");
//		System.out.println(a.draw(23, 10));
//		System.out.println(a.getEuro());
//		System.out.println(a.getCent());
//		System.out.println("--------------------------------- draw: 23€ 12ct");
//		System.out.println(a.draw(23, 12));
//		System.out.println(a.getEuro());
//		System.out.println(a.getCent());
//		System.out.println(a.getDispo());
//		System.out.println("--------------------------------- draw: 1253€ 98ct");
//		System.out.println(a.draw(1253, 98));
//		System.out.println(a.getEuro());
//		System.out.println(a.getCent());
//		System.out.println(a.draw(7, 80));
		System.out.println(a.deposit(2147483647, 700));
//		System.out.println("----------");
		System.out.println(a.getEuro());
		System.out.println(a.getCent());
		System.out.println(Integer.MAX_VALUE);
	}

}
