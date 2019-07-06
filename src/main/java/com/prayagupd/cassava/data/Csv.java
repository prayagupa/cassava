package com.prayagupd.cassava.data;

import java.util.List;

public class Csv {

    public static class Tuple<A, B> {
        private A a;
        private B b;

        public Tuple(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }

    //FIXME encode triple and more
    public static <A, B> String encode(List<Tuple<A, B>> values) {
        return values.stream().collect(
                () -> new StringBuffer(),
                (acc, value) -> acc.append(value.a + "," + value.b + "\n"),
                (a, b) -> {
                }
        ).toString();
    }

    //FIXME
    public static <A> A decode(String csv) {
        return null;
    }
}
