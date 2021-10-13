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
