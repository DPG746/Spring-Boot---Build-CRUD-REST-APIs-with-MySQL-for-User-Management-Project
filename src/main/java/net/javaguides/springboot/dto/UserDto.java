package net.javaguides.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Schema(
        description = "UserDto Model Information"

)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @Schema(
            description = "User first Name"
    )
    @NotEmpty(message = "User First Name should not be Null or Empty")
    private String firstName;
    @Schema(
            description = "User Last Name"
    )
    @NotEmpty(message = "User Last Name should not be Null or Empty")
    private String lastName;
    @Schema(
            description = "User Email Address"
    )
    @NotEmpty(message = "User Email should not be Null or Empty")
    @Email(message = "Email Address should be valid")
    private String email;

}
