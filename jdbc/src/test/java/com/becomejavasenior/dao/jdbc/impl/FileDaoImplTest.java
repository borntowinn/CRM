package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.*;
import com.becomejavasenior.dao.FileDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Default71721 on 10.02.16.
 */
public class FileDaoImplTest {
    private static File file;
    private List<File> files;
    private static FileDao<File> fileDao;
    private static Contact contact;
    private static Company company;
    private static Deal deal;
    private static User user;

    @BeforeClass
    public static void setupAndConnection()
    {
        byte[] bytes = {1, 2, 3, 4};
        fileDao = DaoFactory.getFileDao();
        contact = new Contact();
        contact.setId(1);
        company = new Company();
        company.setId(1);
        user = new User();
        user.setId(1);
        deal = new Deal();
        deal.setId(1);

        file = new File();
        file.setFileName("TestFilename");
        file.setCreationDate(LocalDateTime.now());
        file.setFile(bytes);
        file.setContactId(contact);
        file.setDealId(deal);
        file.setCompanyId(company);
        file.setUserId(user);
    }


    @Test
    public void createDbEntry_File_FileFromDb()
    {
        Assert.assertEquals(file.getFileName(), fileDao.create(file).getFileName());
    }

    @Test
    public void getRecordByPK_File_FileFromDbByPK()
    {
        //when
        Integer id = fileDao.create(file).getId();
        System.out.println(id);

        //then
        Assert.assertTrue(file.getFileName().equals(fileDao.getByPK(id).getFileName()));
        Assert.assertTrue(Arrays.equals(file.getFile(), fileDao.getByPK(id).getFile()));
    }

    @Test
    public void getAllRecordsTest()
    {
        //when
        files = fileDao.getAll();

        //then
        Assert.assertNotNull(files);
        Assert.assertTrue(files.size() > 0);
    }

    @Test
    public void updateRecord_NewFile_NewFileExpectedFromDb()
    {
        //when
        Integer id = fileDao.create(file).getId();
        file.setFileName("newTestFileWithoutSpaces");
        file.setId(id);
        fileDao.update(file);

        //then
        Assert.assertEquals(file.getFileName(), fileDao.getByPK(id).getFileName());
    }

    @Test(expected=PersistException.class)
    public void deleteRecord_PersistExceptionIsExpected()
    {
        //when
        Integer id = fileDao.create(file).getId();
        fileDao.delete(id);

        //then -> exception must be thrown == the record was successfully deleted and can't be accessed anymore
        fileDao.getByPK(id);
    }
}
