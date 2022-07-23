package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(String id);
    Employee update(Employee employee);


    ReportingStructure getEmployeeReportingStructure(String id);

    Compensation employeeCompensationCreate(Compensation compensation);

    Compensation employeeCompensationRead(String id);
}
