/*
 *  This file is part of Alfred Library.
 *
 *  Alfred Library is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Alfred Library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Alfred Library.  If not, see <http://www.gnu.org/licenses/>.
 */
 /* Alterado pelo Grupo e-Gen */
 
package redes_digitais_controle_de_notas.util.validation;

import org.alfredlibrary.utilitarios.texto.Texto;

/**
 * Validador de CNPJ.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
final public class CNPJ {

	private CNPJ() {}

	/**
	 * <b>Validar um CNPJ.</b><br>
	 * Obtido00 em <a href=
	 * "http://www.javafree.org/artigo/852844/Validacao-de-CNPJ-em-java.html"
	 * >http://www.javafree.org/artigo/852844/Validacao-de-CNPJ-em-java.html</a><br>
	 * 
	 * @param str_cnpj CNPJ a ser validado.
	 * @return Verdadeiro caso seja vÃ¡lido. Falso, caso contrÃ¡rio.
	 */
	public static boolean isValido(String str_cnpj) {
		str_cnpj = Texto.manterNumeros(str_cnpj);
		if (str_cnpj.length() != 14 || "00000000000000".equals(str_cnpj) || "11111111111111".equals(str_cnpj) || "22222222222222".equals(str_cnpj) || "33333333333333".equals(str_cnpj) || "44444444444444".equals(str_cnpj) || "55555555555555".equals(str_cnpj) || "66666666666666".equals(str_cnpj) || "77777777777777".equals(str_cnpj) || "88888888888888".equals(str_cnpj) || "99999999999999".equals(str_cnpj))
			return false;
		
		String cnpj_calc = str_cnpj.substring(0, 12) + org.alfredlibrary.utilitarios.cpfcnpj.CNPJ.gerarDigitoVerificador(str_cnpj.substring(0, 12));
		return str_cnpj.equals(cnpj_calc);
	}

}