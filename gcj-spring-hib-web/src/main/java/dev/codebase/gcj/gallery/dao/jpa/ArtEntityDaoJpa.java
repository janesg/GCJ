package dev.codebase.gcj.gallery.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
import org.apache.lucene.util.Version;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import org.hibernate.search.jpa.Search;
import org.hibernate.search.jpa.FullTextEntityManager;
*/

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import dev.codebase.gcj.gallery.dao.ArtEntityDao;
import dev.codebase.gcj.gallery.domain.ArtEntity;

@Repository("artEntityDao")
public class ArtEntityDaoJpa extends GenericDaoJpa<ArtEntity> implements ArtEntityDao {

    protected Log log = LogFactory.getLog(ArtEntityDaoJpa.class);

    public ArtEntityDaoJpa() {
        super(ArtEntity.class);
    }

    public ArtEntity getArtEntityByTitle(String title) throws DataAccessException {
        String jpql = "select art from ArtEntity art where art.title = :title";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("title", title);
        return (ArtEntity) query.getSingleResult();
    }

    public List<ArtEntity> getArtInExhibition(Long exhibitionId) throws DataAccessException {
        String jpql = "select art from Exhibition exhibits join exhibits.exhibitionArtWork art where exhibits.id = :id";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("id", exhibitionId);
        return query.getResultList();
    }

    public List<ArtEntity> getArtworkInCategory(Long catId) throws DataAccessException {
        String jpql = "select art from Category cat join cat.artEntities art where cat.id = :catId";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("catId", catId);
        return query.getResultList();
    }

/*    
    public void indexArtEntity(ArtEntity artEntity) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.index(artEntity);
    }


    public List<ArtEntity> searchForArtEntitiesByTerms(String searchTerms, Integer startIndex, Integer maxResults) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        String[] fieldsToMatch = new String[] {"title", "subTitle", "media", "description", "caption"};
        //String[] fieldsToMatch = new String[] {"title"};
        QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_29, fieldsToMatch, new StandardAnalyzer(Version.LUCENE_29));
        org.apache.lucene.search.Query luceneQuery = null;
        try {
            luceneQuery = parser.parse(searchTerms);
        } catch (ParseException e) {
            log.error("Error parsing lucene query: " + searchTerms);
        }
        Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, ArtEntity.class);
        if (startIndex != null && maxResults != null) {
            jpaQuery.setFirstResult(startIndex);
            jpaQuery.setMaxResults(maxResults);
        }
        List<ArtEntity> artEntities = jpaQuery.getResultList();
        return artEntities;
    }
*/

}
