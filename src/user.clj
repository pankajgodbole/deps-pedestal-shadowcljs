;;
;; src/user.clj
;;

(ns user
  (:require [io.pedestal.http                          :as    iphttp]
            [clojure.tools.namespace.repl              :refer [refresh]]
            [org.progfun.deps-pedestal-shadowcljs.core :refer [pf-service-map]]))

;; For interactive development
(defonce pf-server (atom nil))

(defn pf-start!
  "Starts the server"
  []
  (reset! pf-server (iphttp/start (iphttp/create-server
                                   (assoc pf-service-map ::iphttp/join? false))))
  (prn "Server started on localhost:8890")
  (prn "Enter (reset) to reload ...")
  :started)

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
  (refresh :after 'user/pf-start!))
