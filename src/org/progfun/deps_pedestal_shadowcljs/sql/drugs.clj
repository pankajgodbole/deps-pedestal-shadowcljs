;;
;; src/org/progfun/deps-pedestal-shadowcljs/sql/drugs.clj
;;
;; HugSQL converts SQL definitions to Clojure functions. It reads an
;; SQL file and takes a Clojure namespace to where to add the functions.
;;

(ns org.progfun.deps-pedestal-shadowcljs.sql.drugs
  (:require [hugsql.core :as hc]))

(defn pf-hello-response
  ""
  [_request]
  {:status 200
   :body   "Hello World! 5"})

(hc/def-db-fns "org/progfun/deps_pedestal_shadowcljs/sql/drugs.sql")

(defn drugs-sql->fns
  ""
  []
  (hc/def-db-fns "org/progfun/deps_pedestal_shadowcljs/sql/drugs.sql"))

(comment
  (require '[hugsql.core :as hc]))
