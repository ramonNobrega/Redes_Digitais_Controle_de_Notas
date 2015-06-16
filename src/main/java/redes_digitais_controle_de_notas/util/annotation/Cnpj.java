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
 
package redes_digitais_controle_de_notas.util.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import redes_digitais_controle_de_notas.util.validation.CnpjValidator;

@Documented
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@Constraint(validatedBy = CnpjValidator.class)
public @interface Cnpj {

	Class<?>[] groups() default {};

	String message() default "{br.gov.frameworkdemoiselle.cnpj}";

	Class<? extends Payload>[] payload() default {};
	
}
