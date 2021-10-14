;;
;; src/org/progfun/deps-pedestal-shadowcljs/sql/queries.clj
;;
;; HugSQL converts SQL definitions to Clojure functions. It reads an
;; SQL file and takes a Clojure namespace to where to add the functions.
;;

(ns org.progfun.deps-pedestal-shadowcljs.sql.queries
  (:require [hugsql.core        :as hc]
            [clojure.spec.alpha :as csa]))

(defn pf-hello-response
  ""
  [_request]
  {:status 200
   :body   "org.progfun.deps-pedestal-shadowcljs.sql.queries/pf-hello-response 0"})

(defn queries
  ""
  [_request]
  {:status 200
   :body   "org.progfun.deps-pedestal-shadowcljs.sql.queries/queries 0"})

(hc/def-db-fns "org/progfun/deps_pedestal_shadowcljs/sql/queries.sql")

(csa/def ::name         string?)
(csa/def ::availability int?)
(csa/def ::price        (csa/or :price int?
                                :price float?))
(csa/def ::drug         (csa/keys :req-un [::name ::availability ::price]))

(comment
  (require '[hugsql.core        :as hc]
           '[clojure.spec.alpha :as csa]))
