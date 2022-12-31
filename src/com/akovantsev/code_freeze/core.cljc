(ns com.akovantsev.code-freeze.core
  #?(:cljs (:require-macros [com.akovantsev.code-freeze.core :refer [freeze-source]]))
  (:require [clojure.string :as str]))


(defmacro freeze-source
  ([]
   (-> *file* slurp (str/replace #"\s*\(\s*freeze-source(\s+-?\d+)?\s*\)\s*" "") hash))
  ([prev-hash]
   (let [current-hash (freeze-source)]
     (assert (= prev-hash current-hash)
       {'prev-hash prev-hash 'current-hash current-hash 'file-path *file*}))))
