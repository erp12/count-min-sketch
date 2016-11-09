[![Clojars Project](https://img.shields.io/clojars/v/count-min-sketch/count-min-sketch.svg)](https://clojars.org/count-min-sketch)


# count-min-sketch

A simple Count-Min Sketch implementation in Clojure.

## Installation

Add the following to your project dependencies:

```
[count-min-sketch "0.1.0"]
```

Then call `lein deps` from the project folder.

## Usage

```clj
(require '[count-min-sketch.core :as cms])

;; Creating a Count-Min Sketch requires 3 peices of information.
;; 1. An over estimate of the number of items that you expect to store. We will use 10000.
;; 2. Delta. Should be between 0 and 1. This is used to determine the number of rows in the hash-table, and thus the number of hash functions used.
;; 3. Epsilon. Should be between 0 and 1. This is used to determine the width of the hash-table.
(def my-bloom-filter (atom (bf/make-bloom-filter 10000 0.01)))

;; To preprocess the CMS we call add-elements-cms
(swap! my-bloom-filter #(cms/add-elements-cms % (range 2000)))

;; To check the aproximate frequency of an item we call check-element-bloom-filter
(cms/get-aprox-freq @my-CMS 555) ;; probably 1
(cms/get-aprox-freq @my-CMS 1)   ;; probably 1 
(cms/get-aprox-freq @my-CMS -10) ;; probably 0 

;; To check the approximate frequency of a collection of new items, we can call `get-aprox-freqs`.
(apply + (cms/get-aprox-freqs @my-CMS (range 2000))) ;; Hopefuly 2000
```

## Examples

In the `/test/count_min_sketch/core_test.clj` file, there are 2 simple example usages.

## Contribute

Everyone is encouraged to submit pull requests, or open issues with feature requests.

## License

Copyright © 2016 Edward Pantridge

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.