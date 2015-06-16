package redes_digitais_controle_de_notas.view.system;

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

import redes_digitais_controle_de_notas.business.entity.UserBC;
import redes_digitais_controle_de_notas.domain.entity.User;
import redes_digitais_controle_de_notas.business.entity.RoleBC;
import org.primefaces.model.DualListModel;

@ViewController
@PreviousView("/system/maintainUser.xhtml")
@NextView("/system/maintainUserEdit.xhtml")
public class MaintainUserEditMB extends AbstractEditPageBean<User, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Inject
	private UserBC userBC;
	
	@Inject
	private RoleBC roleBC;
	
	private DualListModel<Role> rolesPickList;
	
	public DualListModel<Role> getRolesPickList() {
		List<Role> target = new ArrayList<Role>();
		java.util.List<redes_digitais_controle_de_notas.domain.entity.Role> fieldValue = getBean().getRoles();
		if (fieldValue != null) {
			for (Role item: fieldValue) {
				target.add(item);
			}
		}
		List<Role> source = new ArrayList<Role>();
		Role example = new Role();
		example.setActive(Boolean.TRUE);
		List<Role> list = roleBC.findByExample(example);
		if (list != null) {
			for (Role item: list) {
				if (!target.contains(item)) {
					source.add(item);
				}
			}
		}
		rolesPickList = new DualListModel<Role>(source, target);
		return rolesPickList;
	}
	
	public void setRolesPickList(DualListModel<Role> rolesPickList) {
		this.rolesPickList = rolesPickList;
	}
	
	public void rolesPickListValueChangeListener(javax.faces.event.ValueChangeEvent event) {
		Object eventObject = event.getNewValue();
		if (eventObject != null && eventObject instanceof DualListModel) {
			DualListModel<?> dualListModel = (DualListModel<?>)eventObject;
			List<?> target = dualListModel.getTarget();
			List<Role> fieldValue = new ArrayList<Role>();
			if (target != null) {
				for (Object item: target) {
					if (item instanceof Role) {
						fieldValue.add((Role)item);
					}
				}
			}
			getBean().setRoles(fieldValue);
		}
	}
	
	@Override
	@Transactional
	public String insert() {
		this.userBC.insert(getBean());
		messageContext.add(new DefaultMessage("{pages.msg.insertsuccess}"));
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.userBC.update(getBean());
		messageContext.add(new DefaultMessage("{pages.msg.updatesuccess}"));
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String delete() {
		this.userBC.delete(getBean().getId());
		messageContext.add(new DefaultMessage("{pages.msg.deletesuccess}"));
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.userBC.load(getId()));
	}

}
