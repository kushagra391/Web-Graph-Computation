
public class MatrixDemo {
	
	public static void main(String[] args) {
		
		
		double M[][] = {
				{0, 		1.0/3.0, 	1.0/3.0, 	0, 			0, 			1.0/3.0},
				{0, 		0.0, 		1.0/4.0, 	1.0/4.0, 	1.0/4.0, 	1.0/4.0},
				{0, 		0, 			0, 			1.0/2.0, 	1.0/2.0, 	0},
				{1.0/4.0, 	0, 			1.0/4.0, 	0, 			1.0/4.0, 	1.0/4.0},
				{1.0/1.0, 	0, 			0, 			0, 			0, 			0},
				{1.0/3.0, 	1.0/3.0, 	0, 			0, 			1.0/3.0, 	0}
		};
		
//		printMatrix(M);
		M = postProcess(M);
		
		System.out.println();
		System.out.println();
//		printMatrix(M);
//		System.out.println();
		
		
		
		double P0[] = {1.0/6.0, 1.0/6.0, 1.0/6.0, 1.0/6.0, 1.0/6.0, 1.0/6.0};
		
		double N1[][] = multiply(M, M);
		double N2[][] = multiply(N1, N1);
		double N3[][] = multiply(N2, N2);
		double N4[][] = multiply(N3, N3);
		double N5[][] = multiply(N4, N4);
		
		
		double P[] = new double[6];
		double temp[] = new double[6];
		
		P = multiplyVectorMatrix(P0, N5);
		printVector(P);
		int count = 0;
//		for (int i=0; i<5; i++) {
		while(true) {
			
			
			M = multiply(M, M);
			P = multiplyVectorMatrix(P0, M);
			
			printVector(P);
			printVector(temp);
			if (isEqual(P, temp)) break;
			
			
			temp = cloneVector(P);
			count++;
			
		}
		
		P0 = cloneVector(P0);
//		while (true) {
//			
//			double P1[] = multiplyVectorMatrix(P0, M);
//			printVector(P1);
//			if (isEqual(P1, P0)) break;
//			P0 = cloneVector(P1);
//			
//			count++;
//			
//		}
		
		System.out.println(count);
		
		
//		printVector(P);
//		printMatrix(M);
//		System.out.println("########################");
//		printMatrix(N5);
//		System.out.println("########################");
//		printVector(temp);
//		System.out.println("########################");
		
		
		
		
		
	}
	
	private static double[][] postProcess(double[][] A) {
		// TODO Auto-generated method stub
		
		
		int m = A.length;
		int n = A[0].length;
		
		double d = 0.85;
		double k = d/m;
		
		for (int i=0; i<n; i++) {
			
			if (hasInlinks(A[i])) {
				
				for (int j=0; j<m; j++) {
					if (A[i][j] != 0) {
						A[i][j] = A[i][j] + k;
					}
				}
				
				
			}
			
			if (!hasInlinks(A[i])) {
				
				for (int j=0; j<m; j++) {
					if (A[i][j] == -1) {
						A[i][j] = A[i][j] + k;
					}
					
				}
			}
			
			
		}
		
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				System.out.print(A[i][j] + "\t\t ");
			}
			System.out.println();
		}
		
		
		return A;
	}

	private static boolean hasInlinks(double[] ds) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean isEqual(double[] X, double[] Y) {
		// TODO Auto-generated method stub

		int N = X.length;

		for (int i = 0; i < N; i++) {
			if (X[i] - Y[i] > 0.0000000000000001) {
//			if (Double.compare(X[i], Y[i]) != 0) {
				return false;
			}
		}

		return true;
	}
	
	
	
	private static double[] cloneVector(double[] M) {
		// TODO Auto-generated method stub
		
		int n = M.length;
		double R[] = new double[n];
		
		for (int i=0; i<n; i++) {
			R[i] = M[i];
		}

		return R;
	}
	
	
	private static double[][] cloneMatrix(double[][] src) {
		// TODO Auto-generated method stub
		int length = src.length;
		double[][] target = new double[length][src[0].length];
		for (int i = 0; i < length; i++) {
			System.arraycopy(src[i], 0, target[i], 0, src[i].length);
		}
		return target;
	}
	
	
	private static void printVector(double[] V) {
		// TODO Auto-generated method stub

		int n = V.length;
		for (int i = 0; i < n; i++) {
			System.out.print(V[i] + " ");
		}

		System.out.println();

	}
	
	

	private static double[] multiplyVectorMatrix(double[] x, double[][] A) {
		int m = A.length;
		int n = A[0].length;
		if (x.length != m)
			throw new RuntimeException("Illegal matrix dimensions.");
		double[] y = new double[n];
		for (int j = 0; j < n; j++)
			for (int i = 0; i < m; i++)
				y[j] += (A[i][j] * x[i]);
		return y;
	}

	private static void printMatrix(double[][] A) {
		// TODO Auto-generated method stub
		
		int m = A.length;
		int n = A[0].length;
		
		System.out.println(m + ", " + n);
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				System.out.print(A[i][j] + "\t\t ");
			}
			System.out.println();
		}
		
		
		
		
	}
	
	public static double[][] multiply(double[][] A, double[][] B) {
		int mA = A.length;
		int nA = A[0].length;
		int mB = B.length;
		int nB = B[0].length;
		if (nA != mB)
			throw new RuntimeException("Illegal matrix dimensions.");
		double[][] C = new double[mA][nB];
		for (int i = 0; i < mA; i++)
			for (int j = 0; j < nB; j++)
				for (int k = 0; k < nA; k++)
					C[i][j] += (A[i][k] * B[k][j]);
		return C;
	}
	
	
	
	
}
