FROM openjdk:11.0.7-slim
LABEL maintainer="mail@gmail.com"
ARG JAR_FILE
ADD target/${JAR_FILE} service-gateway-app.jar
RUN echo ${JAR_FILE}
ENTRYPOINT ["java","-jar","/service-gateway-app.jar"]