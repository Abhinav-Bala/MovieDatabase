/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import object.Movie;

public class MoviePopupController implements Initializable {
    @FXML Label titleLabel;
    @FXML Label producerLabel;
    @FXML Label directorLabel;
    @FXML Label ratingLabel;
    @FXML Label castLabel;
    @FXML ImageView poster;
    
    public void transferMovieInfo(Movie movieSearch){
       Movie movie = movieSearch;
       System.out.println(movie);
       titleLabel.setText(movie.getTitle());
       producerLabel.setText(movie.getProducer());
       directorLabel.setText(movie.getDirector());
       ratingLabel.setText(movie.getRating());
       castLabel.setText(movie.getCast());

       Image image = new Image(movie.getPoster());
       poster.setImage(image);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
