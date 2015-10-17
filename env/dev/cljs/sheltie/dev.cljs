(ns ^:figwheel-no-load sheltie.app
  (:require [sheltie.core :as core]
            [figwheel.client :as figwheel :include-macros true]))

(enable-console-print!)

(figwheel/watch-and-reload
  :websocket-url "ws://dev.local:3449/figwheel-ws"
  :on-jsload core/mount-components)

(core/init!)
