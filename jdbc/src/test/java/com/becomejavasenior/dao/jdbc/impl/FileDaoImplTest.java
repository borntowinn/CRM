package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Comment;
import com.becomejavasenior.File;
import com.becomejavasenior.dao.CommentDao;
import com.becomejavasenior.dao.FileDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.ConnectionFactory;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
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

    @BeforeClass
    public static void setupAndConnection()
    {
        Connection connection = ConnectionFactory.getConnection();
        byte[] bytes = {1, 2, 3, 4};
        fileDao = DaoFactory.getFileDao();
        file = new File();
        file.setFileName("TestFilename");
        file.setCreationDate(LocalDateTime.now());
        file.setFile(bytes);

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void closeConnection()
    {
        fileDao.closeCurrentConnection();
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
        System.out.println(file.getId());
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
