package repository;

import domain.Sparky;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SparkyRepository extends JpaRepository<Sparky, UUID> {}
