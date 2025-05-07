package repository;

import domain.Restriccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestriccionRepository extends JpaRepository<Restriccion, UUID> {}
