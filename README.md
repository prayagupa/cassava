Cassava
------

Java CSV encoder/decoder

example snipped

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