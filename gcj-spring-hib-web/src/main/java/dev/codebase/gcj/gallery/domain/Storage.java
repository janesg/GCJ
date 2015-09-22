package dev.codebase.gcj.gallery.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("STORAGE")          // Value of "DTYPE" discriminator column for this class of Art Data
public class Storage extends ArtData {

    public static final String BASE_URL = "/images/storage/";

    public Storage() {
    }

    public Storage(byte[] picture) {
        this.setPicture(picture);
    }

    @Transient
    public String getUrl() {
        return BASE_URL + this.getId();
    }

}
