package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Task2 compensation controller
 */
@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;


    /**
     * Task 2 - one: compensation for the employee (post)
     * @param compensation
     * @return
     */
    @PostMapping("/employee/compensation/create")
    public Compensation employeeCompensationCreate(@RequestBody Compensation compensation) {
        LOG.debug("Received employee compensation create request for [{}]", compensation);

        return compensationService.employeeCompensationCreate(compensation);
    }


    /**
     * Task 2 - two: obtain the compensation of the employee(get)
     * @param id
     * @return
     */
    @GetMapping("/employee/compensation/read/{id}")
    public Compensation employeeCompensationRead(@PathVariable String id) {
        LOG.debug("Received employee compensation read request for employeeId [{}]", id);

        return compensationService.employeeCompensationRead(id);
    }
}
