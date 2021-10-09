;;
;; src/org/progfun/deps_pedestal_shadowcljs/core.clj
;;

(ns org.progfun.deps_pedestal_shadowcljs.core
  (:require [io.pedestal.http :as iphttp]))

(defn respond-hello
  ""
  [_request]
  {:status 200
   :body   "Hello World!"})

(def api-routes
  ""
  #{["/hello" :get `respond-hello]})

(defn server
  ""
  []
  (-> #::iphttp{:routes api-routes
                :port   8890
                :type   :jetty}
      iphttp/create-server
      iphttp/start))
