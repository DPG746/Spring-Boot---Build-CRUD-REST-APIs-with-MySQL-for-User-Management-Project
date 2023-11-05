package net.javaguides.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.ErrorDetails;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Tag(

        name="CRUD REST API FOR USERS RESOURCE",
        description = "CRUD REST APIS- Create User, Update User, Get User, Get All Users, Delete User"
)

@RequestMapping("api/users")
@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;
    @Operation(
            summary = "Create User Rest Api",
            description = "Create User REST Api is used to save user in a database"
    )
    @ApiResponse(

            responseCode = "201",
            description = "Http Status 201 Created"
    )
@PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
        UserDto savedUser= userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @Operation(
            summary = "Get User by ID Rest Api",
            description = "Get User By ID REST Api is used to get a single user from the database"
    )
    @ApiResponse(

            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )


    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto  user=userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @Operation(
            summary = "Get All  User  Rest Api",
            description = "Get All Users REST Api is used to get a all users from the database"
    )
    @ApiResponse(

            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )


@GetMapping
public ResponseEntity<List<UserDto>> getAllUsers(){
    List<UserDto> users= userService.getAllUsers();
    return new ResponseEntity<>(users , HttpStatus.OK);
}
    @Operation(
            summary = "Update User  Rest Api",
            description = "Update User REST Api is used to update a particular users in the database"
    )
    @ApiResponse(

            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )

@PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,@Valid @RequestBody UserDto user){
    user.setId(userId);
    UserDto updatedUser = userService.updateUser(user);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
}
    @Operation(
            summary = "Delete User  Rest Api",
            description = "Delete User REST Api is used to update a particular users in the database"
    )
    @ApiResponse(

            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User Succesfully Deleted", HttpStatus.OK);
    }
@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){

        ErrorDetails errorDetails=new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "User_NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


}