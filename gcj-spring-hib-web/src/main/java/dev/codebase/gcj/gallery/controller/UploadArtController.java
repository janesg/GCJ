package dev.codebase.gcj.gallery.controller;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.Errors;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import dev.codebase.gcj.gallery.domain.*;
import dev.codebase.gcj.gallery.service.ArtworkFacade;
import dev.codebase.gcj.gallery.converter.ImageHandler;
import dev.codebase.gcj.gallery.converter.ArtDataMultipartFileEditor;

@Controller
public class UploadArtController {

    private ArtworkFacade artworkFacade;
    private String successView;

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        Set<Category> selectedCategories = getSelectedCategories(request);
        ArtEntity artEntity = (ArtEntity) command;

        ImageHandler imageHandler = new ImageHandler();

        byte[] thumbnailData = imageHandler.convertToThumb(artEntity.getStoragePicture().getPicture());
        byte[] galleryData = imageHandler.convertToGallery(artEntity.getStoragePicture().getPicture());
        
        artEntity.setThumbnailPicture(new Thumbnail(thumbnailData));
        artEntity.setGalleryPicture(new Gallery(galleryData));
        artworkFacade.saveArtEntity(artEntity);
        
        for (Category category : selectedCategories) {
            category.addArtToCategory(artEntity);
            artworkFacade.saveCategory(category);
        }
        
        artworkFacade.saveArtEntity(artEntity);
        
        return new ModelAndView(successView);
    }

    private Set<Category> getSelectedCategories(HttpServletRequest request) {
        
        Set<Category> selectedCats = new HashSet<Category>();
        List<Category> availCats = artworkFacade.getCategories();
        
        for (Category curCat : availCats) {
            if (request.getParameterMap().containsKey(curCat.getCategoryName())) {
                // category has been selected
                selectedCats.add(curCat);
            }
        }
        
        return selectedCats;
    }

    protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws Exception {
        
        List<Category> categories = artworkFacade.getCategories();
        List<Exhibition> exhibits = artworkFacade.getExhibitions();
        
        Map refData = new HashMap();
        refData.put("categories", categories);
        refData.put("exhibits", exhibits);
        
        return refData;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        binder.registerCustomEditor(Storage.class, new ArtDataMultipartFileEditor());
        DateFormat dateStyle = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateStyle, true));
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
