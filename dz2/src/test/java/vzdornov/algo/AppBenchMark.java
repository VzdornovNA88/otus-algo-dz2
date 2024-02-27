package vzdornov.algo;

import org.openjdk.jmh.annotations.Benchmark;
// import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

class AppBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.All) // тестируем во всех режимах
    @Warmup(iterations = 10) // число итераций для прогрева нашей функции
    @Measurement(iterations = 100, batchSize = 10)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchCountLuckyDinProgMethod(Blackhole blackhole) {

        blackhole.consume(App.countLuckyDinProgMethod(300));
    }

}