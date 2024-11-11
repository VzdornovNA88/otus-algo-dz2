package vzdornov.algo;

import java.math.BigInteger;
import java.util.Date;

public class App 
{
    public static BigInteger countLuckyDinProgMethod( int N ) {
        
        BigInteger sN = BigInteger.ZERO;
        
        if( 1 != N ) {

            final int countGroup = 9*N+1;
            BigInteger[][] s1Arr = new BigInteger[N][countGroup];

            for( byte q = 0; q <= 9; q++ ) {
                s1Arr[0][q] = BigInteger.ONE;
            }
            for( int i = 1; i < N; i++ ) {
                sN = BigInteger.ZERO;
                for( byte j = 0; j <= 9; j++ ) {
                    for( int k = j; k < countGroup; k++ ) {
                        if( s1Arr[i][k] == null ) s1Arr[i][k] = BigInteger.ZERO;
                        if( s1Arr[i-1][k-j] == null ) s1Arr[i-1][k-j] = BigInteger.ZERO;
                        s1Arr[i][k] = s1Arr[i][k].add(s1Arr[i-1][k-j]);
                        if( k == j || j==9 ) {
                            sN = sN.add(s1Arr[i][k].pow(2));
                        }
                    }
                }
            }
        }
        else {
            sN = new BigInteger("10");
        }

        return sN;
    }

    public static long countLuckyDinProgLongMethod( int N ) {
        
        long sN = 0;
        
        if( 1 != N ) {

            final int countGroup = 9*N+1;
            long[][] s1Arr = new long[N][countGroup];

            for( byte q = 0; q <= 9; q++ ) {
                s1Arr[0][q] = 1;
            }
            for( int i = 1; i < N; i++ ) {
                sN = 0;
                for( byte j = 0; j <= 9; j++ ) {
                    for( int k = j; k < countGroup; k++ ) {
                        s1Arr[i][k] += s1Arr[i-1][k-j];
                        if( k == j || j==9 ) {
                            sN += Math.pow(s1Arr[i][k],2);
                        }
                    }
                }
            }
        }
        else {
            sN = 10;
        }

        return sN;
    }

    public static long sum(long n) {
        long s = 0;
        while (n > 0) {
            s += n % 10;
            n /= 10;
        }
        return s;
    }

    public static long countLuckyBruteForceMethod(int N) {
        long maxSize = (long) Math.pow(10, N);

        long sN = 0;
        for (long i = 0; i < maxSize; i++) {
            for (long j = 0; j < maxSize; j++)
                if (sum(i) == sum(j)) {
                    sN++;
                }
        }
        return sN;
    }

    public static void main( String[] args )
    {
        Date d = new Date();
        
        System.out.println( d.toString() + " : " + "BEGIN" );

        System.out.println( "N = 3" );
        System.out.println( d.toString() + " : " + countLuckyBruteForceMethod((short)3) );

        System.out.println( "N = 10" );
        System.out.println( d.toString() + " : " + countLuckyDinProgMethod(10) );

        System.out.println( "N = 300" );
        System.out.println( d.toString() + " : " + countLuckyDinProgMethod(300) );

        System.out.println( d.toString() + " : " + "END" );
    }
}
