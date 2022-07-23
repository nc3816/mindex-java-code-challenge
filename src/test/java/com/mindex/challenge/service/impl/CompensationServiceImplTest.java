package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
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
public class CompensationServiceImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImplTest.class);

    private String taskTwo1Url;
    private String taskTwo2Url;


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        //task2-1
        taskTwo1Url = "http://localhost:" + port + "/employee/compensation/create";
        //task2-2
        taskTwo2Url = "http://localhost:" + port + "/employee/compensation/read/{id}";
    }

    //Task2-2
    @Test
    public void taskTwoTest() {
        //Task2-1
        //step1: construct compensation object
        String compensationEmployeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        Employee compensationEmployee = new Employee();
        compensationEmployee.setEmployeeId(compensationEmployeeId);
        Compensation compensation = new Compensation();
        compensation.setEmployee(compensationEmployee);
        compensation.setSalary(String.valueOf(500000));
        compensation.setEffectiveDate("2022-12-31");

        //step2: create compensation
        Compensation createCompensation = restTemplate.postForEntity(taskTwo1Url, compensation, Compensation.class).getBody();
        LOG.info("===Task2-1 has finished");

        //Task2-2
        //step3: read compensation
        Compensation readCompensation = restTemplate.getForEntity(taskTwo2Url, Compensation.class, compensationEmployeeId).getBody();
        LOG.info("===Task2-2 has finished");
        LOG.info("===Task2-2 compensate money = {}", readCompensation.getSalary());
    }
}
