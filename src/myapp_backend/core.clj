(ns myapp-backend.core
    (:require
      [io.pedestal.http :as http]
      [io.pedestal.http.route :as route])
    (:gen-class))


(defn respond-health [request]
      {:status 200 :body "Ok"})

(defn respond-hello [request]
      {:status  200
       :headers {"Content-Type" "text/html"}
       :body    "Backend responding 4"})

(def routes
  (route/expand-routes
    #{["/api/health" :get respond-health :route-name :health]
      ["/api/" :get respond-hello :route-name :hello]}))

(defn create-server []
      (http/create-server
        {::http/routes routes
         ::http/type   :jetty
         ::http/port   8080}))

(defn -main [& args]
      (http/start (create-server)))