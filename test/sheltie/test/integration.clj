(ns sheltie.test.integration
  (:use [clj-webdriver.taxi]
        [clojure.test])
  (:require [sheltie.handler :refer [app]]
            [clj-webdriver.driver :refer [init-driver]]
            [ring.adapter.jetty :refer [run-jetty]]
            [clojure.java.io :as io])
  (:import (org.openqa.selenium.phantomjs PhantomJSDriver)
           (org.openqa.selenium.remote DesiredCapabilities)))

(defn start-server []
  (loop [server (run-jetty app {:port 3001 :join? false})]
    (if (.isStarted server)
      server
      (recur server))))

(defn stop-server [server]
  (.stop server))

(defn start-browser []
  (set-driver! {:browser :phantomjs}))

(defn stop-browser []
  (quit))

(deftest user-can-visit-todo-page
  (let [server (start-server)]
    (start-browser)
    (to "http://localhost:3001/#/todo")
    (is (= (text "body") "Hello"))
    (stop-browser)
    (stop-server server)))
