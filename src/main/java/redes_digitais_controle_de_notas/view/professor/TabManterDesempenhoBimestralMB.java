package redes_digitais_controle_de_notas.view.professor;

import java.util.*;

import javax.annotation.*;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.*;
import br.gov.frameworkdemoiselle.exception.*;
import br.gov.frameworkdemoiselle.message.*;
import br.gov.frameworkdemoiselle.stereotype.*;
import br.gov.frameworkdemoiselle.template.*;
import br.gov.frameworkdemoiselle.transaction.*;

import redes_digitais_controle_de_notas.domain.entity.*;
import redes_digitais_controle_de_notas.domain.enumeration.*;
import redes_digitais_controle_de_notas.domain.view.*;
import redes_digitais_controle_de_notas.business.entity.*;
import redes_digitais_controle_de_notas.business.process.*;
import redes_digitais_controle_de_notas.constant.*;
import redes_digitais_controle_de_notas.exception.*;

import redes_digitais_controle_de_notas.business.entity.DesempenhoBimestralBC;
import redes_digitais_controle_de_notas.domain.entity.DesempenhoBimestral;
import redes_digitais_controle_de_notas.security.ContextMB;
@ViewController
@PreviousView("/professor/tabManterDesempenhoBimestral.xhtml")
@NextView("/professor/tabManterDesempenhoBimestralDetail.xhtml")
public class TabManterDesempenhoBimestralMB extends AbstractListPageBean<DesempenhoBimestral, Integer> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Inject
	private DesempenhoBimestralBC desempenhoBimestralBC;
	
		@Inject
		private ProfessorBC professorBC;
		
		@Inject
		private ContextMB context;
	
	       private List<DesempenhoBimestral> desempenhoResultList;
	/* Button[list.handleResultList] */
			@Override
			protected List<DesempenhoBimestral> handleResultList() {
				
				calculaMediaBimestral();		
				
				Professor professor = professorBC.load(new Long(context.getUser().getId()));
				desempenhoResultList = new ArrayList<DesempenhoBimestral>();
				for (DesempenhoBimestral db : desempenhoBimestralBC.findAll()){			
					if( db.getProfessor().equals(professor)){
								desempenhoResultList.add(db);
						}
					}
					
				return desempenhoResultList;
			}/* Button[list.handleResultList] */
	
	/* Trigger[list.handleResultList.calculaMediaBimestral] */
	public void calculaMediaBimestral() {
		for (DesempenhoBimestral item : desempenhoBimestralBC.findAll()) {
			item.setMediaBimestre((item.getNota1() + item.getNota2() + item.getNota3())/3);
		}
	}
	
	/* Trigger[list.handleResultList.calculaMediaBimestral] */

}
