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
 * @since 02/06/2009
 */
final public class CPF {

	private CPF() {
	}

	/**
	 * Verificar se um CPF Ã© vÃ¡lido.
	 * 
	 * @param cpf CPF a ser verificado.
	 * @return Verdadeiro caso seja vÃ¡lido. Falso, caso contrÃ¡rio.
	 */
	public static boolean isValido(String cpf) {
		cpf = Texto.manterNumeros(cpf);
        if (cpf.length() != 11 || "00000000000".equals(cpf) || "11111111111".equals(cpf) || "22222222222".equals(cpf) || "33333333333".equals(cpf) || "44444444444".equals(cpf) || "55555555555".equals(cpf) || "66666666666".equals(cpf) || "77777777777".equals(cpf) || "88888888888".equals(cpf) || "99999999999".equals(cpf))
            return false;
        String numDig = cpf.substring(0, 9);
        return org.alfredlibrary.utilitarios.cpfcnpj.CPF.gerarDigitoVerificador(numDig).equals(cpf.substring(9, 11));
	}

}