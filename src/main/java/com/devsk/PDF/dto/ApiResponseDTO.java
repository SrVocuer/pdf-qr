package com.devsk.PDF.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResponseDTO<T> {

    private Meta  meta;
    private T data;
}
