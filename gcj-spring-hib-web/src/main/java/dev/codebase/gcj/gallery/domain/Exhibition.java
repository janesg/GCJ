package dev.codebase.gcj.gallery.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

//import org.hibernate.search.annotations.*;

@Entity
//@Indexed
public class Exhibition implements Serializable, DomainObject {

    private Long id;
    private Integer version;

    private String exhibitionName;
    private Set<ArtEntity> exhibitionArtWork = new HashSet<ArtEntity>();

    public Exhibition() {
    }

    @Id
    @GeneratedValue
    //@DocumentId
    public final Long getId() {
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

    //@Field(index = Index.UN_TOKENIZED, store = Store.YES)
    public String getExhibitionName() {
        return exhibitionName;
    }

    public void setExhibitionName(String exhibitionName) {
        this.exhibitionName = exhibitionName;
    }

    @ManyToMany
    public Set<ArtEntity> getExhibitionArtWork() {
        return exhibitionArtWork;
    }

    public void setExhibitionArtWork(Set<ArtEntity> exhibitionArtWork) {
        this.exhibitionArtWork = exhibitionArtWork;
    }

    /**
     * This method adds an artEntity to an exhibition It is a unidirectional relationship
     * 
     * @param art
     * @return success
     */
    public boolean addArtToExhibition(ArtEntity art) {
        return this.getExhibitionArtWork().add(art);
    }

}
