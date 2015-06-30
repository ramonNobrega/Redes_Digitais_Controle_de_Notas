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
public class DesempenhoDAOTest {

	@Inject
	private DesempenhoDAO desempenhoDAO;

	@Before
	public void before() {
		for (Desempenho desempenho : desempenhoDAO.findAll()) {
		desempenhoDAO.delete(desempenho.getIdDesempenho());
		}
	}

	@After
	public void after() {
		before();
	}

	@Test
	public void insert() {
		Desempenho desempenho = new Desempenho();
		desempenho.setMediaParcial(desempenho.getId().setMediaParcial(new Double("1.1"));
		desempenho.setProvaFinal(desempenho.getId().setProvaFinal(new Double("1.1"));
		desempenho.setMediaFinal(desempenho.getId().setMediaFinal(new Double("1.1"));
		desempenho.setSituacao("XXXXXXXXXX");
		desempenhoDAO.insert(desempenho);
		List<Desempenho> desempenhoList = desempenhoDAO.findAll();
		assertNotNull(desempenhoList);
		assertEquals(1, desempenhoList.size());
	}

	@Test
	public void update() {
		Desempenho desempenho = new Desempenho();
		desempenho.setMediaParcial(new Double("1.1"));
		desempenho.setProvaFinal(new Double("1.1"));
		desempenho.setMediaFinal(new Double("1.1"));
		desempenho.setSituacao("XXXXXXXXXX");
		desempenhoDAO.insert(desempenho);
		List<Desempenho> desempenhoList = desempenhoDAO.findAll();
		assertNotNull(desempenhoList);
		assertTrue(desempenhoList.size()>0);
		Desempenho beforeUpdate = desempenhoList.get(0);
		assertEquals("XXXXXXXXXX", beforeUpdate.getSituacao());
		beforeUpdate.setSituacao("YYYYYYYYYY");
		desempenhoDAO.update(beforeUpdate);
		desempenhoList = desempenhoDAO.findAll();
		Desempenho afterUpdate = desempenhoList.get(0);
		assertEquals("YYYYYYYYYY", afterUpdate.getSituacao());
	}

	@Test
	public void delete() {
		Desempenho desempenho = new Desempenho();
		desempenho.setMediaParcial(new Double("1.1"));
		desempenho.setProvaFinal(new Double("1.1"));
		desempenho.setMediaFinal(new Double("1.1"));
		desempenho.setSituacao("XXXXXXXXXX");
		desempenhoDAO.insert(desempenho);
		List<Desempenho> desempenhoList = desempenhoDAO.findAll();
		assertNotNull(desempenhoList);
		assertEquals(1, desempenhoList.size());
		desempenhoDAO.delete(desempenho.getIdDesempenho());
		desempenhoList = desempenhoDAO.findAll();
		assertEquals(0, desempenhoList.size());
	}

}

