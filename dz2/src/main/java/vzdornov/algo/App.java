package vzdornov.algo;

import java.math.BigInteger;

/**
 * Hello world!
 *
 */
public class App 
{
    // 1.
    // идеи по поводу уменьшения сложности по памяти с N*(9N+1) до 2*(9N+1) 
    // кажется могут привести к ухудшению сложности по времени , 
    // так как придется чистить каждый раз при смене текущей строки матрицы

    // 2.
    // первая реализация была на типе long , 
    // но к сожалению 64 битов знакового лонга не хватает в java , 
    // чтобы решить эту задачу для более высоких N скорее всего переполняется эта операция - sN += Math.pow(s1Arr[i][k],2);
    // (Хоть и известно , что с Java 8 с long можно выполнять операции сложения и вычитания как с беззнаковым,
    // это сопряжено с некоторыми проблемами , такими как использования битовой арифметики на long числах 
    // для постоянно слежения за правильностью знака числа или правильной инициализации изначально с 
    // помощью функций parseUnsignedLong в нужнфх местах алгоритма, а так же кконтролем всех неявных
    // преобразований) - это не делает реализацию не возможной , но решение через BigInteger
    // позволяет получить реализацию путем простой замены типа и операций не контролируя правильность знака.
    // В с++ мы могли бы использовать unsigned long переложив эти проблемы на железо
    // и таким образом решив задачу для N > 8 , что в java приходится решать 
    // используя BigInteger, с другой стороны мы имеем возможность решать эту задачу 
    // для каких-то "жалких" N = 300 за приемлемое время, мы могли бы так же сделать динамическую 
    // диспетчеризацию на функцию в зависимости от N , 
    // которая могла бы выбирать нам функцию расчета основанную на long для меньших N,
    // чтобы избавиться здесь от не оптимальности BigInteger

    // 3.
    // так же из-за реализации алгоритма на основе BigInteger мы
    // теряем очень хорошую оптимизацию в этом случае для нас - 
    // инициализация массива по умолчанию для long нулями JVM
    // , что в случае с BigInteger приходится (если в лоб) 
    // делать в самом алгоритме, я не берусь утверждать , но возможно
    // начальное заполнение матрицы нулями будет эффективнее с точки зрения времени выполнения
    // так как дополнительные branches в алгоритме могут плохо сказаться

    // 4.
    // ну и наивная реализация перебором всех комбинаций через 2 
    // вложенных цикла для соответственно 2 частей билета, 
    // где (10^N) * (10^N) не дает примемлемого времени выполнения уже на N > 4

    public static BigInteger countLuckyDinProgMethod( int N ) {
        
        BigInteger sN = BigInteger.ZERO;
        
        if( 1 != N ) {

            final int countGroup = 9*N+1;
            BigInteger[][] s1Arr = new BigInteger[N][countGroup];

            for( byte q = 0; q <= 9; q++ ) {  // 10
                s1Arr[0][q] = BigInteger.ONE;
            }
            for( int i = 1; i < N; i++ ) {  // N - 1
                sN = BigInteger.ZERO;
                for( byte j = 0; j <= 9; j++ ) {  // 10*(N-1)
                    for( int k = j; k < countGroup; k++ ) {  // (10*(N+1))^2
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

            for( byte q = 0; q <= 9; q++ ) {  // 10
                s1Arr[0][q] = 1;
            }
            for( int i = 1; i < N; i++ ) {  // N - 1
                sN = 0;
                for( byte j = 0; j <= 9; j++ ) {  // 10*(N-1)
                    for( int k = j; k < countGroup; k++ ) {  // (10*(N+1))^2
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
        System.out.println( countLuckyDinProgMethod(300) );

        System.out.println( countLuckyBruteForceMethod((short)3) );
    }
}
