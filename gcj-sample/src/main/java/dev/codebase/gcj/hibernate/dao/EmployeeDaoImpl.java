package dev.codebase.gcj.hibernate.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.codebase.gcj.hibernate.model.Employee;

@Repository
// @Transactional(readOnly = true)   ....use this if we want to prevent writing of data
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.openSession();

/* 
 * Based on HQL and Query
 *        
        String hql = "FROM Employee";
        Query q = session.createQuery(hql);
        
        @SuppressWarnings("unchecked")
        List<Employee> emps = new ArrayList<Employee>(q.list());
*/
        
        // Alternative based on HCQL and Criteria
        Criteria c = session.createCriteria(Employee.class);
        c.add(Restrictions.gt("id", 0));        // Has no impact but demos criteria restrictions
        
        @SuppressWarnings("unchecked")
        List<Employee> emps = new ArrayList<Employee>(c.list());
        
        return emps;
    }

    @Override
    public Integer insertEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        Serializable s = session.save(employee);
        return (s instanceof Integer) ? (Integer) s : null;
    }

    @Override
    public void deleteEmployee(Integer id) {
        Session session = sessionFactory.openSession();
        
        String hql = "DELETE from Employee E where E.id = :employee_id";
        Query q = session.createQuery(hql);
        q.setParameter("employee_id", id);
        
        q.executeUpdate();
    }

}
