package br.com.rogrs.repository;

import br.com.rogrs.domain.Database;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface DatasourceRepository extends JpaRepository<Database, Long> {

}
