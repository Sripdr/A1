package in.shop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @NotNull(message = "First name cannot be null")
    private  String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    private String phoneNumber;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDate dateOfBirth;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    @Email
    @Column(unique = true)
    @NotNull(message = "please enter valid user name")
    private String username;
    @NotNull(message = "please enter valid password")
    private String password;

    private Set<String> role;

    public void getCreatedAt(LocalDateTime now) {

    }

    public void getUpdatedAt(Instant now) {

    }
}
