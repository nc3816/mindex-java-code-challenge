package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

public interface CompensationService {

    Compensation employeeCompensationCreate(Compensation compensation);

    Compensation employeeCompensationRead(String id);
}
