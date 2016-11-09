
(defproject count-min-sketch "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [ds-utils "0.1.0"]]
  :plugins [[org.clojars.benfb/lein-gorilla "0.4.0"]]
  :main ^:skip-aot count-min-sketch.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

