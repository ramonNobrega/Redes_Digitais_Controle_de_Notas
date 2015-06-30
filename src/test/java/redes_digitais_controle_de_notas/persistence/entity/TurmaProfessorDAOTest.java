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
public class TurmaProfessorDAOTest {

	@Inject
	private TurmaProfessorDAO turmaProfessorDAO;

	@Inject
	private TurmaDAO turmaDAO;

	@Before
	public void before() {
		for (TurmaProfessor turmaProfessor : turmaProfessorDAO.findAll()) {
		TurmaProfessorKey turmaProfessorKey = new TurmaProfessorKey(turmaProfessor.getTurma().getIdTurma(), turmaProfessor.getProfessor().getUser().getId());
		turmaProfessorDAO.delete(turmaProfessorKey);
		}
	}

	@After
	public void after() {
		before();
	}

	@Test
	public void insert() {
		TurmaProfessor turmaProfessor = new TurmaProfessor();
		turmaProfessorDAO.insert(turmaProfessor);
		List<TurmaProfessor> turmaProfessorList = turmaProfessorDAO.findAll();
		assertNotNull(turmaProfessorList);
		assertEquals(1, turmaProfessorList.size());
	}

	@Test
	public void update() {
		TurmaProfessor turmaProfessor = new TurmaProfessor();
		Turma turma = new Turma();
		turma.setNomeTurma("XXXXXXXXXXXXXXXXXXXX");
		turmaDAO.insert(turma);
		turmaProfessor.setTurma(turma);
		turmaProfessorDAO.insert(turmaProfessor);
		List<TurmaProfessor> turmaProfessorList = turmaProfessorDAO.findAll();
		assertNotNull(turmaProfessorList);
		assertTrue(turmaProfessorList.size()>0);
	}

	@Test
	public void delete() {
		TurmaProfessor turmaProfessor = new TurmaProfessor();
		turmaProfessorDAO.insert(turmaProfessor);
		List<TurmaProfessor> turmaProfessorList = turmaProfessorDAO.findAll();
		assertNotNull(turmaProfessorList);
		assertEquals(1, turmaProfessorList.size());
		TurmaProfessorKey turmaProfessorKey = new TurmaProfessorKey(turmaProfessor.getTurma().getIdTurma(), turmaProfessor.getProfessor().getUser().getId());
		turmaProfessorDAO.delete(turmaProfessorKey);
		turmaProfessorList = turmaProfessorDAO.findAll();
		assertEquals(0, turmaProfessorList.size());
	}

}

