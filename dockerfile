FROM alpine:latest

RUN apk --no-cache add mysql-client

COPY init.sh /init.sh

RUN chmod +x /init.sh

CMD ["/init.sh"]
