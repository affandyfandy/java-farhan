package aliramadhan.assignment3.repository;

import java.util.List;

import aliramadhan.assignment3.model.Employee;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class EmployeeRepository {

    private final JdbcTemplate jdbc1;
    private final JdbcTemplate jdbc2;

    @Autowired
    public EmployeeRepository(@Qualifier("dataSource1") DataSource dataSource1,
                              @Qualifier("dataSource2") DataSource dataSource2) {
        this.jdbc1 = new JdbcTemplate(dataSource1);
        this.jdbc2 = new JdbcTemplate(dataSource2);
    }

    public int saveToDs1(Employee employee){
        return jdbc1.update("INSERT INTO employee (id, name, dob, address, department) VALUES (?,?,?,?,?)",
                new Object[] { employee.getId(), employee.getName(), employee.getDob(), employee.getAddress(), employee.getDepartment() });
    }

    public int saveToDs2(Employee employee){
        return jdbc2.update("INSERT INTO employee (id, name, dob, address, department) VALUES (?,?,?,?,?)",
                new Object[] { employee.getId(), employee.getName(), employee.getDob(), employee.getAddress(), employee.getDepartment() });
    }

    public int updateDs1(Employee employee){
        return jdbc1.update("UPDATE employee SET name=?, dob=?, address=?, department=? WHERE id=?",
                new Object[] { employee.getName(), employee.getDob(),employee.getAddress(), employee.getDepartment(), employee.getId()});
    }

    public int updateDs2(Employee employee){
        return jdbc2.update("UPDATE employee SET name=?, dob=?, address=?, department=? WHERE id=?",
                new Object[] { employee.getName(), employee.getDob(),employee.getAddress(), employee.getDepartment(), employee.getId()});
    }

    public Employee findDs1(String id){
        try {
            Employee employee = jdbc1.queryForObject("SELECT * FROM employee WHERE id=? LIMIT 1",
                    BeanPropertyRowMapper.newInstance(Employee.class), id);
            return employee;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public Employee findDs2(String id){
        try {
            Employee employee = jdbc2.queryForObject("SELECT * FROM employee WHERE id=? LIMIT 1",
                    BeanPropertyRowMapper.newInstance(Employee.class), id);
            return employee;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public int deleteDs1(String id) {
        return jdbc1.update("DELETE FROM employee WHERE id=?", id);
    }

    public int deleteDs2(String id) {
        return jdbc2.update("DELETE FROM employee WHERE id=?", id);
    }

    public List<Employee> findAllDS1(){
        return jdbc1.query("SELECT * from employee", BeanPropertyRowMapper.newInstance(Employee.class));
    }

    public List<Employee> findAllDS2(){
        return jdbc2.query("SELECT * from employee", BeanPropertyRowMapper.newInstance(Employee.class));
    }

}
