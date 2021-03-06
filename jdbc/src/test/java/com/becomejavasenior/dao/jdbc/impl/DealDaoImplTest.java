package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.*;
import com.becomejavasenior.dao.DealDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import org.junit.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Default71721 on 29.01.16.
 */
public class DealDaoImplTest {
    private static Deal deal;
    private static Contact contact;
    private static Company company;
    private static User user;
    private static Phase phase;
    private List<Deal> deals;
    private static DealDao<Deal> dealDao;

    @BeforeClass
    public static void setupAndConnection()
    {
        dealDao = DaoFactory.getDealDao();
        deal = new Deal();
        company = new Company();
        contact = new Contact();
        user = new User();
        phase = new Phase();
        company.setId(1);
        contact.setId(1);
        phase.setId(1);
        user.setId(1);
        deal.setContact(contact);
        deal.setCompany(company);
        deal.setDeleted(false);
        deal.setCreationDate(LocalDateTime.now());
        deal.setBudget(new BigDecimal("123.00"));
        deal.setCreatedBy(user);
        deal.setResponsible(user);
        deal.setPhase(phase);
        deal.setDealName("testing name");

    }


    @Test
    public void createDbEntry_LocalDeal_DealFromDb()
    {
        //when
        Deal localDeal = dealDao.create(deal);

        //then
        Assert.assertEquals(deal.getCreationDate(), localDeal.getCreationDate());
        Assert.assertEquals(deal.getBudget(), localDeal.getBudget());
        Assert.assertEquals(deal.getPhase().getId(), localDeal.getPhase().getId());
        Assert.assertEquals(deal.getCompany().getId(), localDeal.getCompany().getId());
        Assert.assertEquals(deal.getContact().getId(), localDeal.getContact().getId());
        Assert.assertEquals(deal.getDeleted(), localDeal.getDeleted());
        Assert.assertEquals(deal.getResponsible().getId(), localDeal.getResponsible().getId());
        Assert.assertEquals(deal.getCreatedBy().getId(), localDeal.getCreatedBy().getId());
        Assert.assertEquals(deal.getDealName(), localDeal.getDealName());
    }

    @Test
    public void getByPK_Deal_DealFromDbByPK()
    {
        //when
        Integer id = dealDao.create(deal).getId();

        //then
        Assert.assertEquals(deal.getCreationDate(), dealDao.getByPK(id).getCreationDate());
        Assert.assertEquals(deal.getBudget(), dealDao.getByPK(id).getBudget());
        Assert.assertEquals(deal.getPhase().getId(), dealDao.getByPK(id).getPhase().getId());
        Assert.assertEquals(deal.getCompany().getId(), dealDao.getByPK(id).getCompany().getId());
        Assert.assertEquals(deal.getContact().getId(), dealDao.getByPK(id).getContact().getId());
        Assert.assertEquals(deal.getDeleted(), dealDao.getByPK(id).getDeleted());
        Assert.assertEquals(deal.getResponsible().getId(), dealDao.getByPK(id).getResponsible().getId());
        Assert.assertEquals(deal.getCreatedBy().getId(), dealDao.getByPK(id).getCreatedBy().getId());
        Assert.assertEquals(deal.getDealName(), dealDao.getByPK(id).getDealName());
    }

    @Test
    public void getAllRecordsTest()
    {
        //when
        deals = dealDao.getAll();

        //then
        Assert.assertNotNull(deals);
        Assert.assertTrue(deals.size() > 0);
    }

    @Test
    public void updateRecord_Budget789_Returned789()
    {
        //when
        Integer id = dealDao.create(deal).getId();
        deal.setBudget(new BigDecimal("789.00"));
        deal.setId(id);
        dealDao.update(deal);

        //then
        Assert.assertEquals(deal.getBudget(), dealDao.getByPK(id).getBudget());
    }

    @Test(expected=PersistException.class)
    public void deleteRecord_PersistExceptionIsExpected()
    {
        //when
        Integer id = dealDao.create(deal).getId();
        dealDao.delete(id);

        //then -> exception must be thrown == the record was successfully deleted and can't be accessed anymore
        dealDao.getByPK(id);
    }
}
