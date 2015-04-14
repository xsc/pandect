(defproject pandect "0.5.3-SNAPSHOT"
  :description "Message Digest and Checksum Library for Clojure"
  :url "https://github.com/xsc/pandect"
  :license {:name "MIT License"
            :url "http://xsc.mit-license.org"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [potemkin "0.3.13"]
                 [org.bouncycastle/bcprov-jdk15on "1.52"]]
  :exclusions [org.clojure/clojure]
  :source-paths ["src/clojure" "target/generated"]
  :java-source-paths ["src/java"]
  :profiles {:dev {:dependencies [[midje "1.6.3"]
                                  [joda-time "2.7"]]
                   :plugins [[lein-midje "3.1.3"]
                             [codox "0.8.10"]]
                   :codox {:project {:name "pandect"}} }
             :benchmark {:dependencies [[criterium "0.4.3"]
                                        [clj-message-digest "1.0.0"]
                                        [digest "1.4.4"]]
                         :source-paths ["shootout"]
                         :jvm-opts ^:replace ["-Xmx1g" "-server"]}
             :1.5 {:dependencies [[org.clojure/clojure "1.5.1"]]}}

  :prep-tasks ["codegen"]
  :aliases {"benchmark" ["with-profile" "dev,benchmark" "run" "-m"]
            "codegen" ["run" "-m" "pandect.codegen"]
            "all" ["with-profile" "+dev:+1.5"]
            "test" ["with-profile" "+dev" "midje"]}
  :pedantic? :abort)
