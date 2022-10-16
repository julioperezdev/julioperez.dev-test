FROM postgres AS builder
ENV POSTGRES_DB=julioperez-db
ENV PGDATA=/pgdata
ENV POSTGRES_USER=julioperezdev
ENV POSTGRES_PASSWORD=openSourcePassword
COPY ./initSqlScripts/* /docker-entrypoint-initdb.d/
EXPOSE 5432