package com.uade.tpo.courseCommerce.entity.requestEntity;

import lombok.Data;

@Data
public class RequestCourseEdit {

    private String description;

    private String newDescription;

    private String longDescription;

    private String startDate;

    private String category;

    private int maxSlots;

    private String teacher;

}
