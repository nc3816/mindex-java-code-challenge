package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.ReportingStructure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImplTest.class);

    //task1
    private String taskOneUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        taskOneUrl = "http://localhost:" + port + "/employee/reporting_structure/{id}";
    }

    @Test
    public void taskOneTest() {

        String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";

        ReportingStructure reportingStructure = restTemplate.getForEntity(taskOneUrl, ReportingStructure.class, employeeId).getBody();
        LOG.info("=====TaskOne result....");
        LOG.info("reportingStructure employee="+ reportingStructure.getEmployee().getEmployeeId());
        LOG.info("reportingStructure numberOfReports="+reportingStructure.getNumberOfReports());
    }

}
