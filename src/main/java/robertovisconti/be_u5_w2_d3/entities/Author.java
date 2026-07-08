package robertovisconti.be_u5_w2_d3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Author")
@NoArgsConstructor
@Getter
@ToString
public class Author {

    @Column(name = "id_author")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String avatar;

    public Author(String name, String surname, String email, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.avatar = "https://ui-avatars.com/api/?name=" + name + "+" + surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
