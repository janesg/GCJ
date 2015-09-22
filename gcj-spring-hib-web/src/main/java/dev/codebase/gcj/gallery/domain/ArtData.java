package dev.codebase.gcj.gallery.domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Version;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)               // All Art Data in single table
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)  // Column that indicates type: "DTYPE" (default)
@DiscriminatorValue("GENERIC")                                      // Value of "DTYPE" for this class of Art Data
public class ArtData implements Serializable, DomainObject {

    private Long id;
    private Integer version;
    
    private byte[] picture;

    public ArtData() {
    }

    public ArtData(byte[] picture) {
        this.picture = picture;
    }

    @Id
    @GeneratedValue
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

    @Lob
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

}
