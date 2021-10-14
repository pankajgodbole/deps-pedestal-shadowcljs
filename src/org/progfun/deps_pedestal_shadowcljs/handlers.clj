;;
;; src/org/progfun/deps-pedestal-shadowcljs/handlers.clj
;;
;; HugSQL works by converting SQL definitions to Clojure functions. It requires us
;; to define a namespace to hold the model of the data.
;;

(ns org.progfun.deps-pedestal-shadowcljs.handlers
  (:require [clojure.spec.alpha                               :as    csa]
            [io.pedestal.http                                 :as    iph]
            [org.progfun.deps-pedestal-shadowcljs.config      :refer [pf-drugs-db-config]]
            [org.progfun.deps-pedestal-shadowcljs.sql.queries :as    opdsq] :reload))

(defn pf-hello-response
  ""
  [_request]
  {:status 200
   :body   "org.progfun.deps-pedestal-shadowcljs.handlers 1"})

(defn list-all-drugs
  ""
  [_]
  (iph/json-response (opdsq/drugs pf-drugs-db-config)))

(defn create-drug
  ""
  [request]
  (let [new-drug (select-keys (:json-params request)
                              [:name :availability :price])]
    (if (csa/valid? ::drug new-drug)
      (let [[_ id] (opdsq/new-drug pf-drugs-db-config new-drug)]
        (iph/json-response {:msg "Drug created successfully."
                            :id id}))
      (assoc (iph/json-response {:msg "Please send a valid drug."})
             :status 400))))

(comment
  (do
    (require '[io.pedestal.http                                 :as    iph])
    (require '[org.progfun.deps-pedestal-shadowcljs.config      :refer [pf-drugs-db-config]])
    (require '[org.progfun.deps-pedestal-shadowcljs.sql.queries :as    opdsq] :reload)))
