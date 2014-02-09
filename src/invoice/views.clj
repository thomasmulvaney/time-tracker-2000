(ns invoice.views
  (:use hiccup.core hiccup.page hiccup.element
        [hiccup.middleware :only (wrap-base-url)]))

(defn build-overview-row [item]
  [:tr
   (for [[k v] item] [:td (str v)])
   [:td
    [:a {:href (str "/delete")} "X"]]])

(defn build-overview-table [items]
  [:table {:style "width:100%"}
   [:thead
    [:tr
     (for [[k v] (first items)] [:th (name k)])
     [:td "Delete"]]]
   [:tbody
    (map build-overview-row items)]])

(defn overview-html [table]
  [(keyword (str "div#" (:name table)))
   (list
    [:h3 (:name table)]
    (if (empty? (:results table))
      [:p "empty"]
      (build-overview-table (:results table)))
    [:hr])])

(def head
  (list [:meta {:name "viewport"
                :content "width=device-width, initial-scale=1.0"}]
        [:meta {:charset "utf-8"}]
        [:title "Invoice"]
        (include-css "/css/normalize.css" "/css/foundation.min.css")
        (include-js "/js/jquery.js" "/js/foundation.min.js")))

(def nav
  [:nav {:class "top-bar" :data-topbar true}
   [:ul {:class "title-area"}
    [:li {:class "name"}
     [:h1
      [:a {:href "#"} "Invoice 2000"]]]]
   [:section {:class "top-bar-section"}
    [:ul {:class "right"}
     [:li {:class "active"}
      [:a {:href "/"} "Dashboard"]]
     [:li {:class "has-dropdown"}
      [:a {:href "#"} "New"]
      [:ul {:class "dropdown"}
       [:li
        [:a {:href "/members/new"} "Member"]
        [:a {:href "/clients/new"} "Client"]
        [:a {:href "/jobs/new"} "Job"]]]]
     [:li
      [:a {:href "/hours/log"} "Log Hours"]]
     [:li
      [:a {:href "/buid_pdf"} "PDF"]]]]])

(defn render [body]
  "Renders the main view"
  (html
   [:html {:class "no-js" :lang "en"}
    [:head head]
    [:body (list
            nav
            [:div {:class "row"}
             [:div {:class "large-12 columns"} body]]
            (javascript-tag "$(document).foundation()"))]]))

(defn build-form-input [n type]
  "Builds a input box.  n: input name, type: input type"
  [:div {:class "row"}
   [:div {:class "large-4 columns"}
    [:label]
    [:input {:type type
             :name (name n)}]]])

(defn build-form-submit [v]
  "Builds a submit button. v: submit value"
  [:div {:class "row"}
   [:div {:class "large-4 columns"}
    [:input {:class "button"
             :type "submit"
             :value v}]]])

(defn build-form-select [n options]
  "Builds a select box. n: select name, options: select options"
  [:div {:class "row"}
   [:div {:class "large-4 columns"}
    [:label {:name (name n)}]
    [:select {:name (name n)}
     (map (fn [option]
            [:option {:value (:id option)} (:name option)]) options)]]])

(defn build-form [n method action & inputs]
  "Builds a form."
  [:form {:method method :action action :name n}
   (list
    inputs
    (build-form-submit "Submit"))])
