package project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class flightGUI extends Application {
    private SimpleFlightRouteTracker tracker; // Declare a tracker object to handle flight routes

    @Override
    public void start(Stage primaryStage) {
        tracker = new SimpleFlightRouteTracker(); // Initialize the SimpleFlightRouteTracker object
        initializeData(); // Populate the tracker with airport data and routes

        primaryStage.setTitle("Flight Route Tracker"); // Set the title of the application window

        // Labels for the departure and destination airport fields
        Label departureLabel = new Label("Departure Airport:");
        Label destinationLabel = new Label("Destination Airport:");

        // ComboBoxes to allow the user to select airports
        ComboBox<String> departureComboBox = new ComboBox<>();
        ComboBox<String> destinationComboBox = new ComboBox<>();
        departureComboBox.getItems().addAll(tracker.getAirports()); // Add airports to departure ComboBox
        destinationComboBox.getItems().addAll(tracker.getAirports()); // Add airports to destination ComboBox

        // Button to find the shortest route
        Button findRouteButton = new Button("Find Shortest Route");

        // TextArea to display the result
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false); // The result area is not editable

        // Action when the "Find Shortest Route" button is clicked
        findRouteButton.setOnAction(e -> {
            String startAirport = departureComboBox.getValue(); // Get selected departure airport
            String endAirport = destinationComboBox.getValue(); // Get selected destination airport

            // If both airports are selected, find and display the shortest route
            if (startAirport != null && endAirport != null) {
                tracker.findShortestRoute(startAirport, endAirport); // Find the shortest route using the tracker
                resultArea.setText(tracker.getRouteResult()); // Display the result in the TextArea
            } else {
                resultArea.setText("Please select both departure and destination airports."); // If not both selected, show an error message
            }
        });

        // Layout for the UI
        GridPane grid = new GridPane();
        grid.setHgap(10); // Horizontal gap between elements
        grid.setVgap(10); // Vertical gap between elements
        grid.setPadding(new Insets(20)); // Padding around the grid

        // Add UI elements to the grid
        grid.add(departureLabel, 0, 0); // Add departure label to the grid
        grid.add(departureComboBox, 1, 0); // Add departure ComboBox to the grid
        grid.add(destinationLabel, 0, 1); // Add destination label to the grid
        grid.add(destinationComboBox, 1, 1); // Add destination ComboBox to the grid
        grid.add(findRouteButton, 1, 2); // Add the "Find Shortest Route" button to the grid
        grid.add(resultArea, 0, 3, 2, 1); // Add the result area (TextArea) to the grid, spanning two columns

        // Create the scene and show the stage (the main window of the application)
        Scene scene = new Scene(grid, 400, 300); // Create the scene with the specified grid layout
        primaryStage.setScene(scene); // Set the scene for the primary stage
        primaryStage.show(); // Show the stage (the window)
    }

    // Method to initialize the airports and routes in the tracker
    private void initializeData() {
        // Add airports to the tracker
        tracker.addAirport("Dubai");
        tracker.addAirport("Karachi");
        tracker.addAirport("London");
        tracker.addAirport("New York");
        tracker.addAirport("Singapore");
        tracker.addAirport("Tokyo");
        tracker.addAirport("Sydney");
        tracker.addAirport("Berlin");
        tracker.addAirport("Toronto");
        tracker.addAirport("Beijing");

        // Adding routes between airports with realistic distances (in kilometers)
        tracker.addRoute("Dubai", "Karachi", 65);
        tracker.addRoute("Dubai", "London", 420);
        tracker.addRoute("Dubai", "New York", 550);
        tracker.addRoute("Dubai", "Singapore", 600);
        tracker.addRoute("Dubai", "Tokyo", 700);
        tracker.addRoute("Dubai", "Sydney", 960);
        tracker.addRoute("Dubai", "Berlin", 510);
        tracker.addRoute("Dubai", "Toronto", 600);
        tracker.addRoute("Dubai", "Beijing", 60);

        tracker.addRoute("Karachi", "London", 420);
        tracker.addRoute("Karachi", "New York", 550);
        tracker.addRoute("Karachi", "Singapore", 430);
        tracker.addRoute("Karachi", "Tokyo", 600);
        tracker.addRoute("Karachi", "Sydney", 750);
        tracker.addRoute("Karachi", "Berlin", 800);
        tracker.addRoute("Karachi", "Toronto", 500);
        tracker.addRoute("Karachi", "Beijing", 400);

        tracker.addRoute("London", "New York", 550);
        tracker.addRoute("London", "Singapore", 700);
        tracker.addRoute("London", "Tokyo", 850);
        tracker.addRoute("London", "Sydney", 960);
        tracker.addRoute("London", "Berlin", 900);
        tracker.addRoute("London", "Toronto", 550);
        tracker.addRoute("London", "Beijing", 800);

        tracker.addRoute("New York", "Singapore", 880);
        tracker.addRoute("New York", "Tokyo", 950);
        tracker.addRoute("New York", "Sydney", 870);
        tracker.addRoute("New York", "Berlin", 1000);
        tracker.addRoute("New York", "Toronto", 700);
        tracker.addRoute("New York", "Beijing", 850);

        tracker.addRoute("Singapore", "Tokyo", 180);
        tracker.addRoute("Singapore", "Sydney", 450);
        tracker.addRoute("Singapore", "Berlin", 700);
        tracker.addRoute("Singapore", "Toronto", 500);
        tracker.addRoute("Singapore", "Beijing", 600);

        tracker.addRoute("Tokyo", "Sydney", 770);
        tracker.addRoute("Tokyo", "Berlin", 800);
        tracker.addRoute("Tokyo", "Toronto", 280);
        tracker.addRoute("Tokyo", "Beijing", 500);

        tracker.addRoute("Sydney", "Berlin", 510);
        tracker.addRoute("Sydney", "Toronto", 250);
        tracker.addRoute("Sydney", "Beijing", 450);

        tracker.addRoute("Berlin", "Toronto", 900);
        tracker.addRoute("Berlin", "Beijing", 700);

        tracker.addRoute("Toronto", "Beijing", 400);
    }

    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}
