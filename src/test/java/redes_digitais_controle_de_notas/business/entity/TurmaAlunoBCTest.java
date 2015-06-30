package redes_digitais_controle_de_notas.business.entity;

import redes_digitais_controle_de_notas.domain.entity.TurmaAluno;
import redes_digitais_controle_de_notas.domain.entity.TurmaAlunoKey;
import redes_digitais_controle_de_notas.business.entity.TurmaAlunoBC;
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
public class TurmaAlunoBCTest {

	@Inject
	private TurmaAlunoBC turmaAlunoBC;

	@Inject
	private TurmaBC turmaBC;

	@Before
	public void before() {
		for (TurmaAluno turmaAluno : turmaAlunoBC.findAll()) {
		TurmaAlunoKey turmaAlunoKey = new TurmaAlunoKey(turmaAluno.getTurma().getIdTurma(), turmaAluno.getAluno().getUser().getId());
		turmaAlunoBC.delete(turmaAlunoKey);
		}
	}

	@After
	public void after() {
		before();
	}

	@Test
	public void insert() {
		TurmaAluno turmaAluno = new TurmaAluno();
		turmaAlunoBC.insert(turmaAluno);
		List<TurmaAluno> turmaAlunoList = turmaAlunoBC.findAll();
		assertNotNull(turmaAlunoList);
		assertEquals(1, turmaAlunoList.size());
	}

	@Test
	public void update() {
		TurmaAluno turmaAluno = new TurmaAluno();
		Turma turma = new Turma();
		turma.setNomeTurma("XXXXXXXXXXXXXXXXXXXX");
		turmaBC.insert(turma);
		turmaAluno.setTurma(turma);
		turmaAlunoBC.insert(turmaAluno);
		List<TurmaAluno> turmaAlunoList = turmaAlunoBC.findAll();
		assertNotNull(turmaAlunoList);
		assertTrue(turmaAlunoList.size()>0);
	}

	@Test
	public void delete() {
		TurmaAluno turmaAluno = new TurmaAluno();
		turmaAlunoBC.insert(turmaAluno);
		List<TurmaAluno> turmaAlunoList = turmaAlunoBC.findAll();
		assertNotNull(turmaAlunoList);
		assertEquals(1, turmaAlunoList.size());
		TurmaAlunoKey turmaAlunoKey = new TurmaAlunoKey(turmaAluno.getTurma().getIdTurma(), turmaAluno.getAluno().getUser().getId());
		turmaAlunoBC.delete(turmaAlunoKey);
		turmaAlunoList = turmaAlunoBC.findAll();
		assertEquals(0, turmaAlunoList.size());
	}

}

