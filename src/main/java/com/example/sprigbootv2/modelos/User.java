package com.example.sprigbootv2.modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Users")
@Data
public class User {
    @Id
    private String _id;
    private String user;
    private String email;
    private String token;
}

