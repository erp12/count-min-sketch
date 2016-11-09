
(ns count-min-sketch.params
  (:require [clojure.math.numeric-tower :as math]))

(defn optimal-number-of-hash-functions
  [num-items delta]
  (* 2
     (Math/log (/ num-items
                  delta))))

(defn optimal-width
  [epsilon]
  (/ Math/E
     epsilon))

(defn optimal-count-min-sketch-params
  [num-items delta epsilon]
  (let [w (optimal-width epsilon)
        d (optimal-number-of-hash-functions num-items delta)]
    {:num-items num-items
     :aprox-false-pos-rate delta
     :width (Math/round w)
     :num-hash-funcs (Math/round d)}))

