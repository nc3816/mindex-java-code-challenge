package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Task1 reporting structure controller
 */
@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private ReportingStructureService reportingStructureService;


    /**
     * Task 1: obtain the employee reporting structure (get)
     * @param id
     * @return
     */
    @GetMapping("/employee/reporting_structure/{id}")
    public ReportingStructure getEmployeeReportingStructure(@PathVariable String id) {
        LOG.debug("Received employee reporting_structure request for id [{}]", id);

        return reportingStructureService.getEmployeeReportingStructure(id);
    }
}
