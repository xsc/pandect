(defproject pandect "0.3.3"
  :description "Message Digest and Checksum Library for Clojure"
  :url "https://github.com/xsc/pandect"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.bouncycastle/bcprov-jdk15on "1.50"]]
  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"]
  :profiles {:dev {:dependencies [[midje "1.6.3"]]
                   :plugins [[lein-midje "3.1.3"]
                             [codox "0.8.9"]]
                   :codox {:include [pandect.core]}}
             :benchmark {:dependencies [[criterium "0.4.3"]
                                        [clj-message-digest "1.0.0"]
                                        [digest "1.4.3"]]
                         :source-paths ["shootout"]
                         :jvm-opts ["-Xmx1g" "-server"]}}
  :aliases { "benchmark" ["with-profile" "dev,benchmark" "run" "-m"] })
