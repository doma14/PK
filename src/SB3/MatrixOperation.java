package SB3;

public class MatrixOperation {

	public static int[][] add(int[][] matrixA, int[][] matrixB) {
		int[][] ergebnis = null;
		
		if (matrixA != null && matrixB != null) {
			if (matrixA.length != 0 && matrixB.length != 0) {
				if (matrixA[0].length != 0 && matrixB[0].length != 0) {
					if (sindMatrizenGleichGross(matrixA, matrixB)) {
						ergebnis = new int[matrixA.length][matrixA[0].length];
						for (int i = 0; i < matrixA.length; i++) {
							for (int j = 0; j < matrixA[0].length; j++) {
								ergebnis[i][j] = matrixA[i][j] + matrixB[i][j];
							}
						}
					}
				}
			}
		}
		
		return ergebnis;
	}
	
	private static boolean sindMatrizenGleichGross(int[][] matrixA, int[][] matrixB) {
		boolean gleich = true;
		int reihenelemente = 0;
		
		if (matrixA.length == matrixB.length) {
			for (int i = 0; i < matrixA.length; i++) {
				for (int k = 0; k < matrixB.length; k++) { //Arraylaengen sind identisch, Variablen unterschiedlich zur besseren Unterscheidung
					reihenelemente = matrixA[0].length;
					if (!(matrixA[i].length == matrixB[k].length)) {
						return false;
					}
				}
				if (reihenelemente != matrixA[i].length) {
					return false;
				}
			}
		}
		
		return gleich;
	}

	public static int[][] multiply(int[][] matrixA, int[][] matrixB) {
		int[][] ergebnis = null;
		int zwischenergebnis = 0;
		
		if (matrixA != null && matrixB != null) {
			if (matrixA.length != 0 && matrixB.length != 0) {
				if (sindMatrizenValide(matrixA, matrixB)) {
					ergebnis = new int[matrixA.length][matrixB[0].length];
					for (int i = 0; i < matrixA.length; i++) {
						int k = 0;
						for (k = 0; k < matrixB[0].length; k++) {
							for (int l = 0; l < matrixB.length; l++) {
								zwischenergebnis += matrixA[i][l] * matrixB[l][k];
							}
							ergebnis[i][k] = zwischenergebnis;
							zwischenergebnis = 0;
						}
						
					}
				}
			}
		}
		
		return ergebnis;
	}
	
	private static boolean sindMatrizenValide(int[][] matrixA, int[][] matrixB) {
		boolean valide = true;
		
		for (int i = 0; i < matrixA.length; i++) {
			if (!(matrixA[i].length == matrixB.length)) {
				return false;
			}
		}
		
		return valide;
	}

}
