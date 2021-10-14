;;
;; src/org/progfun/deps_pedestal_shadowcljs/core.clj
;;

(ns org.progfun.deps-pedestal-shadowcljs.core
  (:require [io.pedestal.http                                 :as    iphttp]
            [clojure.tools.namespace.repl                     :refer [refresh]]
            [org.progfun.deps-pedestal-shadowcljs.handlers    :as    opdh]
            [org.progfun.deps-pedestal-shadowcljs.sql.queries :as    opdsq]))

(defn pf-hello-response
  ""
  [_request]
  {:status 200
   :body   "org.progfun.deps-pedestal-shadowcljs.core 0"})

(def pf-routes
  ""
  #{["/hello"          :get `pf-hello-response       :route-name :core-hello]
    ["/handlers-hello" :get opdh/pf-hello-response   :route-name :opdh-hello]
    ["/queries-hello"  :get opdsq/pf-hello-response  :route-name :opdsq-hello]
    ["/drugs"          :get opdh/list-all-drugs      :route-name :opdsq-drugs]
    ;;["/drugs"        :post]
    })

(def pf-service-map
  ""
  #::iphttp{:routes pf-routes
            :port   8890
            :type   :jetty})

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
    (require '[io.pedestal.http                                 :as    iphttp])
    (require '[clojure.tools.namespace.repl                     :refer [refresh]])
    (require '[org.progfun.deps-pedestal-shadowcljs.handlers    :as    opdh])
    (require '[org.progfun.deps-pedestal-shadowcljs.sql.queries :as    opdsq])))
