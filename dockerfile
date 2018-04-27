FROM gettyimages/spark

MAINTAINER Yurii Kaminskyi "yurii.kaminskyi@gmail.com"

ENV PATH $PATH:/spark/bin

ADD ./TriangleCount /app

WORKDIR /app

CMD spark-submit --class Main --master local target/TriangleCount-1.0-SNAPSHOT-jar-with-dependencies.jar
