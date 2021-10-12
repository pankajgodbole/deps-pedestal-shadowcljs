;;
;; src/org/progfun/deps_pedestal_shadowcljs/core.clj
;;

(ns org.progfun.deps-pedestal-shadowcljs.core
  (:require [io.pedestal.http :as iphttp]
            [clojure.tools.namespace.repl :refer [refresh]]
            [org.progfun.deps-pedestal-shadowcljs.drugs]
            [org.progfun.deps-pedestal-shadowcljs.sql.drugs]))

(defn pf-hello-response
  ""
  [_request]
  {:status 200
   :body   "Hello World! 9"})

(def pf-routes
  ""
  #{;;["/hello" :get `pf-hello-response]
    ["/hello" :get org.progfun.deps-pedestal-shadowcljs.sql.drugs/pf-hello-response :route-name :say-hello]
    ["/drugs" :get org.progfun.deps-pedestal-shadowcljs.drugs/all-drugs :route-name :get-drugs]
    })

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

(comment
  (do
    (require '[io.pedestal.http :as iphttp])
    (require '[clojure.tools.namespace.repl :refer [refresh]])
    (require '[org.progfun.deps-pedestal-shadowcljs.drugs])
    (require '[org.progfun.deps-pedestal-shadowcljs.sql.drugs])))
