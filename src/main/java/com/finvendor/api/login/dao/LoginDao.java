/**
 *
 */
package com.finvendor.api.login.dao;

import com.finvendor.model.FinVendorUser;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rayulu vemula
 */
@Repository
public class LoginDao {

    private static final Logger logger = LoggerFactory.getLogger(LoginDao.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    /** --------------------------------------------------------------------- */
    /**
     * (non-Javadoc)
     *
     */
    @Transactional
    public FinVendorUser getUserInfoByEmail(String email) {
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(FinVendorUser.class);
            criteria.add(Restrictions.eq("email", email));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (FinVendorUser) criteria.uniqueResult();
    }


}
