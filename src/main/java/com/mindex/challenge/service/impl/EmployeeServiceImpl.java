package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    CompensationRepository compensationRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    @Override
    public ReportingStructure getEmployeeReportingStructure(String id) {
        LOG.debug("get employee reporting structure with id [{}]", id);

        ReportingStructure reportingStructure = new ReportingStructure();

        Employee employee = employeeRepository.findByEmployeeId(id);
        int numberOfReports = getNumberOfReports(id);

        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(numberOfReports);
        return reportingStructure;
    }

    @Override
    public Compensation employeeCompensationCreate(Compensation compensation) {
        LOG.debug("Creating employee compensation: [{}]", compensation);

        String employeeId = compensation.getEmployee().getEmployeeId();
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        compensation.setEmployee(employee);
        compensationRepository.insert(compensation);

        return compensation;
    }

    @Override
    public Compensation employeeCompensationRead(String id) {
        LOG.debug("Reading employee compensation with id: [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        Compensation compensation = compensationRepository.findByEmployee(employee);

        if (compensation == null) {
            throw new RuntimeException("Invalid compensation: " + id);
        }
        return compensation;
    }

    /**
     * 获取下发报告数量统计 递归
     * @param employeeId
     * @return
     */
    private int getNumberOfReports(String employeeId) {
        int totalCount = 0;

        Employee employee = this.read(employeeId);
        if (employee == null) {
            throw new RuntimeException("getNumberOfReports Invalid employeeId: " + employeeId);
        }
        //查有直接报告的人员ids
        List<Employee> reports = employee.getDirectReports();
        if (reports != null && reports.size() > 0) {
            for (Employee reportingEmployee : reports) {
                totalCount++;
                int total = getNumberOfReports(reportingEmployee.getEmployeeId());
                totalCount += total;
            }
        }
        return totalCount;
    }
}
