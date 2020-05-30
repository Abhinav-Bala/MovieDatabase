/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import object.Movie;

public class HomeController implements Initializable {

    @FXML private TextField movieSearch;
    @FXML TextField listSearch;
    @FXML TableView table;
    @FXML ChoiceBox filterBox;
    TableColumn<Movie, String> titleColumn = new TableColumn<>("Title");
    TableColumn<Movie, String> producerColumn = new TableColumn<>("Producer");
    TableColumn<Movie, String> directorColumn = new TableColumn<>("Director");
    TableColumn<Movie, String> ratingColumn = new TableColumn<>("Rating");
    TableColumn<Movie, String> castColumn = new TableColumn<>("Cast");
    ObservableList<Movie> movies = FXCollections.observableArrayList();
    
    public void readTextFile() {

	File myFile = new File("C:\\Users\\emucd\\Documents\\NetBeansProjects\\MovieDatabase\\src\\controller\\movie.txt"); //Change the string to your location of the text file
	BufferedReader br = null;

	try {
	   
	    FileReader fr = new FileReader(myFile);

	 
	    br = new BufferedReader(fr);

	
	    String line;

	  
	    while ((line = br.readLine()) != null) {
		String[] myStrings = line.split(";");
		if (myStrings.length == 6) {
		    Movie newMovie = new Movie(myStrings[0], myStrings[1], myStrings[2], myStrings[3], myStrings[4], myStrings[5]);
		    movies.add(newMovie);
                    
		} 
	    }
	} 
	catch (FileNotFoundException ex) {
	    System.err.println("File not found");
            System.err.println("Change the text file's location to your pathway");
	} 
	catch (IOException e) {
	    System.err.println("Error occured");
	} 
	finally {

	    try {		
		br.close();
	    } 
	    catch (IOException ex) {
		System.err.println("Error closing BufferedReader");
	    } 
	    catch (NullPointerException e) {
	    }
	}
    }
    
    
    public void findMovie(){
        String titleSearch = movieSearch.getText();
        
                    //Initalize search variable
            Boolean found = false;

            //Check the whole libray
            for (Movie b : movies) {           
            
            if (b.getTitle().equalsIgnoreCase(titleSearch)) {
                     try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/moviePopup.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle(movieSearch.getText() + " Window");
            stage.setScene(new Scene(root));
            stage.show();
            
             
            //Get controller of scene2
            MoviePopupController userCart = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            userCart.transferMovieInfo(b);
            found = true;
 
        } catch (IOException ex) {
            System.err.println(ex);
        }
            }

        }
	
        if (!found) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Search Error");
            alert.setContentText("Oops, this movie is not in our database.");
            alert.showAndWait();
        }
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
       titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
       producerColumn.setCellValueFactory(new PropertyValueFactory<>("producer"));
       directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
       ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
       castColumn.setCellValueFactory(new PropertyValueFactory<>("cast"));
       table.getColumns().addAll(titleColumn, producerColumn, directorColumn, castColumn, ratingColumn);  
       
       //table.setItems(movies);
       readTextFile();
       System.out.println(movies);
       
       FilteredList<Movie> filteredData = new FilteredList<>(movies, b -> true);
           		// 2. Set the filter Predicate whenever the filter changes.
		listSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(movie -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (movie.getTitle().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches title
				} else if (movie.getProducer().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches producer
				}else if (movie.getCast().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches cast
				}else if (movie.getDirector().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches cast
				}else if (String.valueOf(movie.getRating()).contains(lowerCaseFilter))
				     return true;
                                
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Movie> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }    

}
