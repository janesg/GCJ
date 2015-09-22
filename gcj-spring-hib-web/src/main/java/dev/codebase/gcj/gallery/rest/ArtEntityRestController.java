package dev.codebase.gcj.gallery.rest;

import java.util.List;

import org.dozer.Mapper;

import dev.codebase.gcj.gallery.domain.ArtEntity;
import dev.codebase.gcj.gallery.dto.ArtEntityDTO;
import dev.codebase.gcj.gallery.service.ArtworkFacade;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

public class ArtEntityRestController {

    @Autowired
    private ArtworkFacade artworkFacade;

    @Autowired
    private Mapper dozerMapper;

    //public static final String JAXB_VIEW = "jaxbView";

    @RequestMapping(method=RequestMethod.GET, value="/api/artEntities/{artEntityId}")
    @ResponseBody
    public ArtEntityDTO getArtEntity(@PathVariable("artEntityId") Long id) {
        ArtEntity artEntity = artworkFacade.getArtEntity(id);
        ArtEntityDTO artEntityDTO = null;
        if (artEntity != null) {
            artEntityDTO = this.getDozerMapper().map(artEntity, ArtEntityDTO.class);
        }
        return artEntityDTO;
    }

    @RequestMapping(method=RequestMethod.GET, value="/api/category/{categoryId}/artEntities")
    @ResponseBody()
    public List<ArtEntityDTO> getArtEntities(@PathVariable("categoryId") Long categoryId) {
        List<ArtEntity> artEntities = artworkFacade.getArtworkInCategory(categoryId);
        Class<List<ArtEntityDTO>> classArtEntityDTO = null;
        List<ArtEntityDTO> artEntityDTOs = dozerMapper.map(artEntities, classArtEntityDTO);
        return artEntityDTOs;
    }

    public ArtworkFacade getArtworkFacade() {
        return artworkFacade;
    }

    public void setArtworkFacade(ArtworkFacade artworkFacade) {
        this.artworkFacade = artworkFacade;
    }

    public Mapper getDozerMapper() {
        return dozerMapper;
    }

    public void setDozerMapper(Mapper dozerMapper) {
        this.dozerMapper = dozerMapper;
    }

}
