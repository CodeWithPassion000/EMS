package com.company.ems.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentRequestDto {

    @NotBlank(message = "Department Name is required")
    @Size(min = 2,max = 100,message = "Department Name must be between 2 and 100 characters")
    private String departmentName;

    @NotBlank(message = "Location is required")
    @Size(min = 2, max = 100,
            message = "Location must be between 2 and 100 characters")
    private String location;

}
