# Read Me First
The project consist two modules, robotapp and command-robot.
Server side which is command-robot should run first.

To start command-robot run bellowed command inside /command-robot folder
    mvn spring-boot:run

To start robotapp run bellowed command inside /command-robot/robotapp
    npm install
    ng serve

# Needed Improvements
* A more elegant solution for sending actions in correct order needed.
* When more than 3 actions sent successively angular animations skips previous animations, fix needed. 
