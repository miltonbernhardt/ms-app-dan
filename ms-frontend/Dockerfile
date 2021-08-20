FROM openjdk:11.0.7-slim
LABEL maintainer="mail@gmail.com"
ARG JAR_FILE
ADD target/${JAR_FILE} ms-frontend-app.jar
RUN echo ${JAR_FILE}
ENTRYPOINT ["java","-jar","/ms-frontend-app.jar"]