FROM  azul/zulu-openjdk-alpine:11
RUN apk update
LABEL maintainer="jpoffy@ameritech.net"

COPY target/audit-0.1.0-SNAPSHOT.jar /opt/Audit.jar
RUN adduser -u 40003 -D -s /bin/bash hl7acc
COPY entry.sh /

RUN chmod +x /entry.sh
RUN chmod +r /opt/Audit.jar

USER hl7acc

ENTRYPOINT [ "/entry.sh" ]

