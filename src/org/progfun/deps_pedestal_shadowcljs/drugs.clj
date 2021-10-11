;;
;; src/org/progfun/deps-pedestal-shadowcljs/drugs.clj
;;
;; HugSQL works by converting SQL definitions to Clojure functions. It requires us
;; to define a namespace to hold the model of the data.
;;

(ns org.progfun.deps-pedestal-shadowcljs.drugs
  (:require [io.pedestal.http                               :as    iph]
            [org.progfun.deps-pedestal-shadowcljs.config    :refer [pf-drugs-db-config]]
            [org.progfun.deps-pedestal-shadowcljs.sql.drugs :as    opdsd]))

(defn call-hello
  ""
  [_]
  (opdsd/pf-hello-response))

(comment
  (defn all-drugs
  ""
  [_]
  (iph/json-response (opdsd/drugs pf-drugs-db-config))))

(comment
  (do
    (require '[io.pedestal.http                               :as    iph])
    (require '[org.progfun.deps-pedestal-shadowcljs.config    :refer [pf-drugs-db-config]])
    (require '[org.progfun.deps-pedestal-shadowcljs.sql.drugs :as    opdsd] :reload)))
