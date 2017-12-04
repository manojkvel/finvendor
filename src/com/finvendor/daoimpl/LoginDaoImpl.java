<<<<<<< HEAD
/**
 * 
 */
package com.finvendor.daoimpl;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.dao.LoginDao;
import com.finvendor.model.FinVendorUser;

/**
 * @author rayulu vemula
 *
 */
public class LoginDaoImpl implements LoginDao{

	private static Logger logger = Logger.getLogger(LoginDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.LoginDaoImpl#getUserInfoByEmail(com.finvendor.model.FinVendorUser)
	 */
	@Transactional
	public FinVendorUser getUserInfoByEmail(String email) {
		Criteria criteria=null;
		try{
			criteria=this.sessionFactory.getCurrentSession().createCriteria(FinVendorUser.class);
			criteria.add(Restrictions.eq("email", email));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return (FinVendorUser)criteria.uniqueResult();
	}

	 

}
=======
/**
 * 
 */
package com.finvendor.daoimpl;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.dao.LoginDao;
import com.finvendor.model.FinVendorUser;

/**
 * @author rayulu vemula
 *
 */
public class LoginDaoImpl implements LoginDao{

	private static Logger logger = Logger.getLogger(LoginDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.LoginDaoImpl#getUserInfoByEmail(com.finvendor.model.FinVendorUser)
	 */
	@Transactional
	public FinVendorUser getUserInfoByEmail(String email) {
		Criteria criteria=null;
		try{
			criteria=this.sessionFactory.getCurrentSession().createCriteria(FinVendorUser.class);
			criteria.add(Restrictions.eq("email", email));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return (FinVendorUser)criteria.uniqueResult();
	}

	 

}
>>>>>>> origin/master
