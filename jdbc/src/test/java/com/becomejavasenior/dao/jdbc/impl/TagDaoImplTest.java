package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Tag;
import com.becomejavasenior.dao.TagDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TagDaoImplTest {
    private static Tag tag;
    private List<Tag> tags;
    private static TagDao<Tag> tagDao;

    @BeforeClass
    public static void setupAndConnection()
    {
        tagDao = DaoFactory.getTagDao();
        tag = new Tag();
        tag.setTag("one");
    }

    @Test
    public void createDbEntry_Phase_PhaseFromDb()
    {
        Assert.assertEquals(tag.getTag(), tagDao.create(tag).getTag());
    }

    @Test
    public void getRecordByPK()
    {
        //when
        Integer id = tagDao.create(tag).getId();

        //then
        Assert.assertEquals(tag.getTag(), tagDao.getByPK(id).getTag());
        tagDao.delete(id);
    }

    @Test
    public void getAllRecordsTest()
    {
        //when
        tags = tagDao.getAll();

        //then
        Assert.assertNotNull(tags);
        Assert.assertTrue(tags.size() > 0);
    }

    @Test(expected=PersistException.class)
    public void deleteRecord()
    {
        //when
        Integer id = tagDao.create(tag).getId();
        tagDao.delete(id);

        //then -> exception must be thrown == the record was successfully deleted and can't be accessed anymore
        tagDao.getByPK(id);
    }
}