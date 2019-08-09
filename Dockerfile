FROM circleci/openjdk:8-jdk-browsers
ENV PORT 80
EXPOSE 80
COPY target/socrates-reporting-automation-0.0.1-SNAPSHOT.jar /opt/
WORKDIR /opt
CMD ["java","-jar","socrates-reporting-automation-0.0.1-SNAPSHOT.jar"]
