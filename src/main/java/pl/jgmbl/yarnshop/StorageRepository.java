package pl.jgmbl.yarnshop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<Storage, Integer> {
    Optional<Storage> findByYarnId (Integer yarn_id);
}
