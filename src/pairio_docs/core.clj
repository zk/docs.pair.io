(ns pairio-docs.core
  (:use hiccup.core
        [hiccup.page-helpers :only (html5)]
        slingshot.core)
  (:require [clojure.string :as str]
            [clojure.contrib.io :as ju]
            [clojure.java.shell :as sh]))


(def default-title "docs.pair.io")

(defn include-css [& cs]
  (->> cs
       (map name)
       (map #(str "/css/" % ".css"))
       (map #(html [:link {:rel :stylesheet :href %}]))))

(defn header []
  [:div {:class "grid_16"}
   [:div {:class "header"}
    [:h1 "docs.pair.io"]]])

(defn main [& opts]
  (let [{:keys [title content path]}
        (apply hash-map opts)]
    (html5
     [:head
      [:title (or title default-title)]
      (include-css :reset :text :960 :main)]
     [:body
      [:div {:class "container_16"}
       (header)]
      [:div {:class "clear"}]
      [:div {:class "container_16 content_wrapper"}
       [:div {:class "grid_14 prefix_1 suffix_1"}
        [:div {:class "grid_10 alpha"}
         content]
        [:div {:class "grid_3 suffix_1 omega"}
         "nav"]]
       [:div {:class "clear"}]]])))

(def site
  {:resources-path "./resources"
   :output-path "./html"
   :pages {:index (main :content [:div "hello world!"])}})


;; ===========

(defn mkdirs [out-dir]
  (let [f (java.io.File. out-dir)]
    (when (.isFile f)
      (throw+ (Exception. (str "mkdirs -- path " out-dir " exists, and is not a directory."))))
    (when-not (.exists f)
      (.mkdirs f))))

(defn rm-rf [path]
  (when-not (= "/usr" path) ; smiley
    (ju/delete-file-recursively path true)))

(defn cp-r [source dest]
  (sh/sh "cp" "-r" source dest))

(defn gen-site [{:keys [pages
                        resources-path
                        output-path]}]
  (rm-rf output-path)
  (mkdirs output-path)
  (cp-r (str resources-path "/css") output-path)
  (doseq [k (keys pages)]
    (let [path (str
                output-path "/"
                (-> k name (str/replace #"\." "/") (str ".html")))]
      (spit path (get pages k (str "<!-- NO CONTENT FOUND FOR " k " -->"))))))


(gen-site site)
