package cl.moriahdp.church.sermon.modelObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class SermonModelObject implements Serializable {


    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("pastor_name")
    @Expose
    private String pastorName;

    @SerializedName("video_url")
    @Expose
    private String videoUrl;

    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("created")
    @Expose
    private String createdAt;

    public SermonModelObject() {

    }

    public SermonModelObject(String title, String description, String pastorName, String videoUrl, String imageUrl, String date, String createdAt) {
        this.title = title;
        this.description = description;
        this.pastorName = pastorName;
        this.videoUrl = videoUrl;
        this.imageUrl = imageUrl;
        this.date = date;
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPastorName() {
        return pastorName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDate() {
        return date;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
