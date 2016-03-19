package implementations.entitiesImpl;

import com.becomejavasenior.Company;
import com.becomejavasenior.dao.hibernatedao.CompanyDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class CompanyDaoImpl extends AbstractDaoImpl<Company> implements CompanyDao<Company> {

    private static final Logger LOGGER = Logger.getLogger(CompanyDaoImpl.class);
    @Override
    public Company getByPK(Integer id) {
        Session session = getSession();
        Company company = null;
        try {
            company = (Company) session.load(Company.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        LOGGER.debug(CompanyDaoImpl.class + "get company by PK, getByPK method");
        return company;
    }

    @Override
    public List getAll() {
        return getSession().createCriteria(Company.class).list();
    }
}
