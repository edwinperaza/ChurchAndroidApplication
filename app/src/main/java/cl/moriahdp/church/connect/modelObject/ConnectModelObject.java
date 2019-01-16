package cl.moriahdp.church.connect.modelObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ConnectModelObject implements Serializable {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("url_more_info")
    @Expose
    private String url;

    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    public ConnectModelObject(String title, String url, String imageUrl) {
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
