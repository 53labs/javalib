package com.gomilabs.javalib;

import com.gomilabs.javalib.Tuple;
import org.junit.Test;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import static com.gomilabs.javalib.Tuple.curry;
import static com.gomilabs.javalib.Tuple.swap;
import static com.gomilabs.javalib.Tuple.uncurry;

public class TupleTest {

    @Test
    public void testCurry() {
        final Function<Tuple<Double, String>, Double> function = Tuple::fst;
        assertThat(curry(function).apply(0d).apply("string"), is(0d));
    }

    @Test
    public void testUncurry() {
        final Function<Double, Function<String, Double>> function = a -> b -> a;
        assertThat(uncurry(function).apply(Tuple.tuple(0d, "string")), is(0d));
    }

    @Test
    public void testSwap() {
        assertThat(swap(Tuple.tuple(0d, "string")), is(Tuple.tuple("string", 0d)));
    }

}