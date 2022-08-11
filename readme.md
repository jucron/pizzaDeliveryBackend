For certifications (handshake SSL):

Creating:
keytool -genkeypair -alias pizzaDelivery -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore pizzaDelivery.p12 -validity 3650

Extracting:
keytool -export -keystore src/main/resources/keystore/pizzaDelivery.p12 -alias pizzaDelivery -file myCertificate.crt

Adding to JVM (admin) password: 'changeit'
Bash:
keytool -importcert -file myCertificate.crt -alias pizzaDelivery -keystore /c/Program\ Files/Amazon\ Corretto/jdk17.0.3_6/lib/security/cacerts
keytool -importcert -file myCertificate.crt -alias pizzaDelivery -keystore “c:/Program Files/Amazon Corretto/jdk17.0.3_6/lib/security/cacerts”
