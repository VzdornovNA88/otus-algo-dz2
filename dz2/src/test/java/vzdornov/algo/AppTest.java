package vzdornov.algo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.math.BigInteger;
import java.util.Collection;

public class AppTest 
{
    static BigInteger[] tickets = {
            new BigInteger("10"),
            new BigInteger("670"),
            new BigInteger("55252"),
            new BigInteger("4816030"),
            new BigInteger("432457640"),
            new BigInteger("39581170420"),
            new BigInteger("3671331273480"),
            new BigInteger("343900019857310"),
            new BigInteger("32458256583753952"),
            new BigInteger("3081918923741896840")
    };

    @DisplayName("Count lucky tickets by dynamic programming method test")
	@RepeatedTest(value = 10)
    void testCountLuckyDinProgMethod(RepetitionInfo repetitionInfo) {
        int i = repetitionInfo.getCurrentRepetition();
        Assertions.assertEquals(tickets[i-1],App.countLuckyDinProgMethod(i));
    }

    @DisplayName("Count lucky tickets by bruteforce method test")
	@RepeatedTest(value = 4)
    void testCountLuckyBruteForceMethod(RepetitionInfo repetitionInfo) {
        int i = repetitionInfo.getCurrentRepetition();
        Assertions.assertEquals(tickets[i-1],BigInteger.valueOf(App.countLuckyBruteForceMethod(i)));
    }

    @DisplayName("Count lucky tickets by dynamic programming with type \"long\" method test")
	@RepeatedTest(value = 10)
    void testCountLuckyDinProgLongMethod(RepetitionInfo repetitionInfo) {
        int i = repetitionInfo.getCurrentRepetition();
        Assertions.assertEquals(tickets[i-1],BigInteger.valueOf(App.countLuckyDinProgLongMethod(i)));
    }
}
