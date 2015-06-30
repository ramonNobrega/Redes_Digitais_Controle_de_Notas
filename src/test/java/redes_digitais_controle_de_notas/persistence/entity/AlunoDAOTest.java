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
public class AlunoDAOTest {

	@Inject
	private AlunoDAO alunoDAO;

	@Before
	public void before() {
		for (Aluno aluno : alunoDAO.findAll()) {
		alunoDAO.delete(aluno.getUser().getId());
		}
	}

	@After
	public void after() {
		before();
	}

	@Test
	public void insert() {
		Aluno aluno = new Aluno();
		alunoDAO.insert(aluno);
		List<Aluno> alunoList = alunoDAO.findAll();
		assertNotNull(alunoList);
		assertEquals(1, alunoList.size());
	}

	@Test
	public void update() {
		Aluno aluno = new Aluno();
		alunoDAO.insert(aluno);
		List<Aluno> alunoList = alunoDAO.findAll();
		assertNotNull(alunoList);
		assertTrue(alunoList.size()>0);
	}

	@Test
	public void delete() {
		Aluno aluno = new Aluno();
		alunoDAO.insert(aluno);
		List<Aluno> alunoList = alunoDAO.findAll();
		assertNotNull(alunoList);
		assertEquals(1, alunoList.size());
		alunoDAO.delete(aluno.getUser().getId());
		alunoList = alunoDAO.findAll();
		assertEquals(0, alunoList.size());
	}

}

