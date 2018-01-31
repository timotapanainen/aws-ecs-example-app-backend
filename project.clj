(defproject aws-ecs-example-app-backend "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [io.pedestal/pedestal.service "0.5.3"]
                 [io.pedestal/pedestal.route "0.5.3"]
                 [io.pedestal/pedestal.jetty "0.5.3"]
                 [org.slf4j/slf4j-simple "1.7.21"]
                 [environ "1.1.0"]]
  :main myapp-backend.core
  :profiles {
             :uberjar {
                       :aot  :all
                       :main myapp-backend.core}})