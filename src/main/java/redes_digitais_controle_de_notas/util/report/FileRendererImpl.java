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
 
package redes_digitais_controle_de_notas.util.report;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.enterprise.inject.Alternative;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.util.Faces;
import br.gov.frameworkdemoiselle.util.FileRenderer;

/**
 * Responsible for displaying the contents of files in the browser.
 * 
 * @author Grupo e-Gen
 */
@Alternative
public class FileRendererImpl implements FileRenderer {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Override
	public void render(final byte[] byteArray, final ContentType contentType, final String fileName, boolean forceDownload) {
		logger.debug("Renderizando para o arquivo " + fileName + ".");

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		BufferedInputStream input = null;
		BufferedOutputStream outputStream = null;
		final int DEFAULT_BUFFER_SIZE = 10240;
		try {
			input = new BufferedInputStream(new ByteArrayInputStream(byteArray), DEFAULT_BUFFER_SIZE);
			// Init servlet response.
			response.reset();
			response.setHeader("Content-Type", contentType.getContentType());
			response.setHeader("Content-Length", String.valueOf(byteArray.length));
			response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
			context.responseComplete();
			outputStream = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

			//Write file contents to response.
			byte[] outputBuffer = new byte[DEFAULT_BUFFER_SIZE];
			int length;
			while ((length = input.read(outputBuffer)) > 0) {
				outputStream.write(outputBuffer, 0, length);
			}

			// Finalize task.
			outputStream.flush();
		} catch (IOException e) {
			logger.error("Erro na gera\u00e7\u00e3o do relat\u00f3rio. Incluindo a exce\u00e7\u00e3o de erro em um FacesMessage", e);
			Faces.addMessage(e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// Do your thing with the exception. Print it, log it or mail it. It may be useful to 
					// know that this will generally only be thrown when the client aborted the download.
					logger.info("Erro no fechamento do relat\u00f3rio. Incluindo a exce\u00e7\u00e3o de erro em um FacesMessage", e);
					Faces.addMessage(e);
				}
			}
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					// Do your thing with the exception. Print it, log it or mail it. It may be useful to 
					// know that this will generally only be thrown when the client aborted the download.
					logger.info("Erro no fechamento do relat\u00f3rio. Incluindo a exce\u00e7\u00e3o de erro em um FacesMessage", e);
					Faces.addMessage(e);
				}
			}
		}
	}
	
	@Override
	public void render(final byte[] byteArray, final ContentType contentType, final String fileName) {
		render(byteArray, contentType, fileName, false);
	}

	@Override
	public void render(final InputStream stream, final ContentType contentType, final String fileName, boolean forceDownload) {
		logger.debug("Renderizando o arquivo " + fileName + ".");
		render(getBytes(stream), contentType, fileName, forceDownload);
	}
	
	@Override
	public void render(final InputStream stream, final ContentType contentType, final String fileName) {
		render(stream, contentType, fileName, false);
	}

	@Override
	public void render(File file, ContentType contentType, String fileName, boolean forceDownload) {
		logger.debug("Renderizando para o arquivo " + fileName + ".");
		try {
			render(new FileInputStream(file), contentType, fileName, forceDownload);
		} catch (FileNotFoundException e) {
			logger.info("Erro na gera\u00e7\u00e3o do relat\u00f3rio. Incluindo a exce\u00e7\u00e3o de erro em um FacesMessage", e);
			Faces.addMessage(e);
		}
	}
	
	@Override
	public void render(File file, ContentType contentType, String fileName) {
		render(file, contentType, fileName, false);
	}

	private byte[] getBytes(InputStream stream) {
		byte[] byteArray = null;
		try {
			int thisLine;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while ((thisLine = stream.read()) != -1) {
				bos.write(thisLine);
			}
			bos.flush();
			byteArray = bos.toByteArray();

			if (bos != null) {
				bos.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return byteArray;
	}

}
