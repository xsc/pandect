(defproject pandect "1.0.2-SNAPSHOT"
  :description "Message Digest and Checksum Library for Clojure"
  :url "https://github.com/xsc/pandect"
  :license {:name "MIT"
            :comment "MIT License"
            :url "https://choosealicense.com/licenses/mit"
            :year 2014
            :key "mit"}
  :dependencies [[org.clojure/clojure "1.10.1" :scope "provided"]
                 [org.bouncycastle/bcprov-jdk15on "1.68" :scope "provided"]]
  :profiles {:dev
             {:dependencies [[perforate "0.3.4"]]
              :plugins [[perforate "0.3.4"]]
              :global-vars {*warn-on-reflection* true}}
             :benchmark {:dependencies [[criterium "0.4.6"]
                                        [clj-message-digest "1.0.1"]
                                        [digest "1.4.10"]]
                         :source-paths ["shootout"]
                         :jvm-opts ^:replace ["-Xmx1g" "-server"]}
             :codox
             {:dependencies [[codox-theme-rdash "0.1.2"]]
              :plugins [[lein-codox "0.10.7"]]
              :codox {:themes [:rdash]}}
             :perforate
             [:dev
              {:dependencies [[buddy/buddy-core "1.9.0"]
                              [digest "1.4.10"]]
               :jvm-opts ^:replace ["-Xmx2g" "-server"]
               :source-paths ["benchmarks"]
               :global-vars {*warn-on-reflection* false}}]
             :kaocha
             {:dependencies [[lambdaisland/kaocha "1.0.732"
                              :exclusions [org.clojure/spec.alpha]]]}
             :ci
             [:kaocha
              {:global-vars {*warn-on-reflection* false}}]}

  :prep-tasks [["codegen"]]

  :aliases {"kaocha"    ["with-profile" "+kaocha" "run" "-m" "kaocha.runner"]
            "ci"        ["with-profile" "+ci" "run" "-m" "kaocha.runner"
                         "--reporter" "documentation"]
            "codox"     ["with-profile" "codox" "codox"]
            "bench"     ["with-profile" "perforate" "perforate"]
            "codegen"   ["run" "-m" "pandect.codegen"]}
  :pedantic? :abort)
