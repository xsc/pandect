(defproject pandect "0.1.1-SNAPSHOT"
  :description "Message Digest and Checksum Library for Clojure"
  :url "https://github.com/xsc/pandect"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/data.codec "0.1.0"]] 
  :profiles {:dev {:dependencies [[midje "1.5.1"]]
                   :plugins [[lein-midje "3.0.1"]]}
             :benchmark {:dependencies [[criterium "0.4.1"]
                                        [clj-message-digest "1.0.0"]
                                        [digest "1.4.3"]]
                         :source-paths ["shootout"]
                         :jvm-opts ["-Xmx1g" "-server"]}}
  :aliases { "benchmark" ["with-profile" "dev,benchmark" "run" "-m"] }) 
