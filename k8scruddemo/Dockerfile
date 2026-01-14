FROM eclipse-temurin:17
WORKDIR /apps
LABEL authors="heart"
COPY target/k8scruddemoapp.jar /apps/k8scruddemoapp.jar
ENTRYPOINT ["java", "-jar", "/apps/k8scruddemoapp.jar"]