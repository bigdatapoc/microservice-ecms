FROM java:8
VOLUME /tmp
RUN wget https://johnvansickle.com/ffmpeg/builds/ffmpeg-git-64bit-static.tar.xz
RUN tar xvf ffmpeg-git-*.tar.xz
RUN cd ./ffmpeg-git-*
RUN cp ffmpeg-git-20180526-64bit-static/qt-faststart /usr/local/bin/
ADD target/microservice-ecms-transcoding-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
