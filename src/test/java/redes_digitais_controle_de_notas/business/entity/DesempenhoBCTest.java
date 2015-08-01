package redes_digitais_controle_de_notas.business.entity;

import redes_digitais_controle_de_notas.domain.entity.Desempenho;
import redes_digitais_controle_de_notas.business.entity.DesempenhoBC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.*;
import javax.inject.Inject;
import org.junit.*;
import org.junit.runner.RunWith;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

@RunWith(DemoiselleRunner.class)
public class DesempenhoBCTest {

	@Inject
	private DesempenhoBC desempenhoBC;

	@Before
	public void before() {
		for (Desempenho desempenho : desempenhoBC.findAll()) {
		  desempenhoBC.delete(desempenho.getIdDesempenho());
		}
	}

	@After
	public void after() {
		before();
	}

	@Test
	public void insert() {
		Desempenho desempenho = new Desempenho();
		desempenho.setMediaParcial(new Double("1.1"));
		desempenho.setMediaFinal(new Double("1.1"));
		desempenho.setSituacao("XXXXXXXXXX");
		desempenhoBC.insert(desempenho);
		List<Desempenho> desempenhoList = desempenhoBC.findAll();
		assertNotNull(desempenhoList);
		assertEquals(1, desempenhoList.size());
	}

	@Test
	public void update() {
		Desempenho desempenho = new Desempenho();
		desempenho.setMediaParcial(new Double("1.1"));
		desempenho.setMediaFinal(new Double("1.1"));
		desempenho.setSituacao("XXXXXXXXXX");
		desempenhoBC.insert(desempenho);
		List<Desempenho> desempenhoList = desempenhoBC.findAll();
		assertNotNull(desempenhoList);
		assertTrue(desempenhoList.size()>0);
		Desempenho beforeUpdate = desempenhoList.get(0);
		assertEquals("XXXXXXXXXX", beforeUpdate.getSituacao());
		beforeUpdate.setSituacao("YYYYYYYYYY");
		desempenhoBC.update(beforeUpdate);
		desempenhoList = desempenhoBC.findAll();
		Desempenho afterUpdate = desempenhoList.get(0);
		assertEquals("YYYYYYYYYY", afterUpdate.getSituacao());
	}

	@Test
	public void delete() {
		Desempenho desempenho = new Desempenho();
		desempenho.setMediaParcial(new Double("1.1"));
		desempenho.setMediaFinal(new Double("1.1"));
		desempenho.setSituacao("XXXXXXXXXX");
		desempenhoBC.insert(desempenho);
		List<Desempenho> desempenhoList = desempenhoBC.findAll();
		assertNotNull(desempenhoList);
		assertEquals(1, desempenhoList.size());
		  desempenhoBC.delete(desempenho.getIdDesempenho());
		desempenhoList = desempenhoBC.findAll();
		assertEquals(0, desempenhoList.size());
	}

}

