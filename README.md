# FoodTracker
WebApp da presentare come progetto per il corso di Programmazione Avanzata - UNIVPM - A.A. 2020/2021
Seguente configurazione specificata nel pom.xml per deploy su tomcat tramite Tomcat Maven Plugin

<url>http://localhost:8080/manager/text</url>
            <server>localhost</server>
            <path>/FoodTracker</path>
            <username>tomcat-script</username>
            <password>tomcat</password>

Deploy automatico su tomcat:

mvn 
  clean 
  package 
  install
