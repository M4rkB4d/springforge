package dev.springforge.t2_02;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTaskRequest(
    @NotBlank
    String title,

    @Size(max = 500)
    String description,

    @Min(1) @Max(10)
    int priority
) {}
