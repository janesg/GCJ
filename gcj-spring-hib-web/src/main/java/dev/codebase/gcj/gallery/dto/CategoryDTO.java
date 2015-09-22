package dev.codebase.gcj.gallery.dto;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="category")
public class CategoryDTO {

    private Long id;
    private String categoryName;
    private String categoryDescription;
    private Set<ArtEntityDTO> artEntities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Set<ArtEntityDTO> getArtEntities() {
        return artEntities;
    }

    public void setArtEntities(Set<ArtEntityDTO> artEntities) {
        this.artEntities = artEntities;
    }
}
