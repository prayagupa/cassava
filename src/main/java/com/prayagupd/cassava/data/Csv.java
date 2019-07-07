package com.prayagupd.cassava.data;

import com.prayagupd.cassava.data.api.CassavaErrors.CassavaExitError;

import java.lang.reflect.InvocationTargetException;
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

    public static <A> String encodeWithHeader(A record) {
        var fields = Arrays.stream(record.getClass().getDeclaredFields());
        var commaSeparatedHeaders = fields
                .map(a -> a.getName())
                .collect(Collectors.joining(","));

        var commaSeparatedValues = encode(record);

        return commaSeparatedHeaders + "\n" + commaSeparatedValues;
    }

    public static <A> String encode(A record) {
        var commaSeparated = Arrays.stream(record.getClass().getDeclaredFields())
                .map(a -> {
                    try {
                        return a.get(record).toString();
                    } catch (IllegalAccessException e) {
                        throw new CassavaExitError("error accessing data class field " + a, e);
                    }
                }).collect(Collectors.joining(","));
        return commaSeparated;
    }

    public static <A> A decode(String csv, Class<A> clazz) {
        try {
            var fs = Arrays.asList(clazz.getFields());
            Class<?>[] fts = new Class[fs.size()];

            for (int i = 0; i < fs.size(); i++) {
                fts[i] = fs.get(i).getType();
            }
            Class<?> s = String.class;
            Class<?> i = Integer.class;
            Class<?> d = Double.class;
            Class<?>[] paramTypes = new Class[]{
                    s, s, i, d
            };

            var constructor = clazz.getDeclaredConstructor(fts);
            var fields = clazz.getFields();
            String[] values = csv.split(",");
            //FIXME value types
            var valuesList = new Object[]{
                    values[0],
                    values[1],
                    Integer.valueOf(values[2]),
                    Double.valueOf(values[3])};

            var c = constructor.newInstance(valuesList);
            return c;
        } catch (NoSuchMethodException e) {
            throw new CassavaExitError("error finding constructor", e);
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
            throw new CassavaExitError("error", e);
        }
    }
}
