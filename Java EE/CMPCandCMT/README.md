# Build
mvn clean package && docker build -t com.sample/quickstart .

# RUN

docker rm -f quickstart || true && docker run -d -p 8080:8080 -p 4848:4848 --name quickstart com.sample/quickstart 