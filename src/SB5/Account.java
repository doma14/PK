package SB5;

public class Account {

	/**
	 * Speichert den Eurobetrag.
	 */
	private int euro;
	/**
	 * Speichert den Centbetrag.
	 */
	private int cent;
	/**
	 * Darf nur positiv sein.
	 */
	private int dispo;

	public Account(int euro, int cent) {

		if (cent < 0) {
			cent *= -1;
		}

		if (cent > 99) {
			while (cent >= 100) {
				cent -= 100;
				if (euro < 0) {
					euro--;
				} else {
					euro++;
				}
			}
		}

		int erg = Math.addExact(this.euro, euro);

		this.euro = erg;
		this.cent = cent;

		this.dispo = 500;
	}

	public Account(int euro, int cent, int dispo) {

		if (cent < 0) {
			cent *= -1;
		}

		if (cent > 99) {
			while (cent >= 100) {
				cent -= 100;
				if (euro < 0) {
					euro--;
				} else {
					euro++;
				}
			}
		}

		int erg = Math.addExact(this.euro, euro);

		this.euro = erg;
		this.cent = cent;
		this.dispo = (dispo >= 0) ? dispo : (dispo * -1);
	}

	public int getEuro() {
		return euro;
	}

	public void setEuro(int euro) {
		this.euro = euro;
	}

	public int getCent() {
		return cent;
	}

	public void setCent(int cent) {
		if (cent < 100 && cent >= 0) {
			this.cent = cent;
		} else {
			this.cent = 0;
		}
	}

	public int getDispo() {
		return dispo;
	}

	public void setDispo(int dispo) {
		this.dispo = dispo;
	}

	public boolean draw(int euro, int cent) {
		
		boolean abheben = false;
		long guthabenInCent = 0;
		long abhebenInCent = 0;
		long dispoInCent = 0;
		
		
		if (euro >= 0 && cent >= 0) {

			abhebenInCent = (euro * 100L) + (cent);
			
			if (this.euro >= 0) {
				guthabenInCent = (this.euro * 100L) + (this.cent);
//				System.out.println("guthaben: " + guthabenInCent);
//				System.out.println("abheben: " + abhebenInCent);
				if (abhebenInCent <= guthabenInCent) {
					long rest = guthabenInCent - abhebenInCent;
//					System.out.println(rest);
					this.cent = (int) rest % 100;
//					System.out.println(this.cent);
					this.euro = (int) rest / 100;
//					System.out.println(this.euro);
					abheben = true;
				} else {
					dispoInCent = -1 * this.dispo * 100L;
					long nochAbzuhebenderBetrag = abhebenInCent - guthabenInCent;
					long ergebnis = 0 - nochAbzuhebenderBetrag;
					if (ergebnis >= Integer.MIN_VALUE && ergebnis >= dispoInCent) {
						this.cent = (int) ergebnis % 100;
						this.euro = (int) ergebnis / 100;
						if (this.cent < 0) {
							this.cent *= -1;
						}
						abheben = true;
					} else {
						abheben = false;
					}
					
				}
			} else {
//				System.out.println("in else: this.euro < 0");
				guthabenInCent = (this.euro * 100L) - this.cent;
				dispoInCent = -1 * this.dispo * 100L;
//				System.out.println(guthabenInCent);
//				System.out.println(abhebenInCent);
//				System.out.println("------");
				long ergebnis = guthabenInCent - abhebenInCent;
				if (ergebnis >= Integer.MIN_VALUE && ergebnis >= dispoInCent) {
					this.cent = (int) ergebnis % 100;
					this.euro = (int) ergebnis / 100;
					if (this.cent < 0) {
						this.cent *= -1;
					}
					abheben = true;
				} else {
					abheben = false;
				}
			}
			
		}
		return abheben;
	}

	public boolean deposit(int euro, int cent) {

		boolean einzahlen = false;
		long guthabenInCent = 0;
		long einzahlenInCent = 0;

		if (euro >= 0 && cent >= 0) {
			// Cent zusammenrechnen und ueber diese Summe die weiteren Euros
			// berechnen
			
			einzahlenInCent = (euro * 100L) + cent;
			System.out.println("einzahlenincent: " + einzahlenInCent);
			
			if (this.euro < 0) {
				guthabenInCent = (this.euro * 100L) - this.cent;
			} else {
				guthabenInCent = (this.euro * 100L) + this.cent;
			}
			System.out.println("guthaben: " + guthabenInCent);
			
			long ergebnis = guthabenInCent + einzahlenInCent;
//			System.out.println("ergebnis: " + ergebnis);
//			System.out.println("ergebnis / 100: " + ergebnis / 100);
//			System.out.println("ergebnis % 100: " + ergebnis % 100);
			if ((ergebnis / 100) <= Integer.MAX_VALUE) {
				this.cent = (int) (ergebnis % 100);
				this.euro = (int) (ergebnis / 100);
				if (this.cent < 0) {
					this.cent *= -1;
				}
				einzahlen = true;
			}
			
		}
			
			
			
//			cent += this.cent;
//			if (cent >= 100) {
//				while (cent >= 100) {
//					cent -= 100;
//					try {
//						int erg = Math.addExact(euro, 1);
//						euro = erg;
//					} catch (ArithmeticException e) {
//						return false;
//					}
//				}
//			}
//			// Versuche vorhandene Euros und einzuzahlende Euros zu addieren
//			try {
//				int erg = Math.addExact(this.euro, euro);
//				this.euro = erg;
//				this.cent = cent;
//				einzahlen = true;
//			} catch (ArithmeticException e) {
//				einzahlen = false;
//			}
//		}

		return einzahlen;
	}

	private boolean istAbhebenMoeglich(int euroBetrag, int centBetrag) {
		boolean moeglich = false;
		long guthabenInCent = 0;
		if (euroBetrag >= 0 && centBetrag >= 0) {
			if (this.euro < 0) {
				guthabenInCent = this.euro * 100 + (this.cent * (-1));
			} else {
				guthabenInCent = this.euro * 100 + this.cent;
			}
			long disporahmen = this.dispo * 100;
			long abzuhebenderBetrag = euroBetrag * 100 + centBetrag;

			if (abzuhebenderBetrag <= guthabenInCent + disporahmen) {
				moeglich = true;
			}
		}

		return moeglich;
	}
}
