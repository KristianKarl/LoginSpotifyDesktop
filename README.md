# LoginSpotifyDesktop
For Testing Forum Presentation 2017

## To build it:

* Download Eye 2.0 for Java from http://eyeautomate.com/downloadeye.html
* Install it using maven on command line:
  ```sh
  mvn install:install-file -Dfile=<PATH TO UNZIPPED JAR>/eye2.jar -DgroupId=eye -DartifactId=Eye -Dversion=2 -Dpackaging=jar
  ```
* Build the test
  ```sh
  mvn graphwalker:generate-sources package  -Dmaven.test.skip=true
  ```

## More stuffe to do

* Install Spotify.
* Use your own password, look for `YOUR SECRET PASSWORD` in `SomeSmallTest.java`
* You may need to update the images...

## Run the test

```sh
mvn exec:java -Dexec.mainClass="my.org.Runner"    ```
```

## Don't expect the code to work

The code is not a good example of how to automate. It will most likely not work on some other computer than mine. Have a look at a desktop recording of a test running: https://youtu.be/cAs8vKKy58Y

What it does however, is demonstrating how to GraphWalker and Eye2 could run a simple test.
