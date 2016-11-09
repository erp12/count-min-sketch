
(ns count-min-sketch.core-test
  (:require [clojure.test :refer :all]
            [count-min-sketch.core :refer :all]))
(import '[java.util Random])

(def uniform-cms (-> (make-count-min-sketch 2000 0.001 0.001)
                     (add-elements-cms (range 2000))))

(deftest simple-uniform
  (testing "Simple Uniform CMS"
    (is (< (apply + (get-aprox-freqs uniform-cms (range 2000)))
           2005))))

(defn sample-gaussian [n]
  (let [rng (Random.)]
    (repeatedly n #(.nextGaussian rng))))

(def normal-cms (-> (make-count-min-sketch 10000 0.001 0.001)
                    (add-elements-cms (map #(int (* 100 %)) (sample-gaussian 10000)))))

(deftest simple-uniform
  (testing "Simple Normal CMS"
    (let [zero (get-aprox-freq normal-cms 0)
          pos-ten (get-aprox-freq normal-cms 10)
          neg-ten (get-aprox-freq normal-cms -10)]
      (is (> zero pos-ten))
      (is (> zero neg-ten)))))
