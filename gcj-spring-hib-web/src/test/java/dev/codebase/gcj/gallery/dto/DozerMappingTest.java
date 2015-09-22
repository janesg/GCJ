package dev.codebase.gcj.gallery.dto;

import static org.junit.Assert.*;

import java.util.Date;

import org.dozer.Mapper;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import dev.codebase.gcj.gallery.domain.Comment;
import dev.codebase.gcj.gallery.domain.ArtEntity;
import dev.codebase.gcj.gallery.dto.ArtEntityDTO;
import dev.codebase.gcj.gallery.domain.Thumbnail;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/application-context.xml"})
public class DozerMappingTest {

    private Mapper dozerMapper;
    private ArtEntity artEntity;

    public Mapper getDozerMapper() {
        return dozerMapper;
    }

    @Autowired
    public void setDozerMapper(Mapper dozerMapper) {
        this.dozerMapper = dozerMapper;
    }

    @Before
    public void preMethodSetup() {

        Comment comment = new Comment();
        comment.setComment("This is a test comment. What a cool picture!");
        comment.setCommentDate(new Date());
        comment.setEmail("test@prospringhibernate.com");
        comment.setFirstName("John");
        comment.setLastName("Doe");
        comment.setTelephone("212-555-5555");

        Thumbnail thumbnail = new Thumbnail();
        thumbnail.setId(1L);

        artEntity = new ArtEntity();
        artEntity.setCaption("caption test");
        artEntity.addCommentToArt(comment);
        artEntity.setDescription("A very cool picture of trees.");
        artEntity.setDisplayDate("October 10th");
        artEntity.setHeight(500);
        artEntity.setWidth(300);
        artEntity.setSubTitle("This is a subtitle for a picture");
        artEntity.setTitle("This is a title of a picture");
        artEntity.setThumbnailPicture(thumbnail);

    }


    @Test
    public void testMappingArtEntity() {
        ArtEntityDTO artEntityDTO = this.getDozerMapper().map(artEntity, ArtEntityDTO.class);
        assertEquals(artEntity.getTitle(), artEntityDTO.getTitle());
        assertTrue(artEntityDTO.getComments().size() > 0);
        assertTrue("thumbnail should be a string value", artEntityDTO.getThumbnailURL().length() > 0);
    }

}
