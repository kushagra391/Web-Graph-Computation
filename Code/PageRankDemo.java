import java.util.Arrays;


public class PageRankDemo {
	
	
	public static void main(String[] args) {
		
		/*
		 * 
		 * Graph:
		 * A -> B, C, F 	1/3
		 * B -> C, D, E, F	1/4
		 * C -> D, E		1/2
		 * D -> A, C, E, F	1/4
		 * E -> A			1/1
		 * F -> A, B, E		1/3
		 * 
		 * 
		 */
		
		double M[][] = {
				{0, 		1.0/3.0, 	1.0/3.0, 	0, 			0, 			1.0/3.0},
				{0, 		0.0, 		1.0/4.0, 	1.0/4.0, 	1.0/4.0, 	1.0/4.0},
				{0, 		0, 			0, 			1.0/2.0, 	1.0/2.0, 	0},
				{1.0/4.0, 	0, 			1.0/4.0, 	0, 			1.0/4.0, 	1.0/4.0},
				{1.0/1.0, 	0, 			0, 			0, 			0, 			0},
				{1.0/3.0, 	1.0/3.0, 	0, 			0, 			1.0/3.0, 	0}
		};
		
		
		
		
		double Pi[] = {1.0/6.0, 1.0/6.0, 1.0/6.0, 1.0/6.0, 1.0/6.0, 1.0/6.0};
		
		
		System.out.println(M.length);
		System.out.println(Pi.length);
		System.out.println(Pi[2]);
		
		double Pi_prev[] = new double[Pi.length];
		double Pi_curr[] = new double[Pi.length];
		
//		Pi_prev = cloneVector(Pi);
		int i=0;
		
//		while (true) {
//			
//			Pi_curr = multiplyVector(Pi_prev, M);
//			if (isEqual(Pi_prev, Pi_curr)) {
//				break;
//			}
//			
//			Pi_prev = Pi_curr;
//			
//			printVector(Pi_curr);
//			i++;
//		}
//		System.out.println(i);
		
		// Faster method
		double N[][] = cloneMatrix(M);
		while (true) {
			// TODO
			N = multiply(N, N);
			Pi_curr = multiplyVector(Pi, N);
			
			if (isEqual(Pi_prev, Pi_curr)) break;
			Pi_prev = cloneVector(Pi_curr);
			
			printVector(Pi_curr);
			i++;
		}
		System.out.println(i);
		
		
		
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

	private static boolean isEqual(double[] X, double[] Y) {
		// TODO Auto-generated method stub

		int N = X.length;

		for (int i = 0; i < N; i++) {
			if (X[i] != Y[i]) {
				return false;
			}
		}

		return true;
	}

	private static double square(double[] M) {
		// TODO Auto-generated method stub

		int N = M.length;

		double result = 0;
		for (int i = 0; i < N; i++) {
			result = result + (M[i] * M[i]);
		}

		return Math.sqrt(result);
	}

	private static void printVector(double[] V) {
		// TODO Auto-generated method stub

		int n = V.length;
		for (int i = 0; i < n; i++) {
			System.out.print(V[i] + " ");
		}

		System.out.println();

	}

	public static double[] multiplyVector(double[] x, double[][] A) {
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
