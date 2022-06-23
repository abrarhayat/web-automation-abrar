# Running the test suite

## Step 1

### Download and install IntelliJ IDEA Community Edition [from here.](https://www.jetbrains.com/idea/download)

## Step 2

### Open IntelliJ IDEA and select _Project From Existing Sources_ and select the build.gradle file from the project directory(the cloned project from github)

#### After that, the project will start building by downloading the dependencies first which may take a while

## Step 3

### In IntelliJ IDEA, from _File > Settings > Build, Execution, Deployment > Build Tools > Gradle_, select _Build and run using:_ and _Run tests using:_ options as IntelliJ IDEA. By default Gradle will be selected for both

## Step 4

### Go the _src/test/java_ and run the desired tests using the run icon beside each _@Test_ annotation

---

### Independent Fat JAR / Uber JAR apps

### Now, you can create Fat Jars apps by running the _fatJar_ task in the _build.gradle_ file. The resulting JAR directory **MUST** have the data folder in that same directory for the JARs to work
