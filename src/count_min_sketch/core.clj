

(ns count-min-sketch.core
  (:gen-class)
  (:require [ds-utils.hash-functions :as hash-funcs]
            [count-min-sketch.params :as params]))

(defrecord CountMinSketch [cms-array hash-family param-map])

(defn- make-cms-arrays
  [w d]
  (vec (repeat d
               (vec (repeat w 0)))))

(defn make-count-min-sketch
  [num-items delta epsilon]
  (let [p (params/optimal-count-min-sketch-params num-items delta epsilon)
        cms-array (make-cms-arrays (:width p) (:num-hash-funcs p))
        hash-family (hash-funcs/make-universal-hash-family (:num-hash-funcs p)
                                                           (:width p))]
    (CountMinSketch. cms-array hash-family p)))

(defn add-element-cms
  [cms element]
  (let [hash-inds (hash-funcs/pass-to-hash-family (:hash-family cms)
                                                  element)
        new-cms-array (pmap (fn [i row] (assoc row i (inc (nth row i))))
                            hash-inds
                            (:cms-array cms))]
    (assoc cms :cms-array new-cms-array)))

(defn add-elements-cms
  [cms elements]
  (reduce add-element-cms cms elements))

(defn get-aprox-freq
  [cms element]
  (let [hash-inds (hash-funcs/pass-to-hash-family (:hash-family cms)
                                                  element)
        cms-subset (vec (pmap (fn [i row] (nth row i))
                              hash-inds
                              (:cms-array cms)))]
    (apply min cms-subset)))

(defn get-aprox-freqs
  [ elements]
  (vec (map #(get-aprox-freq cms %) elements)))
