package redes_digitais_controle_de_notas.business.entity;

import redes_digitais_controle_de_notas.domain.entity.TurmaProfessor;
import redes_digitais_controle_de_notas.domain.entity.TurmaProfessorKey;
import redes_digitais_controle_de_notas.business.entity.TurmaProfessorBC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.*;
import javax.inject.Inject;
import org.junit.*;
import org.junit.runner.RunWith;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import redes_digitais_controle_de_notas.domain.entity.Turma;

@RunWith(DemoiselleRunner.class)
public class TurmaProfessorBCTest {

	@Inject
	private TurmaProfessorBC turmaProfessorBC;

	@Inject
	private TurmaBC turmaBC;

	@Before
	public void before() {
		for (TurmaProfessor turmaProfessor : turmaProfessorBC.findAll()) {
		TurmaProfessorKey turmaProfessorKey = new TurmaProfessorKey(turmaProfessor.getTurma().getIdTurma(), turmaProfessor.getProfessor().getUser().getId());
		turmaProfessorBC.delete(turmaProfessorKey);
		}
	}

	@After
	public void after() {
		before();
	}

	@Test
	public void insert() {
		TurmaProfessor turmaProfessor = new TurmaProfessor();
		turmaProfessorBC.insert(turmaProfessor);
		List<TurmaProfessor> turmaProfessorList = turmaProfessorBC.findAll();
		assertNotNull(turmaProfessorList);
		assertEquals(1, turmaProfessorList.size());
	}

	@Test
	public void update() {
		TurmaProfessor turmaProfessor = new TurmaProfessor();
		Turma turma = new Turma();
		turma.setNomeTurma("XXXXXXXXXXXXXXXXXXXX");
		turmaBC.insert(turma);
		turmaProfessor.setTurma(turma);
		turmaProfessorBC.insert(turmaProfessor);
		List<TurmaProfessor> turmaProfessorList = turmaProfessorBC.findAll();
		assertNotNull(turmaProfessorList);
		assertTrue(turmaProfessorList.size()>0);
	}

	@Test
	public void delete() {
		TurmaProfessor turmaProfessor = new TurmaProfessor();
		turmaProfessorBC.insert(turmaProfessor);
		List<TurmaProfessor> turmaProfessorList = turmaProfessorBC.findAll();
		assertNotNull(turmaProfessorList);
		assertEquals(1, turmaProfessorList.size());
		TurmaProfessorKey turmaProfessorKey = new TurmaProfessorKey(turmaProfessor.getTurma().getIdTurma(), turmaProfessor.getProfessor().getUser().getId());
		turmaProfessorBC.delete(turmaProfessorKey);
		turmaProfessorList = turmaProfessorBC.findAll();
		assertEquals(0, turmaProfessorList.size());
	}

}

