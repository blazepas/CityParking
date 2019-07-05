#!/bin/bash

sudo find / -type d -name "deployments" -exec cp car-park-web/target/car-park-web-1.0-SNAPSHOT.war {} \;
echo "Making a copy war file in your JBOSS localisation"
echo "When you will end, press CTRL+C to shutdown JBOSS." & sleep 5

(sudo xdg-open http://localhost:8080/car-park-web-1.0-SNAPSHOT/index.html & sleep 5)

sudo find / -name "standalone.sh" -exec sh {} +  
