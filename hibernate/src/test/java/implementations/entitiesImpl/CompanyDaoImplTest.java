package implementations.entitiesImpl;

import com.becomejavasenior.Company;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Converter;
import java.util.List;

import static org.junit.Assert.*;

@Converter(autoApply = true)
public class CompanyDaoImplTest {
    CompanyDaoImpl dao;
    Company company;

    @Before
    public void before() {
        dao = new CompanyDaoImpl();
        Company company = new Company();
        company.setCompanyName("name");
        company.setAddress("addr");
        company.setEmail("em");
        this.company = dao.create(company);
    }

    @After
    public void after() {
        dao.delete(company);
    }

    @Test
    public void createCompany() {
        Company company = new Company();
        company.setCompanyName("name");
        company.setAddress("addr");
        company.setEmail("em");
        Company check
                = dao.create(company);
        assertSame("addr", check.getAddress());
        assertSame("em", check.getEmail());
    }

    @Test
    public void testGetByPK() throws Exception {
        Integer id = company.getId();
        Company check2 = dao.getByPK(id);
        assertSame(check2.getId(), id);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Company> companies = dao.getAll();
        int size = companies.size();
        assertTrue(size > 0);
        createCompany();
        assertTrue(size < dao.getAll().size());
    }

    @Test
    public void testUpdate() {
        company.setWebsite("newWebsite");
        dao.update(company);
        assertEquals("newWebsite", company.getWebsite());
    }
}