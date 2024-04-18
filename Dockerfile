FROM azul/zulu-openjdk:17.0.10-jre

ARG JAR_FILE=/build/libs/session-storage-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /session-storage.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=develop", "/session-storage.jar"]