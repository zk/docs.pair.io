(ns regen
  (require [pairio-docs.core :as c]))

(defn -main [& _]
  (c/auto-regen c/site))

