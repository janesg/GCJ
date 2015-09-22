package dev.codebase.gcj.gallery.dao.jpa;

import org.springframework.stereotype.Repository;

import dev.codebase.gcj.gallery.dao.ExhibitionDao;
import dev.codebase.gcj.gallery.domain.Exhibition;

@Repository("exhibitionDao")
public class ExhibitionDaoJpa extends GenericDaoJpa<Exhibition> implements ExhibitionDao {

    public ExhibitionDaoJpa() {
        super(Exhibition.class);
    }
}
