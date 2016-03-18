package implementations.entitiesImpl;

import com.becomejavasenior.Company;
import com.becomejavasenior.dao.hibernatedao.CompanyDao;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CompanyDaoImpl extends AbstractDaoImpl implements CompanyDao {

    private static final Logger LOGGER = Logger.getLogger(CompanyDaoImpl.class);
    @Override
    public Company getByPK(Integer id) {
        Session session = getSession();
        Company comment = (Company) session.load(Company.class, id);
        LOGGER.debug(CompanyDaoImpl.class);
        commitTransaction(session);
        return comment;
    }

    @Override
    public List getAll() {
        return getSession().createCriteria(Company.class).list();
    }

    @Override
    public Company selectCompanyByContactId(int contactId) {
        Session session = getSession();
        Criteria allCompanies = getSession().createCriteria(Company.class);
        List companies = allCompanies.add(Restrictions.eq("contact_id", contactId)).list();
        if (companies.size() > 1) {
            LOGGER.error("Company has more than one contact. It was returned first company");
        }
        LOGGER.debug(DealDaoImpl.class);
        commitTransaction(session);
        return (Company) companies.get(0);
    }
}
