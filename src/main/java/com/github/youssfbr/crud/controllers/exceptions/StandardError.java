package com.github.youssfbr.crud.controllers.exceptions;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class StandardError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
}
