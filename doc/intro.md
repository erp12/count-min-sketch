# Introduction to count-min-sketch

Let's make a simple Count-Min Sketch to aproximate how frequently an item occurs in our datastream.

Let's start by `require`-ing the library.

```clj
(require '[count-min-sketch.core :as cms])
```

Next we must create the CountMinSketch object. Creating a Count-Min Sketch requires 3 peices of information.

1. An over estimate of the number of items that you expect to store. We will use 10000.
2. Delta. Should be between 0 and 1. This is used to determine the number of rows in the hash-table, and thus the number of hash functions used.
3. Epsilon. Should be between 0 and 1. This is used to determine the width of the hash-table.

For this example, we will store our Count-Min Sketch in an atom.

```clj
(def my-CMS (atom (cms/make-count-min-sketch 10000 0.001 0.001)))
```
