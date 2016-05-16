(defproject pandect "0.6.0-SNAPSHOT"
  :description "Message Digest and Checksum Library for Clojure"
  :url "https://github.com/xsc/pandect"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"
            :year 2014
            :key "mit"}
  :dependencies [[org.clojure/clojure "1.8.0" :scope "provided"]
                 [org.bouncycastle/bcprov-jdk15on "1.54" :scope "provided"]]
  :exclusions [org.clojure/clojure]
  :source-paths ["src/clojure" "target/generated"]
  :java-source-paths ["src/java"]
  :profiles {:dev {:plugins [[codox "0.8.10"]]
                   :codox {:project {:name "pandect"}} }
             :benchmark {:dependencies [[criterium "0.4.3"]
                                        [clj-message-digest "1.0.0"]
                                        [digest "1.4.4"]]
                         :source-paths ["shootout"]
                         :jvm-opts ^:replace ["-Xmx1g" "-server"]}
             :1.5 {:dependencies [[org.clojure/clojure "1.5.1"]]}
             :1.6 {:dependencies [[org.clojure/clojure "1.6.0"]]}
             :1.7 {:dependencies [[org.clojure/clojure "1.7.0"]]}}

  :prep-tasks ["codegen"]
  :aliases {"benchmark" ["with-profile" "dev,benchmark" "run" "-m"]
            "codegen" ["run" "-m" "pandect.codegen"]
            "all" ["with-profile" "+dev:+1.5:+1.6:+1.7"]}
  :pedantic? :abort)
