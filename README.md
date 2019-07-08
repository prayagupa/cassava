`c`a`s`sa`v`a
------

java csv encoder/decoder

csv encoding
--

```java
// only object(data class ~> not equiv of data class in haskell) based encoding
var encoded = Csv.encode(new Audience(
        "android",
        "Luyata",
        1,
        10.0, 
        true
));

// output
android,Luyata,1,10.0,true

//encode with header
var encodeWithHeader = Csv.encodeWithHeader(new Audience(
        "android",
        "Jumla",
        1,
        10.9, 
        false
));

// output
platform,location,deviceId,dwell,reached
android,Jumla,1,10.9,false
```

csv decoding
---------------

```java
Audience audience = Csv.decode("android,Jumla,1,10.9, false", Audience.class);
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

TODO
---
- encode nested data classes
```java
class A {
    B b;
}

class B {
    String s;
    Double d;
}

// output
s,d
```

- release jar 0.1

reference
---

- https://tools.ietf.org/html/rfc4180
- http://hackage.haskell.org/package/cassava-0.5.1.0#readme