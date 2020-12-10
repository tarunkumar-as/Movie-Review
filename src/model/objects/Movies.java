package model.objects;

import java.util.List;

public class Movies {

    private int movie_Id;
    private String movie_name;
    private int duration;
    private String certification;
    private String photo_url;
    private List<Actors> actorsList;

    public int getMovie_Id() {
        return movie_Id;
    }

    public void setMovie_Id(int movie_Id) {
        this.movie_Id = movie_Id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public List<Actors> getActorsList() {
        return actorsList;
    }

    public void setActorsList(List<Actors> actorsList) {
        this.actorsList = actorsList;
    }
}
