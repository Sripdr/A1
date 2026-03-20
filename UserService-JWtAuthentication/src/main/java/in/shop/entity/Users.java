package in.shop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usr_id")
    private String usrId;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @NonNull
    @Column(unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    private Set<String> roles;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "users")
    private UserInfo userInfo;

    @PrePersist
    public void setUsrId() {
        this.usrId = "USR"+UUID.randomUUID().toString().toUpperCase().substring(0,8);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).toList();
    }

    @Override
    public String getUsername() {
        if (email==null)
            return phoneNumber;
        return email;
    }
}
