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
    (nav-item "/pairio-in-5-minutes.html" "Pair.io in 5 minutes.")
    (nav-item "/instance-config.html" "Instance Config")
    (nav-item "/collaboration.html" "Collaboration")
    (nav-item "/faq.html" "FAQ")
    (nav-item "/changelog.html" "Changelog")]
   [:p
    "Please help us improve this site. "
    "Repo's "
    (href "https://github.com/zkim/docs.pair.io"
          "here")
    "."]
   [:p
    "Thanks to "
    (href "https://github.com" "GitHub")
    " you can even edit these pages "
    (href "https://github.com/zkim/docs.pair.io/blob/master/resources/pages/index.md"
          "right in your browser.")]])

(defn footer []
  (html
   [:div {:class "footer"}
    "Please "
    [:a {:href "https://github.com/zkim/docs.pair.io/issues"} "open up an issue"]
    " on this site's "
    [:a {:href "https://github.com/zkim/docs.pair.io"} "github page"]
    " if any of the info here is confusing or could be improved."]))

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
         [:div {:class "content"}
          content]]
        [:div {:class "prefix_1 grid_4 omega"}
         (nav)]]
       [:div {:class "grid_16"}
        (footer)]
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
   :pages [:index
           :pairio-in-5-minutes
           :instance-config
           :collaboration
           :alpha
           :faq
           :changelog]})


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
  (doseq [k pages]
    (let [content (md-page k)
          path (str
                output-path "/"
                (-> k name (str/replace #"\." "/") (str ".html")))]
      (spit path content))))

(defn auto-regen [site & [timeout]]
  (let [control (atom true)
        stop (fn []
               (reset! control false)
               (println "Stopping regen."))
        timeout (or timeout 1000)]
    (future
      (println "Regenerating docs every" timeout "ms.")
      (while @control
        (try+ 
         (use :reload 'pairio-docs.core)
         (gen-site site)
         (Thread/sleep timeout)
         (catch Exception e (println (.getMessage e))))))
    stop))

