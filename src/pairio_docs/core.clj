(ns pairio-docs.core
  (:use hiccup.core
        [hiccup.page-helpers :only (html5)]
        slingshot.core)
  (:require [clojure.string :as str]
            [clojure.contrib.io :as ju]
            [clojure.java.shell :as sh])
  (:import [com.petebevin.markdown MarkdownProcessor]))


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

(defn href [url text]
  [:a {:href url}
   text])

(defn nav-item [url text]
  [:li (href url text)])

(defn nav []
  [:div {:class "nav"}
   [:ul
    (nav-item "/" "General")
    (nav-item "/instance-config.html" "Instance Config")
    (nav-item "/collaboration.html" "Collaboration")
    (nav-item "/alpha.html" "Alpha Specific Stuff")]
   [:p
    "Want to update this documentation? "
    "Fork "
    (href "https://github.com/zkim/docs.pair.io"
          "this")
    " and send us a pull request."]])

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
        [:div {:class "grid_9 alpha"}
         content]
        [:div {:class "grid_4 suffix_1 omega"}
         (nav)]]
       [:div {:class "clear"}]]])))

(def mdp (MarkdownProcessor.))

(defn markdown [text]
  (.markdown mdp text))

(defn md-path [path]
  (-> path slurp markdown))

(defn md-page [page]
  (main :content (md-path (str "resources/pages/" (name page) ".md"))))

(def site
  {:resources-path "./resources"
   :output-path "./html"
   :pages [:index :instance-config :collaboration :alpha]})


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

 {:index (md-page :index)
           :instance-config (md-page :instance-config)}

(defn gen-site [{:keys [pages
                        resources-path
                        output-path]}]
  (rm-rf output-path)
  (mkdirs output-path)
  (cp-r (str resources-path "/css") output-path)
  (doseq [k pages]
    (let [content (md-page k)
          path (str
                output-path "/"
                (-> k name (str/replace #"\." "/") (str ".html")))]
      (spit path content))))


(gen-site site)
