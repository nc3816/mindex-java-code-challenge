package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;


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

    /**
     * Get the number of reports 
     * @param employeeId
     * @return
     */
    private int getNumberOfReports(String employeeId) {
        int totalCount = 0;

        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if (employee == null) {
            throw new RuntimeException("getNumberOfReports Invalid employeeId: " + employeeId);
        }
        //get the reporting employees' id
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
