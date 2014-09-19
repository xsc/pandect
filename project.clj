(defproject pandect "0.4.1-SNAPSHOT"
  :description "Message Digest and Checksum Library for Clojure"
  :url "https://github.com/xsc/pandect"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [potemkin "0.3.9"]
                 [org.bouncycastle/bcprov-jdk15on "1.51"]]
  :source-paths ["src/clojure" "target/generated"]
  :java-source-paths ["src/java"]
  :profiles {:dev {:dependencies [[midje "1.6.3"]]
                   :plugins [[lein-midje "3.1.3"]
                             [codox "0.8.9"]]
                   :codox {:include [pandect.core]}}
             :benchmark {:dependencies [[criterium "0.4.3"]
                                        [clj-message-digest "1.0.0"]
                                        [digest "1.4.4"]]
                         :source-paths ["shootout"]
                         :jvm-opts ["-Xmx1g" "-server"]}}
  :prep-tasks ["codegen"]
  :aliases {"benchmark" ["with-profile" "dev,benchmark" "run" "-m"]
            "codegen" ["run" "-m" "pandect.codegen"]
            "test" ["with-profile" "+dev" "midje"]})
