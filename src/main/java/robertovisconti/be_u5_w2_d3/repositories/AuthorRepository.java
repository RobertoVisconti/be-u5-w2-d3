package robertovisconti.be_u5_w2_d3.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import robertovisconti.be_u5_w2_d3.entities.Author;

import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
    boolean existsByEmail(String email);
}
