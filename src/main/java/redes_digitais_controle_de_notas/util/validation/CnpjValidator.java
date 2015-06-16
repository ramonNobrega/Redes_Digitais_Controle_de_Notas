/*
 * Demoiselle Framework
 * Copyright (C) 2010 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 * 
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo Ã© parte do Framework Demoiselle.
 * 
 * O Framework Demoiselle Ã© um software livre; vocÃª pode redistribuÃ­-lo e/ou
 * modificÃ¡-lo dentro dos termos da GNU LGPL versÃ£o 3 como publicada pela FundaÃ§Ã£o
 * do Software Livre (FSF).
 * 
 * Este programa Ã© distribuÃ­do na esperanÃ§a que possa ser Ãºtil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implÃ­cita de ADEQUAÃ‡ÃƒO a qualquer MERCADO ou
 * APLICAÃ‡ÃƒO EM PARTICULAR. Veja a LicenÃ§a PÃºblica Geral GNU/LGPL em portuguÃªs
 * para maiores detalhes.
 * 
 * VocÃª deve ter recebido uma cÃ³pia da GNU LGPL versÃ£o 3, sob o tÃ­tulo
 * "LICENCA.txt", junto com esse programa. Se nÃ£o, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a FundaÃ§Ã£o do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
 /* Alterado pelo Grupo e-Gen */
 
package redes_digitais_controle_de_notas.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import redes_digitais_controle_de_notas.util.validation.CNPJ;

import redes_digitais_controle_de_notas.util.annotation.Cnpj;


public class CnpjValidator implements ConstraintValidator<Cnpj, String> {

	@Override
	public void initialize(final Cnpj constraintAnnotation) {
	}

	@Override
	public boolean isValid(String cnpj, final ConstraintValidatorContext context) {
		boolean result = false;
		if ( cnpj == null || "".equals(cnpj) ) {
			result = true;
		} else {
			result = CNPJ.isValido(cnpj);
		}
		return result;
	}

}
