package com.gdg.jwtonlinelecture.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InstructorInfoResponseDto {
    private Long id;
    private String name;
    private String title;
    private String role;
}