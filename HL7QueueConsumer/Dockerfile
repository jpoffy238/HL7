FROM  azul/zulu-openjdk-alpine:11
RUN apk update
LABEL maintainer="jpoffy@ameritech.net"
RUN adduser -D -s  /bin/bash hl7app
COPY target/hl7consumerr-0.0.4-SNAPSHOT.jar /opt/hl7Q.jar

COPY entry.sh /

RUN chmod +x /entry.sh

RUN chown hl7app /entry.sh

RUN chmod 555 /opt/hl7Q.jar

USER hl7app

ENTRYPOINT [ "/entry.sh" ]

