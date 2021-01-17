(defproject pandect "1.0.0-SNAPSHOT"
  :description "Message Digest and Checksum Library for Clojure"
  :url "https://github.com/xsc/pandect"
  :license {:name "MIT"
            :comment "MIT License"
            :url "https://choosealicense.com/licenses/mit"
            :year 2014
            :key "mit"}
  :dependencies [[org.clojure/clojure "1.10.1" :scope "provided"]
                 [org.bouncycastle/bcprov-jdk15on "1.54" :scope "provided"]
                 ^:inline-dep [potemkin "0.4.5"]
                 ^:inline-dep [riddley "0.2.0"]]
  :profiles {:dev
             {:global-vars {*warn-on-reflection* true}}
             :benchmark {:dependencies [[criterium "0.4.3"]
                                        [clj-message-digest "1.0.0"]
                                        [digest "1.4.4"]]
                         :source-paths ["shootout"]
                         :jvm-opts ^:replace ["-Xmx1g" "-server"]}
             :kaocha
             {:dependencies [[lambdaisland/kaocha "1.0.732"
                              :exclusions [org.clojure/spec.alpha]]]}
             :ci
             [:kaocha
              {:global-vars {*warn-on-reflection* false}}]}

  :prep-tasks [["codegen"]]
  :plugins [[lein-isolate "0.2.2-SNAPSHOT"]]
  :middleware [leiningen.isolate/middleware]

  :aliases {"kaocha"    ["with-profile" "+kaocha" "run" "-m" "kaocha.runner"]
            "ci"        ["with-profile" "+ci" "run" "-m" "kaocha.runner"
                         "--reporter" "documentation"]
            "benchmark" ["with-profile" "dev,benchmark" "run" "-m"]
            "codegen"   ["run" "-m" "pandect.codegen"]}
  :pedantic? :abort)
