package pawnbolo.com.pawnbolo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawnbolo.com.pawnbolo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
