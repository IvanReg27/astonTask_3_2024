FROM tomcat:10-jdk17
ADD target/travelagency.war /usr/local/tomcat/webapps/travelagency.war
EXPOSE 8080
CMD ["catalina.sh", "run"]