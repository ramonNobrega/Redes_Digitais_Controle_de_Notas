package redes_digitais_controle_de_notas.business.entity;

import redes_digitais_controle_de_notas.domain.entity.Turma;
import redes_digitais_controle_de_notas.business.entity.TurmaBC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.*;
import javax.inject.Inject;
import org.junit.*;
import org.junit.runner.RunWith;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

@RunWith(DemoiselleRunner.class)
public class TurmaBCTest {

	@Inject
	private TurmaBC turmaBC;

	@Before
	public void before() {
		for (Turma turma : turmaBC.findAll()) {
		  turmaBC.delete(turma.getIdTurma());
		}
	}

	@After
	public void after() {
		before();
	}

	@Test
	public void insert() {
		Turma turma = new Turma();
		turma.setNomeTurma("XXXXXXXXXXXXXXXXXXXX");
		turmaBC.insert(turma);
		List<Turma> turmaList = turmaBC.findAll();
		assertNotNull(turmaList);
		assertEquals(1, turmaList.size());
	}

	@Test
	public void update() {
		Turma turma = new Turma();
		turma.setNomeTurma("XXXXXXXXXXXXXXXXXXXX");
		turmaBC.insert(turma);
		List<Turma> turmaList = turmaBC.findAll();
		assertNotNull(turmaList);
		assertTrue(turmaList.size()>0);
		Turma beforeUpdate = turmaList.get(0);
		assertEquals("XXXXXXXXXXXXXXXXXXXX", beforeUpdate.getNomeTurma());
		beforeUpdate.setNomeTurma("YYYYYYYYYYYYYYYYYYYY");
		turmaBC.update(beforeUpdate);
		turmaList = turmaBC.findAll();
		Turma afterUpdate = turmaList.get(0);
		assertEquals("YYYYYYYYYYYYYYYYYYYY", afterUpdate.getNomeTurma());
	}

	@Test
	public void delete() {
		Turma turma = new Turma();
		turma.setNomeTurma("XXXXXXXXXXXXXXXXXXXX");
		turmaBC.insert(turma);
		List<Turma> turmaList = turmaBC.findAll();
		assertNotNull(turmaList);
		assertEquals(1, turmaList.size());
		  turmaBC.delete(turma.getIdTurma());
		turmaList = turmaBC.findAll();
		assertEquals(0, turmaList.size());
	}

}

