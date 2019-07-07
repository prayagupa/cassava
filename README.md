`c`a`s`sa`v`a
------

java csv encoder/decoder

csv encoding
--

```java
// only object(data class ~> not equiv of data class in haskell) based encoding
var encoded = Csv.encode(new Audience(
        "android",
        "SEA",
        1,
        10.0
));

// output
android,SEA,1,10.0
```

async csv writer
-----------

```java

var success = FileWriter.openStream("/tmp/abc.csv")
        .thenCompose($ -> $.append(encoded))
        .thenCompose($ -> $.flushStream())
        .thenCompose($ -> $.closeStream());

//failure example
var failure = FileWriter.openStream("/dont_exist/abc.csv")
        .thenCompose($ -> $.append(encoded))
        .thenCompose($ -> $.flushStream())
        .thenCompose($ -> $.closeStream());

failure.handle((s, f) -> {
    if (f != null)
        System.out.println("error: " + f);
    return s;
});
```

https://tools.ietf.org/html/rfc4180

reference
---

- http://hackage.haskell.org/package/cassava-0.5.1.0#readme