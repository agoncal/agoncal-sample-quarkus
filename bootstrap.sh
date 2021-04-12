#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=org.agoncal.sample.quarkus \
        -DprojectArtifactId=panache-optional \
        -DclassName="org.agoncal.sample.quarkus.BookResource" \
        -Dpath="/api/books" \
        -Dextensions="resteasy-jsonb, hibernate-orm-panache, jdbc-h2"
