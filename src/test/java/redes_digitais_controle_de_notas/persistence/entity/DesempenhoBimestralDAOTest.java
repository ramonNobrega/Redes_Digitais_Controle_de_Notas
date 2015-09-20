package redes_digitais_controle_de_notas.persistence.entity;

import redes_digitais_controle_de_notas.domain.entity.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.*;
import javax.inject.Inject;
import org.junit.*;
import org.junit.runner.RunWith;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

@RunWith(DemoiselleRunner.class)
public class DesempenhoBimestralDAOTest {

	@Inject
	private DesempenhoBimestralDAO desempenhoBimestralDAO;

	@Inject
	private ProfessorDAO professorDAO;

	@Before
	public void before() {
		for (DesempenhoBimestral desempenhoBimestral : desempenhoBimestralDAO.findAll()) {
		desempenhoBimestralDAO.delete(desempenhoBimestral.getIdBimestre());
		}
	}

	@After
	public void after() {
		before();
	}

	@Test
	public void insert() {
		DesempenhoBimestral desempenhoBimestral = new DesempenhoBimestral();
		desempenhoBimestral.setNumBimestre(new Integer("1"));
		desempenhoBimestral.setNota1(desempenhoBimestral.getId().setNota1(new Double("1.1"));
		desempenhoBimestral.setNota2(desempenhoBimestral.getId().setNota2(new Double("1.1"));
		desempenhoBimestral.setNota3(desempenhoBimestral.getId().setNota3(new Double("1.1"));
		Professor professor = new Professor();
		professor.setDisciplina("XXXXXXXXXXXXXXXXXXXX");
		professorDAO.insert(professor);
		desempenhoBimestral.setProfessor(professor);
		desempenhoBimestralDAO.insert(desempenhoBimestral);
		List<DesempenhoBimestral> desempenhoBimestralList = desempenhoBimestralDAO.findAll();
		assertNotNull(desempenhoBimestralList);
		assertEquals(1, desempenhoBimestralList.size());
	}

	@Test
	public void update() {
		DesempenhoBimestral desempenhoBimestral = new DesempenhoBimestral();
		desempenhoBimestral.setNumBimestre(new Integer("1"));
		desempenhoBimestral.setNota1(new Double("1.1"));
		desempenhoBimestral.setNota2(new Double("1.1"));
		desempenhoBimestral.setNota3(new Double("1.1"));
		Professor professor = new Professor();
		professor.setDisciplina("XXXXXXXXXXXXXXXXXXXX");
		professorDAO.insert(professor);
		desempenhoBimestral.setProfessor(professor);
		desempenhoBimestralDAO.insert(desempenhoBimestral);
		List<DesempenhoBimestral> desempenhoBimestralList = desempenhoBimestralDAO.findAll();
		assertNotNull(desempenhoBimestralList);
		assertTrue(desempenhoBimestralList.size()>0);
		DesempenhoBimestral beforeUpdate = desempenhoBimestralList.get(0);
		assertEquals(new Double("1.1"), beforeUpdate.getProfessor());
		beforeUpdate.setProfessor(null);
		desempenhoBimestralDAO.update(beforeUpdate);
		desempenhoBimestralList = desempenhoBimestralDAO.findAll();
		DesempenhoBimestral afterUpdate = desempenhoBimestralList.get(0);
		assertEquals(null, afterUpdate.getProfessor());
	}

	@Test
	public void delete() {
		DesempenhoBimestral desempenhoBimestral = new DesempenhoBimestral();
		desempenhoBimestral.setNumBimestre(new Integer("1"));
		desempenhoBimestral.setNota1(new Double("1.1"));
		desempenhoBimestral.setNota2(new Double("1.1"));
		desempenhoBimestral.setNota3(new Double("1.1"));
		Professor professor = new Professor();
		professor.setDisciplina("XXXXXXXXXXXXXXXXXXXX");
		professorDAO.insert(professor);
		desempenhoBimestral.setProfessor(professor);
		desempenhoBimestralDAO.insert(desempenhoBimestral);
		List<DesempenhoBimestral> desempenhoBimestralList = desempenhoBimestralDAO.findAll();
		assertNotNull(desempenhoBimestralList);
		assertEquals(1, desempenhoBimestralList.size());
		desempenhoBimestralDAO.delete(desempenhoBimestral.getIdBimestre());
		desempenhoBimestralList = desempenhoBimestralDAO.findAll();
		assertEquals(0, desempenhoBimestralList.size());
	}

}

