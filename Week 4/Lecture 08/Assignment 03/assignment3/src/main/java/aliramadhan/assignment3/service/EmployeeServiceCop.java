//package aliramadhan.assignment3.service;
//
//import aliramadhan.assignment3.model.Employee;
//import aliramadhan.assignment3.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.TransactionException;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.DefaultTransactionDefinition;
//import org.springframework.transaction.support.TransactionSynchronization;
//import org.springframework.transaction.support.TransactionSynchronizationManager;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class EmployeeServiceCop {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    private DataSourceTransactionManager transactionManager1;
//
//    @Autowired
//    private DataSourceTransactionManager transactionManager2;
//
//    public List<Employee> findAllFromDS1() {
//        return employeeRepository.findAllFromDS1();
//    }
//
//    public List<Employee> findAllFromDS2() {
//        return employeeRepository.findAllFromDS2();
//    }
//
//    public Optional<Employee> findByIdFromDS1(String id) {
//        return employeeRepository.findByIdFromDS1(id);
//    }
//
//    public Optional<Employee> findByIdFromDS2(String id) {
//        return employeeRepository.findByIdFromDS2(id);
//    }
//
//    public Employee saveDS1(Employee employee){
//        TransactionStatus status = transactionManager1.getTransaction(new DefaultTransactionDefinition());
//        try {
//            employeeRepository.saveToDS1(employee);
//            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
//                @Override
//                public void afterCommit() {
//                    System.out.println("Transaction committed successfully.");
//                }
//            });
//            transactionManager1.commit(status);
//            return employee;
//        } catch (IllegalStateException | TransactionException e) {
//            transactionManager1.rollback(status);
//            throw e;
//        }
//    }
//
//    public Employee saveDS2(Employee employee) {
//        TransactionStatus status = transactionManager2.getTransaction(new DefaultTransactionDefinition());
//        try {
//            employeeRepository.saveToDS2(employee);
//            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
//                @Override
//                public void afterCommit() {
//                    System.out.println("Transaction committed successfully.");
//                }
//            });
//            transactionManager2.commit(status);
//            return employee;
//        } catch (IllegalStateException | TransactionException e) {
//            transactionManager2.rollback(status);
//            throw e;
//        }
//    }
//
//    public Employee update(Employee employee) {
//        TransactionStatus status1 = transactionManager1.getTransaction(new DefaultTransactionDefinition());
//        TransactionStatus status2 = transactionManager2.getTransaction(new DefaultTransactionDefinition());
//        try {
//            employeeRepository.updateInDS1(employee);
//            employeeRepository.updateInDS2(employee);
//            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
//                @Override
//                public void afterCommit() {
//                    System.out.println("Transaction committed successfully.");
//                }
//            });
//            transactionManager1.commit(status1);
//            transactionManager2.commit(status2);
//            return employee;
//        } catch (IllegalStateException | TransactionException e) {
//            transactionManager1.rollback(status1);
//            transactionManager2.rollback(status2);
//            throw e;
//        }
//    }
//
//    public void deleteById(String id) {
//        TransactionStatus status1 = transactionManager1.getTransaction(new DefaultTransactionDefinition());
//        TransactionStatus status2 = transactionManager2.getTransaction(new DefaultTransactionDefinition());
//        try {
//            employeeRepository.deleteFromDS1ById(id);
//            employeeRepository.deleteFromDS2ById(id);
//            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
//                @Override
//                public void afterCommit() {
//                    System.out.println("Transaction committed successfully.");
//                }
//            });
//            transactionManager1.commit(status1);
//            transactionManager2.commit(status2);
//        } catch (IllegalStateException | TransactionException e) {
//            transactionManager1.rollback(status1);
//            transactionManager2.rollback(status2);
//            throw e;
//        }
//    }
//}
