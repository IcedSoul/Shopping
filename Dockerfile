FROM tomcat:9.0.10-jre8
COPY ./target/Shopping.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]
EXPOSE 8080