package app.jsonClass;

import java.sql.Date;
import java.util.List;

/**
 * Created by jixiang on 2016/6/17.
 */
public class Post {
    private String id;
    private String title;
    private String creatorId;
    private String locationDesc;
    private double latitude;
    private double longitude;
    private String summary;
    private Date createDate;
    private List<Image> imageURLs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String createrId) {
        this.creatorId = creatorId;
    }

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Image> getImageURLs() {
        return imageURLs;
    }

    public void setImageURLs(List<Image> imageURLs) {
        this.imageURLs = imageURLs;
    }
}
