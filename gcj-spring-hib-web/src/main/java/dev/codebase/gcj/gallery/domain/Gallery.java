package dev.codebase.gcj.gallery.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("GALLERY")            // Value of "DTYPE" discriminator column for this class of Art Data
public class Gallery extends ArtData {

    public static final String BASE_URL = "/images/gallery/";

    public Gallery() {
    }

    public Gallery(byte[] picture) {
        this.setPicture(picture);
    }

    @Transient
    public String getUrl() {
        return BASE_URL + this.getId();
    }

}
