Cassava
------

Java CSV encoder/decoder

example snipped

```java
FileWriter.openStream("/tmp/abc.csv")
                .append(encoded)
                .thenCompose($ -> $.flushStream())
                .thenCompose($ -> $.closeStream());
```

https://tools.ietf.org/html/rfc4180

reference
---

- http://hackage.haskell.org/package/cassava-0.5.1.0#readme