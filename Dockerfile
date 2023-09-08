FROM eclipse-temurin:17-jre-focal

# user 추가 (m은 홈 디렉토리 생성, s는 사용자가 사용할 쉘 지정)
RUN useradd -ms /bin/bash xeropise
USER xeropise
WORKDIR /home/xeropise
COPY ./build/libs/Nexon-NewName-Auction-Copy.jar app.jar

EXPOSE 8080

ENTRYPOINT exec java -jar /home/xeropise/app.jar
