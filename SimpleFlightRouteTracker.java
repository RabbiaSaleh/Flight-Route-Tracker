package project;

import java.util.*;

public class SimpleFlightRouteTracker {
    private Map<String, Map<String, Integer>> graph; // Adjacency list for graph representation (each airport with its neighbors and distances)
    private String routeResult; // String to store the result of the shortest path calculation

    // Constructor to initialize the graph and routeResult
    public SimpleFlightRouteTracker() {
        graph = new HashMap<>(); // Initialize the graph as a HashMap
        routeResult = ""; // Initialize the route result as an empty string
    }

    // Method to add an airport to the graph (node)
    public void addAirport(String name) {
        // If the airport does not already exist in the graph, add it with an empty adjacency list
        graph.putIfAbsent(name, new HashMap<>());
    }

    // Method to add a flight route (edge) between two airports with a given distance
    public void addRoute(String source, String destination, int distanceInKilometers) {
        // Ensure both airports exist in the graph
        graph.putIfAbsent(source, new HashMap<>());
        graph.putIfAbsent(destination, new HashMap<>());
        
        // Add the distance between source and destination in both directions (bidirectional routes)
        graph.get(source).put(destination, distanceInKilometers);
        graph.get(destination).put(source, distanceInKilometers); // Assuming bidirectional routes
    }

    // Method to get all airports available in the graph
    public Set<String> getAirports() {
        return graph.keySet(); // Return the set of airport names (keys in the graph)
    }

    // Method to find the shortest route from a start airport to an end airport using Dijkstra's algorithm
    public void findShortestRoute(String start, String end) {
        // Check if the airports exist in the graph
        if (!graph.containsKey(start) || !graph.containsKey(end)) {
            routeResult = "Invalid airport names."; // If airports do not exist, return an error message
            return;
        }

        // Custom class to store distance and number of connections for each airport
        class PathInfo {
            int distance; // Store the distance to reach this airport
            int connections; // Store the number of connections (hops) taken to reach this airport

            PathInfo(int distance, int connections) {
                this.distance = distance; // Initialize distance
                this.connections = connections; // Initialize number of connections
            }
        }

        // Maps to track distances and previous airports during the search
        Map<String, PathInfo> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(a -> distances.get(a).distance));

        // Initialize distances for all airports (set to infinity for unvisited airports)
        for (String airport : graph.keySet()) {
            distances.put(airport, new PathInfo(Integer.MAX_VALUE, Integer.MAX_VALUE)); // Set initial distance to max value
        }
        distances.put(start, new PathInfo(0, 0)); // The distance from start airport to itself is 0
        pq.add(start); // Add the start airport to the priority queue

        // Main loop to explore the graph
        while (!pq.isEmpty()) {
            String current = pq.poll(); // Get the airport with the smallest known distance
            PathInfo currentInfo = distances.get(current); // Get the distance info for the current airport

            // Explore the neighbors (connected airports) of the current airport
            for (Map.Entry<String, Integer> neighbor : graph.get(current).entrySet()) {
                String nextAirport = neighbor.getKey(); // Neighbor airport
                int newDist = currentInfo.distance + neighbor.getValue(); // Calculate the new distance
                int newConnections = currentInfo.connections + 1; // Increase the number of connections

                // If the new path is shorter or the same distance with fewer connections, update it
                if (newDist < distances.get(nextAirport).distance || 
                    (newDist == distances.get(nextAirport).distance && newConnections < distances.get(nextAirport).connections)) {
                    distances.put(nextAirport, new PathInfo(newDist, newConnections)); // Update the distance and connections
                    previous.put(nextAirport, current); // Update the previous airport on this path
                    pq.add(nextAirport); // Add the neighboring airport to the priority queue for further exploration
                }
            }
        }

        // Check if a valid path exists to the end airport
        if (distances.get(end).distance == Integer.MAX_VALUE) {
            routeResult = "No path exists between " + start + " and " + end + "."; // No path found
        } else {
            List<String> path = new ArrayList<>(); // List to store the path from start to end
            buildPath(previous, end, path); // Reconstruct the path by following the previous airports
            int timeInMinutes = (int) Math.ceil(distances.get(end).distance / 800.0 * 60); // Calculate travel time assuming 800 km/h speed
            routeResult = "Shortest Time: " + timeInMinutes + " minutes\nPath: " + String.join(" -> ", path) + "\nConnections: " + distances.get(end).connections;
        }
    }

    // Helper method to reconstruct the path from the previous map
    private void buildPath(Map<String, String> previous, String current, List<String> path) {
        if (current == null) return; // Base case for recursion
        buildPath(previous, previous.get(current), path); // Recursively build the path
        path.add(current); // Add the current airport to the path
    }

    // Method to get the route result (shortest path information)
    public String getRouteResult() {
        return routeResult; // Return the route result string
    }
}
