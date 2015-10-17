(ns sheltie.dev
  (:require [clojure.edn :as edn]
            [clojurescript-build.auto :as auto]
            [figwheel-sidecar.auto-builder :as fig-auto]
            [figwheel-sidecar.config :as conf]
            [figwheel-sidecar.core :as fig]))

(defonce fig-server (atom {}))

(defn start-figwheel []
  (let [server (fig/start-server { :css-dirs ["resources/public/css"] })
        builder (fig-auto/autobuild*
                 {:builds [{:id "dev"
                            :source-paths ["src-cljs" "env/dev/cljs"]
                            :compiler {:output-to            "resources/public/js/app.js"
                                       :output-dir           "resources/public/js/out"
                                       :externs              ["react/externs/react.js"]
                                       :source-map           true
                                       :optimizations        :none
                                       :source-map-timestamp true}}]
                  :figwheel-server server})]
    (reset! fig-server {:figwheel-server server :cljs-builder builder})))

(defn stop-figwheel []
  (let [server (:figwheel-server @fig-server)
        builder (:cljs-builder @fig-server)]
    (when builder
      (auto/stop-autobuild! builder))
    (when server
      (fig/stop-server server))
    (reset! fig-server {:figwheel-server nil :cljs-builder nil})))
