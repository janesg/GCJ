package dev.codebase.gcj.gallery.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


@Entity
public class ArtEntity implements Serializable, DomainObject {

    private Long id;
    private Integer version;
    
    private String title;
    private String subTitle;
    private Date uploadedDate;
    private String displayDate;
    private Integer width;
    private Integer height;
    private String media;
    private String description;
    private String caption;
    private String imagePath;
    private Gallery galleryPicture;
    private Storage storagePicture;
    private Thumbnail thumbnailPicture;
    private Set<Category> categories = new HashSet<Category>();
    private Set<Comment> comments = new HashSet<Comment>();

    public ArtEntity() {
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    // Added mappedBy hint here to indicate that the inverse side of this
    // relationship is referenced by the artEntities property in the Category class. 
    // For bidirectional, many-to-many associations, we need to specify which side of 
    // the relationship is the owner. By adding the mappedBy attribute to the Category 
    // getter, we are asserting that the Category class owns the relationship.
    @ManyToMany(mappedBy = "artEntities")
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public boolean addCommentToArt(Comment comment) {
        // Relationship is bi-directional so setup both sides
        comment.setCommentedArt(this);
        return this.getComments().add(comment);
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getSubTitle() {
        return subTitle;
    }
    
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUploadedDate() {
        return uploadedDate;
    }
    
    public void setUploadedDate(Date uploadedDate) {
        this.uploadedDate = uploadedDate;
    }
    
    public String getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(String displayDate) {
        this.displayDate = displayDate;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }
    
    public void setHeight(Integer height) {
        this.height = height;
    }
    
    public String getMedia() {
        return media;
    }
    
    public void setMedia(String media) {
        this.media = media;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCaption() {
        return caption;
    }
    
    public void setCaption(String caption) {
        this.caption = caption;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    public Gallery getGalleryPicture() {
        return galleryPicture;
    }
    
    public void setGalleryPicture(Gallery galleryPicture) {
        this.galleryPicture = galleryPicture;
    }
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    public Storage getStoragePicture() {
        return storagePicture;
    }
    
    public void setStoragePicture(Storage storagePicture) {
        this.storagePicture = storagePicture;
    }
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    public Thumbnail getThumbnailPicture() {
        return thumbnailPicture;
    }

    public void setThumbnailPicture(Thumbnail thumbnailPicture) {
        this.thumbnailPicture = thumbnailPicture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        final ArtEntity artEntity = (ArtEntity) o;

        if (description != null ? !description.equals(artEntity.description) : artEntity.description != null)
            return false;
        if (displayDate != null ? !displayDate.equals(artEntity.displayDate) : artEntity.displayDate != null)
            return false;
        if (height != null ? !height.equals(artEntity.height) : artEntity.height != null)
            return false;
        if (subTitle != null ? !subTitle.equals(artEntity.subTitle) : artEntity.subTitle != null)
            return false;
        if (title != null ? !title.equals(artEntity.title) : artEntity.title != null)
            return false;
        if (uploadedDate != null ? !uploadedDate.equals(artEntity.uploadedDate) : artEntity.uploadedDate != null)
            return false;
        if (width != null ? !width.equals(artEntity.width) : artEntity.width != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = (title != null ? title.hashCode() : 0);
        result = 29 * result + (subTitle != null ? subTitle.hashCode() : 0);
        result = 29 * result + (uploadedDate != null ? uploadedDate.hashCode() : 0);
        result = 29 * result + (displayDate != null ? displayDate.hashCode() : 0);
        result = 29 * result + (width != null ? width.hashCode() : 0);
        result = 29 * result + (height != null ? height.hashCode() : 0);
        result = 29 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
    
}
