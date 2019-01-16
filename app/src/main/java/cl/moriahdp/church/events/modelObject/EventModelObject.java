package cl.moriahdp.church.events.modelObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EventModelObject implements Serializable {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    @SerializedName("date_start")
    @Expose
    private String dateInit;

    @SerializedName("date_end")
    @Expose
    private String dateEnd;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("url_more_info")
    @Expose
    private String urlMoreInfo;

    @SerializedName("created")
    @Expose
    private String createdAt;

    public EventModelObject(String title, String description, String imageUrl, String dateInit, String dateEnd, String address, String urlMoreInfo, String createdAt) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.dateInit = dateInit;
        this.dateEnd = dateEnd;
        this.address = address;
        this.urlMoreInfo = urlMoreInfo;
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDateInit() {
        return dateInit;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getAddress() {
        return address;
    }

    public String getUrlMoreInfo() {
        return urlMoreInfo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
