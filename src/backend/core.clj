(ns backend.core
  (:require
    [io.pedestal.http :as http]
    [io.pedestal.http.route :as route]
    [environ.core :refer [env]])
  (:gen-class))

(defn container-metadata []
  (if-let [metadata-file (env :ecs-container-metadata-file)]
    (slurp metadata-file)
    "metadata not available"))

(defn respond-root [request]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "API demo response"})

(defn respond-health [request]
  {:status 200 :body "Ok"})

(defn respond-with-container-metadata [request]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (container-metadata)})

(def routes
  (route/expand-routes
    #{["/api/" :get respond-root :route-name :root]
      ["/api/health" :get respond-health :route-name :health]
      ["/api/metadata" :get respond-with-container-metadata :route-name :metadata]}))

(defn create-server []
  (http/create-server
    {::http/routes routes
     ::http/type   :jetty
     ::http/port   8080}))


(defn -main [& args]
  (http/start (create-server)))