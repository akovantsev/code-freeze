# code-freeze

Primitive safeguard against accidental changes in clj/cljs/cljc files which must not be changed.
<br>
<br>Imagine you rename keyword (or change function signature) across dozens of project files,
but forget that in 1 place it is part of a public API.
<br>This assert will remind you to closer review changes in particular namespace.


## Install

```clojure
;; in deps.edn
{:deps {github-akovantsev/code-freeze
        {:git/url "https://github.com/akovantsev/code-freeze"
         :sha     "990e32ad60864023443d92f561f8f9665ee1f2eb"}}} ;; actual sha
```

## Usage

In some public API namespace **file** (it has to be file, sorry):
```clojure
(ns my.public.api
  (:require [com.akovantsev.code-freeze.core :refer [freeze-source]]))

(freeze-source)
```

Pick where you want `(freeze-source)` line in an ns (best is right after the `ns` form), send it to repl, and paste `int` it returned you as an arg:

```clojure
(ns my.public.api
  (:require [com.akovantsev.code-freeze.core :refer [freeze-source]]))

(freeze-source -554440247)
```

Now, if you change anything in this **file**, except the int hash - you'll get compile time exception:
```clojure
Execution error (AssertionError) at com.akovantsev.code-freeze.core/freeze-source (core.cljc:10).
Assert failed: {prev-hash -1676777871, current-hash 554440247, file-path "/Users/kek/my-proj/src/my/public/api.cljc"}
(= prev-hash current-hash)
```
