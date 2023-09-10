# Build project steps

1. Build the project jar file. The file can be found in the target folder. *note Perform this command in the root folder
   of the project.

`mvn clean install`

2. Application start up via java commando

`java -jar target/wordapi-1.0-SNAPSHOT.jar`

3. Perform the letter word api call

`curl -X POST http://localhost:8080/api/letter-words/combinations --data '@input.json' --header 'Content-Type: application/json' > output.json`
