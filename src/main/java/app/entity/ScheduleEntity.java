package app.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by zhujay on 16/6/19.
 */
@Entity
@Table(name = "schedule", schema = "traveller", catalog = "")
public class ScheduleEntity {
    @Id
    @GeneratedValue(generator = "scheduleIdGenerator")
    @GenericGenerator(name = "scheduleIdGenerator",strategy = "uuid2")
    @Column(name = "id")
    private String id;
    private String destination;
    private String scheduleDate;
    private String imageUrl;
    private String creatorId;

    public ScheduleEntity() {
    }

    public ScheduleEntity(String destination, String scheduleDate, String imageUrl, String creatorId) {

        this.destination = destination;
        this.scheduleDate = scheduleDate;
        this.imageUrl = imageUrl;
        this.creatorId = creatorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "destination")
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Basic
    @Column(name = "schedule_date")
    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    @Basic
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleEntity that = (ScheduleEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (destination != null ? !destination.equals(that.destination) : that.destination != null) return false;
        if (scheduleDate != null ? !scheduleDate.equals(that.scheduleDate) : that.scheduleDate != null) return false;
        if (imageUrl != null ? !imageUrl.equals(that.imageUrl) : that.imageUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (scheduleDate != null ? scheduleDate.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "creator_id")
    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
}
