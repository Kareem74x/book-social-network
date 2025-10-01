package com.kareem.book_network.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "authentication")
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED) // This tells Spring what HTTP status code to return for the response.
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationRequest request) throws MessagingException {

        authService.register(request);
        return ResponseEntity.accepted().build();
    }

}
