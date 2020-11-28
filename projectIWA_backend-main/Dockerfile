FROM openjdk:11
LABEL maintainer=jerem
WORKDIR /app
COPY build/docker/libs libs/
COPY build/docker/resources resources/
COPY build/docker/classes classes/
ENTRYPOINT MAIL_PASSWORD=${MAIL_PASSWORD} MAIL_USERNAME=${MAIL_USERNAME} java -cp /app/resources:/app/classes:/app/libs/* jha.stopcovid.StopcovidApplication
EXPOSE 8085 5432
