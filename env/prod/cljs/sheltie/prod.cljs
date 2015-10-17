(ns sheltie.app
  (:require [sheltie.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
