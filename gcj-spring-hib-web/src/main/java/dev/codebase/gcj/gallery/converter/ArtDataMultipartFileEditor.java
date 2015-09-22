package dev.codebase.gcj.gallery.converter;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.propertyeditors.ByteArrayPropertyEditor;

import dev.codebase.gcj.gallery.domain.ArtData;
import dev.codebase.gcj.gallery.domain.Storage;

public class ArtDataMultipartFileEditor extends ByteArrayPropertyEditor {

    protected final Log logger = LogFactory.getLog(getClass());

    public void setValue(Object value) {
        byte[] fileData = null;
        if (value instanceof MultipartFile) {
            MultipartFile multipartFile = (MultipartFile) value;

            try {
                fileData = multipartFile.getBytes();
            } catch (IOException ex) {
                logger.error("Cannot read contents of multipart file", ex);
                throw new IllegalArgumentException("Cannot read contents of multipart file: " + ex.getMessage());
            }
        } else if (value instanceof byte[]) {
            fileData = (byte[]) value;
        } else {
            fileData = (value != null ? value.toString().getBytes() : null);
        }
        
        Storage artData = new Storage(fileData);
        super.setValue(artData);
    }

    public String getAsText() {
        byte[] value = ((ArtData) getValue()).getPicture();
        return (value != null ? new String(value) : "");
    }

}
