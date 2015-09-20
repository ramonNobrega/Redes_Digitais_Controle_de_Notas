package redes_digitais_controle_de_notas.business.entity;

import redes_digitais_controle_de_notas.domain.entity.DesempenhoBimestral;
import redes_digitais_controle_de_notas.business.entity.DesempenhoBimestralBC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.*;
import javax.inject.Inject;
import org.junit.*;
import org.junit.runner.RunWith;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import redes_digitais_controle_de_notas.domain.entity.Professor;

@RunWith(DemoiselleRunner.class)
public class DesempenhoBimestralBCTest {

	@Inject
	private DesempenhoBimestralBC desempenhoBimestralBC;

	@Inject
	private ProfessorBC professorBC;

	@Before
	public void before() {
		for (DesempenhoBimestral desempenhoBimestral : desempenhoBimestralBC.findAll()) {
		  desempenhoBimestralBC.delete(desempenhoBimestral.getIdBimestre());
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
		desempenhoBimestral.setNota1(new Double("1.1"));
		desempenhoBimestral.setNota2(new Double("1.1"));
		desempenhoBimestral.setNota3(new Double("1.1"));
		Professor professor = new Professor();
		professor.setDisciplina("XXXXXXXXXXXXXXXXXXXX");
		professorBC.insert(professor);
		desempenhoBimestral.setProfessor(professor);
		desempenhoBimestralBC.insert(desempenhoBimestral);
		List<DesempenhoBimestral> desempenhoBimestralList = desempenhoBimestralBC.findAll();
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
		professorBC.insert(professor);
		desempenhoBimestral.setProfessor(professor);
		desempenhoBimestralBC.insert(desempenhoBimestral);
		List<DesempenhoBimestral> desempenhoBimestralList = desempenhoBimestralBC.findAll();
		assertNotNull(desempenhoBimestralList);
		assertTrue(desempenhoBimestralList.size()>0);
		DesempenhoBimestral beforeUpdate = desempenhoBimestralList.get(0);
		assertEquals(new Double("1.1"), beforeUpdate.getProfessor());
		beforeUpdate.setProfessor(null);
		desempenhoBimestralBC.update(beforeUpdate);
		desempenhoBimestralList = desempenhoBimestralBC.findAll();
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
		professorBC.insert(professor);
		desempenhoBimestral.setProfessor(professor);
		desempenhoBimestralBC.insert(desempenhoBimestral);
		List<DesempenhoBimestral> desempenhoBimestralList = desempenhoBimestralBC.findAll();
		assertNotNull(desempenhoBimestralList);
		assertEquals(1, desempenhoBimestralList.size());
		  desempenhoBimestralBC.delete(desempenhoBimestral.getIdBimestre());
		desempenhoBimestralList = desempenhoBimestralBC.findAll();
		assertEquals(0, desempenhoBimestralList.size());
	}

}

