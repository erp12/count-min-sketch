
(ns count-min-sketch.core-test
  (:require [clojure.test :refer :all]
            [count-min-sketch.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

(def simple-cms (-> (make-count-min-sketch 2000 0.001 0.001)
                    (add-elements-cms (range 2000))))

(deftest a-test
  (testing "Simple CMS"
    (is (< (apply + (get-aprox-freqs simple-cms (range 2000)))
           2005))))
