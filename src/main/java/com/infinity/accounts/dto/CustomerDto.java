package com.infinity.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold customer and account info"
)
public class CustomerDto {
    @Schema(
            description = "name of the customer", example = "John Doe"
    )
    private String name;

    @Schema(
            description = "Customer email address", example = "johndoe@gmail.com"
    )
    private String email;

    @Schema(
            description = "Customer mobile number", example = "07778989898"
    )
    private String mobileNumber;
}
