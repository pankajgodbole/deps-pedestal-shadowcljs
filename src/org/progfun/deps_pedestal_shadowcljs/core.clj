;;
;; src/org/progfun/deps_pedestal_shadowcljs/core.clj
;;

(ns org.progfun.deps-pedestal-shadowcljs.core
  (:require [io.pedestal.http :as iphttp]
            [clojure.tools.namespace.repl :refer [refresh]]))

(defn pf-hello-response
  ""
  [_request]
  {:status 200
   :body   "Hello World! 4"})

(def pf-routes
  ""
  #{["/hello" :get `pf-hello-response]})

(def pf-service-map
  ""
  #::iphttp{:routes pf-routes
            :port   8890
            :type   :jetty})

(defonce pf-server (atom nil))

(defn pf-start!
  "Starts the server"
  []
  (reset! pf-server (iphttp/start (iphttp/create-server
                                   (assoc pf-service-map ::iphttp/join? false))))
  (prn "Server started on localhost:8890")
  (prn "Enter (reset) to reload ...")
  :started)

(comment
  (-> pf-service-map
      iphttp/default-interceptors
      iphttp/dev-interceptors
      iphttp/create-server
      iphttp/start))

(comment
    (-> pf-service-map
      iphttp/create-server
      iphttp/start))

(defn pf-stop!
  "Stop the development server"
  []
  (iphttp/stop @pf-server))

(comment (swap! pf-server (fn [server]
                     (when server
                       (iphttp/stop server))
                     nil)))

(defn pf-reset-service
  ""
  []
  (pf-stop!)
  (refresh :after 'org.progfun.deps-pedestal-shadowcljs.core/pf-start!))


(defn -main
  ""
  [& args]
  (println "-main:" args)
  ;;(pf-start!)
  ;;(pf-stop!)
  ;;(iphttp/stop pf-server)
  )
