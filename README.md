In order to launch this application you have to have maven installed because it uses "unirest" dependences.

Once you are in this directory just run:

$mvn package

$mvn exec:java -D exec.mainClass=com.flights.app.App

It should compile and run straight away and generate also a txt file with the same output printed in the GUI.
