package redes_digitais_controle_de_notas.view.system;

import java.io.Serializable;
import javax.inject.Named;


import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.DashboardModel;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class HomeBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public HomeBean() {
		homeModel = new DefaultDashboardModel();
		DashboardColumn homeColumn = new DefaultDashboardColumn();
		homeColumn.addWidget("home2Panel");
		homeColumn.addWidget("home3Panel");
		homeModel.addColumn(homeColumn);
	}
	private DashboardModel homeModel;
	
	public DashboardModel getHomeModel() {
		return this.homeModel;
	}

}
