FROM openjdk
LABEL maintainer=jerem
WORKDIR /app
COPY libs libs/
COPY resources resources/
COPY classes classes/
ENTRYPOINT ["java", "-cp", "/app/resources:/app/classes:/app/libs/*", "jha.stopcovid.StopcovidApplication"]
EXPOSE 8085 5432
