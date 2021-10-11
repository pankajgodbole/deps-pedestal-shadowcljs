;;
;; src/org/progfun/deps-pedestal-shadowcljs/config.clj
;;
;; Config file to handle database connection
;;

(ns org.progfun.deps-pedestal-shadowcljs.config)

(def pf-drugs-db-config
  {:classname   "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname     "//localhost:5432/drugstore"
   :user        "p"
   :password    ""})
