package com.seven.mybatispluseasyexcel.normal.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Salaries {

    private Integer empNo;
    private Integer salary;
    private Date fromDate;
    private Date toDate;
}
