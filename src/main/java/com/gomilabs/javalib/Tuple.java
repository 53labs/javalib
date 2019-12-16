package com.gomilabs.javalib;

import java.util.function.Function;

public interface Tuple<A, B> {

    static <A, B> Tuple<A, B> tuple(final A fst, final B snd) {
        return TupleImpl.of(fst, snd);
    }

    A fst();

    B snd();

    static <A, B, C> Function<A, Function<B, C>> curry(Function<Tuple<A, B>, C> func) {
        return a -> b -> func.apply(tuple(a, b));
    }

    static <A, B, C> Function<Tuple<A, B>, C> uncurry(Function<A, Function<B, C>> func) {
        return tuple -> func.apply(tuple.fst()).apply(tuple.snd());
    }

    static <A, B> Tuple<B, A> swap(final Tuple<A, B> t) {
        return tuple(t.snd(), t.fst());
    }

}

class TupleImpl<A, B> implements Tuple<A, B> {

    private final A fst;
    private final B snd;

    private TupleImpl(final A fst, final B snd) {
        this.fst = fst;
        this.snd = snd;
    }

    static <A, B> Tuple<A, B> of(final A fst, final B snd) {
        return new TupleImpl<>(fst, snd);
    }

    @Override
    public A fst() {
        return fst;
    }

    @Override
    public B snd() {
        return snd;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof TupleImpl)) return false;
        final TupleImpl tuple = (TupleImpl) o;
        if (fst != null ? !fst.equals(tuple.fst) : tuple.fst != null) return false;
        if (snd != null ? !snd.equals(tuple.snd) : tuple.snd != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = fst != null ? fst.hashCode() : 0;
        result = 31 * result + (snd != null ? snd.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TupleImpl{" +
                "fst=" + fst +
                ", snd=" + snd +
                '}';
    }

}