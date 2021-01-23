(ns ^:no-doc pandect.utils.bouncy-castle-provider
  (:import java.security.Security
           org.bouncycastle.jce.provider.BouncyCastleProvider))

(defonce __install__
  (Security/addProvider (BouncyCastleProvider.)))
