package main.utils;

@FunctionalInterface
public interface Function3<A, B, C> {
    public void apply(A a, B b, C c);
}
