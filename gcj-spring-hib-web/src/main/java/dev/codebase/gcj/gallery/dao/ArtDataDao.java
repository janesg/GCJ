package dev.codebase.gcj.gallery.dao;

import org.springframework.dao.DataAccessException;

import dev.codebase.gcj.gallery.domain.ArtData;
import dev.codebase.gcj.gallery.domain.ArtEntity;

public interface ArtDataDao extends GenericDao<ArtData> {

    // Gets (loading proxy) appropriate image format from artEntity (such as thumbnail, etc.)
    public ArtData getArtDataFromEntity(ArtEntity entity, String imageFormat) throws DataAccessException;
    
}
