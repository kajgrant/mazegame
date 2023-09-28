# 276 Project - Miner's Adventure

A Java Game created by:

- Sean Chan, smc26
- Kaj Grant-Mathiasen, kgrantma
- Ashhal Vellani, ava47
- Bavneet Hothi, bkh6

## Building the game

To build the game and generate a .jar file, please run the following command from a terminal window:

> Please ensure you are currently in the `project/276Project/` directory

```
$ mvn clean package -DskipTests
```

This will generate a .jar file at the following path:

```
276Project\target\Miner's Adventure.jar
```

## Running the game

To run the game, execute the following command from a terminal window:

> Please ensure you are currently in the `project/276Project/` directory

```
$ java -jar "target/Miner's Adventure.jar"
```

## Testing the game

To test the game, please run the following command:

```
$ mvn clean jacoco:prepare-agent install jacoco:report
```

This will run the suite of tests, as well as generate a code coverage report in the following folder:

```
276Project\target\site\jacoco\index.html
```

## Generate Javadocs

To generate Javadocs, run the following:

```
$ mvn site
```

This generates the Javadocs in the following location:

```
276Project\target\site\apidocs\index.html
```