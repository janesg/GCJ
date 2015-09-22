package dev.codebase.gcj.gallery.dao.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;

import dev.codebase.gcj.gallery.dao.ArtDataDao;
import dev.codebase.gcj.gallery.domain.ArtData;
import dev.codebase.gcj.gallery.domain.ArtEntity;

@Repository("artDataDao")
public class ArtDataDaoJpa extends GenericDaoJpa<ArtData> implements ArtDataDao {

    private static final String STORAGE_FORMAT = "STORAGE_FORMAT";
    private static final String GALLERY_FORMAT = "GALLERY_FORMAT";
    private static final String THUMBNAIL_FORMAT = "THUMBNAIL_FORMAT";

    public ArtDataDaoJpa() {
        super(ArtData.class);
    }

    // gets (loading proxy) appropriate image format from artEntity (such as thumbnail, etc.)
    public ArtData getArtDataFromEntity(ArtEntity entity, String imageFormat) throws DataAccessException {
        if (imageFormat.equalsIgnoreCase(STORAGE_FORMAT)) {
            return entity.getStoragePicture();
        } else if (imageFormat.equalsIgnoreCase(GALLERY_FORMAT)) {
            return entity.getStoragePicture();
        } else if (imageFormat.equalsIgnoreCase(THUMBNAIL_FORMAT)) {
            return entity.getThumbnailPicture();
        }
        
        return null;
    }
}
