package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Comment;
import com.becomejavasenior.dao.CommentDao;
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
import java.util.List;

/**
 * Created by Default71721 on 10.02.16.
 */
public class CommentDaoImplTest {

    private static Comment comment;
    private List<Comment> comments;
    private static CommentDao<Comment> commentDao;

    @BeforeClass
    public static void setupAndConnection()
    {
        Connection connection = ConnectionFactory.getConnection();
        connection = ConnectionFactory.getConnection();
        commentDao = DaoFactory.getCommentDao();
        comment = new Comment();
        comment.setComment("Test comment");
        comment.setCreationDate(LocalDateTime.now());

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createDbEntry_Comment_CommentFromDb()
    {
        Assert.assertEquals(comment.getComment(), commentDao.create(comment).getComment());
    }

    @Test
    public void getRecordByPK_Comment_CommentFromDbByPK()
    {
        //when
        Integer id = commentDao.create(comment).getId();
        System.out.println(id);

        //then
        Assert.assertTrue(comment.getComment().equals(commentDao.getByPK(id).getComment()));
    }

    @Test
    public void getAllRecordsTest()
    {
        //when
        comments = commentDao.getAll();

        //then
        Assert.assertNotNull(comments);
        Assert.assertTrue(comments.size() > 0);
    }

    @Test
    public void updateRecord_NewComment_NewCommentExpectedFromDb()
    {
        //when
        Integer id = commentDao.create(comment).getId();
        comment.setComment("newTestCommentWithoutSpaces");
        System.out.println(comment.getId());
        comment.setId(id);
        commentDao.update(comment);

        //then
        Assert.assertEquals(comment.getComment(), commentDao.getByPK(id).getComment());
    }

    @Test(expected=PersistException.class)
    public void deleteRecord_PersistExceptionIsExpected()
    {
        //when
        Integer id = commentDao.create(comment).getId();
        commentDao.delete(id);

        //then -> exception must be thrown == the record was successfully deleted and can't be accessed anymore
        commentDao.getByPK(id);
    }
}
