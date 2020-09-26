FROM  azul/zulu-openjdk-alpine:11
RUN apk update
LABEL maintainer="jpoffy@ameritech.net"
RUN chmod 777 /tmp
COPY target/hl7ws-0.0.3-SNAPSHOT.jar /opt/hl7Producer.jar
RUN adduser -u 40001 -D -s /bin/bash hl7acc
COPY entry.sh /

RUN chmod +x /entry.sh
RUN chmod +r /opt/hl7Producer.jar

USER hl7acc

ENTRYPOINT [ "/entry.sh" ]

