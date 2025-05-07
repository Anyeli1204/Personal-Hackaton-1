package repository;

import domain.Limite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LimiteRepository extends JpaRepository<Limite, UUID> {}
