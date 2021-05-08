#!/usr/bin/env bash
mvn io.quarkus:quarkus-maven-plugin:1.13.3.Final:create \
    -DplatformVersion=1.13.3.Final \
    -DprojectGroupId=org.agoncal.sample.quarkus \
    -DprojectArtifactId=configuration-profile \
    -DprojectVersion=1.0-SNAPSHOT \
    -DclassName="org.agoncal.sample.quarkus.book.rest.BookResource" \
    -Dpath="/api/books" \
    -Dextensions="resteasy, resteasy-jsonb, hibernate-orm, jdbc-postgresql, jdbc-h2"
