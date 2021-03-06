(defproject invoice "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [korma "0.3.0-RC6"]
                 [lobos "1.0.0-beta1"]
                 [com.h2database/h2 "1.3.170"]
                 [compojure "1.1.6"]
                 [ring/ring-json "0.2.0"]
                 [hiccup "1.0.4"]
                 [clj-time "0.6.0"]]
  :plugins [[lein-ring "0.8.8"]]
  :ring {:handler invoice.core/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
