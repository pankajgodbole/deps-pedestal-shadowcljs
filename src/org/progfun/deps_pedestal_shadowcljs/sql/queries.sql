--
-- src/org/progfun/deps-pedestal-shadowcljs/sql/drugs.SQL
--
-- HugSQL works by converting SQL definitions to Clojure functions. It requires us
-- to define a source file and a Clojure namespace where the functions
-- will be added.

-- :name drugs :? :*                    (:name -the name of the function, :? :* - to return a list of results)
-- :doc  Returns the names of all drugs ()
SELECT * FROM drugs;

--SELECT * FROM drugs WHERE id=9;

-- :name new-drug :insert :1
-- :doc  "Insert a new drug"
INSERT INTO
  drugs (name, availability, price)
VALUES
  (:name :availability, :price)
RETURNING
  id;
