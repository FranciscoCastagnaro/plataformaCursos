package com.uade.tpo.courseCommerce.entity.requestEntity;

import lombok.Data;

@Data
public class RequestCourse {

    private String description;

    private String longDescription;

    private String startDate;

    private String category;

    private int maxSlots;

    private String teacher;

    private int price;

}
