package srdt.co.in.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import srdt.co.in.entities.NavBars;

@Repository
public interface NavBarsJPARepository extends JpaRepository<NavBars, Long> {
	List<NavBars> AllParents();
	List<NavBars> getAllNodes();
}
