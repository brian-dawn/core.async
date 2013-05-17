;;   Copyright (c) Rich Hickey and contributors. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;;   which can be found in the file epl-v10.html at the root of this distribution.
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any other, from this software.

(ns core.async.protocols)

(defprotocol ReadPort
  (take! [port fn1-handler] "returns fn0 (fn1 bound to val) cb if taken, nil if take was enqueued"))

(defprotocol WritePort
  (put! [port val fn0-handler] "returns fn0 cb if put, nil if put was enqueued"))

(defprotocol Channel
  (close! [chan]))

(defprotocol Locking
  (lock [h])
  (unlock [h]))

(defprotocol Handler
  (active? [h] "returns true if has callback. Must work w/o lock")
  (lock-id [h] "a unique id for lock acquisition order, 0 if no lock")
  (commit [h] "commit to fulfilling its end of the transfer, returns cb. Must be caled within lock"))

(defprotocol Buffer
  (full? [b])
  (remove! [b])
  (add! [b]))
