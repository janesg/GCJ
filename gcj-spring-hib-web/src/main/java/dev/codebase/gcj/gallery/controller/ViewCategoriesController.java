package dev.codebase.gcj.gallery.controller;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import dev.codebase.gcj.gallery.util.ViewUtil;
import dev.codebase.gcj.gallery.domain.Category;
import dev.codebase.gcj.gallery.service.ArtworkFacade;

public class ViewCategoriesController implements Controller {

    private ArtworkFacade artworkFacade;
    private String successView;

    @SuppressWarnings("unchecked")
    public ModelAndView handleRequest(HttpServletRequest request, 
                                      HttpServletResponse response) throws Exception {
        
        List<Category> categories = artworkFacade.getCategories();
        
        if (categories == null || categories.size() <= 0) {
            initCategories();
            categories = artworkFacade.getCategories();
        }
        
        Map model = new HashMap();
        model.put("categories", categories);
        model.put("posStyle", ViewUtil.buildAlternator(new String[] { "categoryleft", "categoryright" }, 6));
        
        return new ModelAndView(this.getSuccessView(), "art", model);
    }

    private void initCategories() {
        initCategory("Music", "Artwork involving Music");
        initCategory("Landscapes", "Landscapes");
        initCategory("Figures", "Figures");
        initCategory("Compositions and Collages", "Compositions and Collages");
        initCategory("Travel Sketches", "Travel Sketches");
        initCategory("Drawings", "Drawings");
    }

    private void initCategory(String name, String description) {
        Category cat = new Category();
        cat.setCategoryName(name);
        cat.setCategoryDescription(description);
        
        artworkFacade.saveCategory(cat);
    }

    public ArtworkFacade getArtworkFacade() {
        return artworkFacade;
    }

    public void setArtworkFacade(ArtworkFacade artworkFacade) {
        this.artworkFacade = artworkFacade;
    }

    public String getSuccessView() {
        return successView;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }

}
