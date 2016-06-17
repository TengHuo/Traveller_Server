package app.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by zhujay on 16/6/15.
 */
@Entity
@Table(name = "image", schema = "traveller", catalog = "")
@IdClass(ImageEntityPK.class)
public class ImageEntity {
    @Id
    @GeneratedValue(generator = "imageIdGenerator")
    @GenericGenerator(name = "imageIdGenerator",strategy = "uuid2")
    @Column(name = "id")
    private String id;
    private String postId;
    private String imageUrl;

    public ImageEntity(String postId, String imageUrl) {
        this.postId = postId;
        this.imageUrl = imageUrl;
    }

    public ImageEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    @Id
    @Column(name = "post_id")
    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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

        ImageEntity that = (ImageEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (postId != null ? !postId.equals(that.postId) : that.postId != null) return false;
        if (imageUrl != null ? !imageUrl.equals(that.imageUrl) : that.imageUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (postId != null ? postId.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        return result;
    }
}
