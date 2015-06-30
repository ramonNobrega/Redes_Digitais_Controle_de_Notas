package redes_digitais_controle_de_notas.business.entity;

import redes_digitais_controle_de_notas.domain.entity.Professor;
import redes_digitais_controle_de_notas.business.entity.ProfessorBC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.*;
import javax.inject.Inject;
import org.junit.*;
import org.junit.runner.RunWith;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

@RunWith(DemoiselleRunner.class)
public class ProfessorBCTest {

	@Inject
	private ProfessorBC professorBC;

	@Before
	public void before() {
		for (Professor professor : professorBC.findAll()) {
		  professorBC.delete(professor.getUser().getId());
		}
	}

	@After
	public void after() {
		before();
	}

	@Test
	public void insert() {
		Professor professor = new Professor();
		professor.setDisciplina("XXXXXXXXXXXXXXXXXXXX");
		professorBC.insert(professor);
		List<Professor> professorList = professorBC.findAll();
		assertNotNull(professorList);
		assertEquals(1, professorList.size());
	}

	@Test
	public void update() {
		Professor professor = new Professor();
		professor.setDisciplina("XXXXXXXXXXXXXXXXXXXX");
		professorBC.insert(professor);
		List<Professor> professorList = professorBC.findAll();
		assertNotNull(professorList);
		assertTrue(professorList.size()>0);
		Professor beforeUpdate = professorList.get(0);
		assertEquals("XXXXXXXXXXXXXXXXXXXX", beforeUpdate.getDisciplina());
		beforeUpdate.setDisciplina("YYYYYYYYYYYYYYYYYYYY");
		professorBC.update(beforeUpdate);
		professorList = professorBC.findAll();
		Professor afterUpdate = professorList.get(0);
		assertEquals("YYYYYYYYYYYYYYYYYYYY", afterUpdate.getDisciplina());
	}

	@Test
	public void delete() {
		Professor professor = new Professor();
		professor.setDisciplina("XXXXXXXXXXXXXXXXXXXX");
		professorBC.insert(professor);
		List<Professor> professorList = professorBC.findAll();
		assertNotNull(professorList);
		assertEquals(1, professorList.size());
		  professorBC.delete(professor.getUser().getId());
		professorList = professorBC.findAll();
		assertEquals(0, professorList.size());
	}

}

