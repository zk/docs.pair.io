(ns gen
  (require [pairio-docs.core :as c]))

(defn -main [& _]
  (c/gen-site c/site)
  (println "Site generated to" (:output-path c/site)))


