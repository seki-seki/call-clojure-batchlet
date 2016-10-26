(ns hello-world)
(defn hello
  "My first main"
  [& [display-word]]
  (println (or display-word "Hello World")))