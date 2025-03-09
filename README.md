# Flight-Route-Tracker
## Description

The Simple Flight Route Tracker is a Java application that allows users to manage airports, add flight routes, and find the shortest route between two airports using Dijkstra's Algorithm. The system determines the shortest path based on distance and provides estimated travel time and the number of connections.

## Features

Add airports dynamically
Define flight routes with distances

Find the shortest route between two airports

Display the estimated travel time (assuming an 800 km/h average flight speed)

Show the number of connections required for the shortest route

User-friendly Java GUI for interaction

## Technologies Used

Java (Core logic and Dijkstra's Algorithm)

Java Swing (GUI Framework)

HashMap for graph representation

PriorityQueue for efficient shortest path computation

## How to Run

Ensure you have Java 8 or later installed on your system.

Compile the Java files:

javac project/SimpleFlightRouteTracker.java project/flightGUI.java

Run the GUI application:

java project.flightGUI

## Code Structure

SimpleFlightRouteTracker.java: Implements the graph-based flight route system and shortest path algorithm.

flightGUI.java: Provides a graphical user interface (GUI) for users to interact with the system.

## Usage

Start the application.

Add airports to the system.

Define flight routes by specifying distances.

Input a start and destination airport.

Get the shortest route with travel details.

## Future Enhancements

Implement real-time flight data integration

Optimize route calculation for different priorities (fastest vs. cheapest flights)

Store flight route data in a database for persistence

## Author

Rabbia Saleh

