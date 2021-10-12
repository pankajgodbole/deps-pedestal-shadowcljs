(require '[clojure.java.jdbc :as clj-jdbc])

(def pg-db {:dbtype "postgresql"
            :dbname "drugstore"
            :host "localhost"
            :user "postgres"
            :password "pgpswd"
            ;;:ssl true
            ;;:sslfactory "org.postgresql.ssl.NonValidatingFactory"
            })

(clj-jdbc/query pg-db
                ["select * from drugs"])

#_(comment
  ({:id 1,
  :name "Vicodin, Norco, Xodol (hydrocodone, acetaminophen)",
  :availability 100,
  :price 14.0}
 {:id 2,
  :name "Synthroid, Levoxyl, Unithroid (levothyroxine)",
  :availability 200,
  :price 11.0}
 {:id 3,
  :name "Delasone, Sterapred (prednisone)",
  :availability 150,
  :price 5.0}
 {:id 4, :name "Amoxil (amoxicillin)", :availability 200, :price 9.0}
 {:id 5,
  :name "Neurontin (gabapentin)",
  :availability 50,
  :price 13.0}
 {:id 6,
  :name "Prinivil, Zestril (lisinopril)",
  :availability 60,
  :price 7.0}
 {:id 7,
  :name "Lipitor (atorvastatin)",
  :availability 78,
  :price 12.0}
 {:id 8,
  :name "Glucophage (metformin)",
  :availability 180,
  :price 8.0}
 {:id 9, :name "Zofran (ondansetron)", :availability 40, :price 17.0}
 {:id 10, :name "Motrin (ibuprofen)", :availability 70, :price 12.0}))

(clj-jdbc/query pg-db
                ["SELECT * FROM drugs WHERE id=1"])
#_({:id 1,
  :name "Vicodin, Norco, Xodol (hydrocodone, acetaminophen)",
  :availability 100,
  :price 14.0})
