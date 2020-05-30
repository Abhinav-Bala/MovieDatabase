package object;

import javafx.scene.image.Image;

public class Movie {
    
    private String title;
    String producer;
    String director;
    String rating;
    String cast;
    String poster;
    
    public Movie(String title, String producer, String director, String rating, String cast, String poster){
        this.title = title;
        this.producer = producer;
        this.director = director;
        this.rating = rating;
        this.cast = cast;
        this.poster = poster;
    }
        public Movie(String title, String producer, String director, String rating, String cast){
        this.title = title;
        this.producer = producer;
        this.director = director;
        this.rating = rating;
        this.cast = cast;
        this.poster = " ";
    }
    
    public String getPoster(){
        return this.poster;
    }
    public String getTitle(){
        return this.title;
    }
      public String getProducer(){
        return this.producer;
    }
        public String getDirector(){
        return this.director;
    }
          public String getRating(){
        return this.rating;
    }
            public String getCast(){
        return this.cast;
    }
    public void setTitle(String Title){
        this.title = Title;
    }
    public void setProducer(String Producer){
        this.producer = Producer;
    }
    public void setDirector(String Director){
        this.director = Director;
    }
    public void setRating(String Rating){
        this.rating = Rating;
    }
    public void setCast(String Cast){
        this.cast = Cast;
    }
    
    @Override
    public String toString() {
	return this.title + ";"
	    + this.producer + ";"
	    + this.director + ";"
	    + this.rating + ";"
	    + this.cast+ ";";
    }
}

