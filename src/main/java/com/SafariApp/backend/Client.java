package com.SafariApp.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class Client {
    private String  Name;
    private String  Surname;
    private String  PhoneNumber;
    private String  Email;
}
