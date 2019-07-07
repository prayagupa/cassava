package com.prayagupd.cassava.data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static <A> String encode(A record) {
        var commaSeparated = Arrays.stream(record.getClass().getDeclaredFields())
                .map(a -> {
                    try {
                        return a.get(record).toString();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.joining(","));
        return commaSeparated;
    }

    //FIXME
    public static <A> A decode(String csv) {
        return null;
    }
}
