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
public class TurmaAlunoDAOTest {

	@Inject
	private TurmaAlunoDAO turmaAlunoDAO;

	@Inject
	private TurmaDAO turmaDAO;

	@Before
	public void before() {
		for (TurmaAluno turmaAluno : turmaAlunoDAO.findAll()) {
		TurmaAlunoKey turmaAlunoKey = new TurmaAlunoKey(turmaAluno.getTurma().getIdTurma(), turmaAluno.getAluno().getUser().getId());
		turmaAlunoDAO.delete(turmaAlunoKey);
		}
	}

	@After
	public void after() {
		before();
	}

	@Test
	public void insert() {
		TurmaAluno turmaAluno = new TurmaAluno();
		turmaAlunoDAO.insert(turmaAluno);
		List<TurmaAluno> turmaAlunoList = turmaAlunoDAO.findAll();
		assertNotNull(turmaAlunoList);
		assertEquals(1, turmaAlunoList.size());
	}

	@Test
	public void update() {
		TurmaAluno turmaAluno = new TurmaAluno();
		Turma turma = new Turma();
		turma.setNomeTurma("XXXXXXXXXXXXXXXXXXXX");
		turmaDAO.insert(turma);
		turmaAluno.setTurma(turma);
		turmaAlunoDAO.insert(turmaAluno);
		List<TurmaAluno> turmaAlunoList = turmaAlunoDAO.findAll();
		assertNotNull(turmaAlunoList);
		assertTrue(turmaAlunoList.size()>0);
	}

	@Test
	public void delete() {
		TurmaAluno turmaAluno = new TurmaAluno();
		turmaAlunoDAO.insert(turmaAluno);
		List<TurmaAluno> turmaAlunoList = turmaAlunoDAO.findAll();
		assertNotNull(turmaAlunoList);
		assertEquals(1, turmaAlunoList.size());
		TurmaAlunoKey turmaAlunoKey = new TurmaAlunoKey(turmaAluno.getTurma().getIdTurma(), turmaAluno.getAluno().getUser().getId());
		turmaAlunoDAO.delete(turmaAlunoKey);
		turmaAlunoList = turmaAlunoDAO.findAll();
		assertEquals(0, turmaAlunoList.size());
	}

}

