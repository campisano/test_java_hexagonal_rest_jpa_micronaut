ARG FROM_IMAGE
FROM $FROM_IMAGE

RUN useradd -r -U app -d /srv/app -m
USER app:app

COPY ./app.jar /srv/app/app.jar

WORKDIR /srv/app
ENTRYPOINT java -jar app.jar
