;;
;; src/user.clj
;;

(ns user
  (:require [io.pedestal.http                          :as    iph]
            [clojure.tools.namespace.repl              :refer [refresh]]
            [org.progfun.deps-pedestal-shadowcljs.core :refer [pf-service-map]]))

;; For interactive development
(defonce pf-server (atom nil))

(defn pf-start!
  "Starts the server"
  []
  (reset! pf-server (iph/start (iph/create-server
                                   (assoc pf-service-map ::iph/join? false))))
  (prn "Server started on localhost:8890")
  (prn "Enter (reset) to reload ...")
  :started)

(defn pf-stop!
  "Stop the development server"
  []
  (iph/stop @pf-server))

(comment (swap! pf-server (fn [server]
                     (when server
                       (iph/stop server))
                            nil)))

(defn pf-reset-service
  ""
  []
  (pf-stop!)
  (refresh :after 'user/pf-start!))
