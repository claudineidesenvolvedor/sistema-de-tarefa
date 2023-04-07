package br.com.gestao.tarefa.util.report;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import br.com.gestao.tarefa.util.enuns.Uteis;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class UtilRelatorios {

	// ABRI UMA NOVA ABA DO NAVEGADOR
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void imprimeRelatorio(String nomeRelatorio, HashMap parametros, List lista, String nomeArquivoSaida)
			throws Throwable {
		try {
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();
			ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
			String path = scontext.getRealPath("/WEB-INF/reports/");
			parametros.put("SUBREPORT_DIR", path + File.separator);
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					scontext.getRealPath("/WEB-INF/reports/") + nomeRelatorio + ".jasper", parametros, dataSource);
			HttpServletResponse res = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			res.setContentType("application/pdf");
			res.setHeader("Content-disposition", "inline;filename=relatorio_" + nomeArquivoSaida + ".pdf");
			byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
			res.getOutputStream().write(b);
			res.getCharacterEncoding();
			facesContext.responseComplete();
		} catch (JRException e) {
			e.printStackTrace();
			throw new Exception("Não foi possível gerar o relatório.", e);
		} catch (FileNotFoundException e) {
			throw new Exception("Arquivo do relatório não encontrado.", e);
		} catch (Exception e) {
			Uteis.MensagemError("Erro ao imprimir relatório: ");
		}
	}

	// ABRIR NO APLICATIVO DE PDF
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void imprimeRelatorios(String nomeRelatorio, HashMap parametros, List lista) throws Exception {
		try {
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista, false);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();
			ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
			String path = scontext.getRealPath("/WEB-INF/reports/");

			parametros.put("SUBREPORT_DIR", path + File.separator);
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					scontext.getRealPath("/WEB-INF/reports/") + nomeRelatorio + ".jasper", parametros, dataSource);
			JasperExportManager.exportReportToPdfFile(jasperPrint, nomeRelatorio + ".pdf");
			File arquivo = new File(nomeRelatorio + ".pdf");
			Desktop.getDesktop().open(arquivo);

		} catch (JRException e) {
			e.printStackTrace();
			throw new Exception("Não foi possível gerar o relatório.", e);
		} catch (FileNotFoundException e) {
			throw new Exception("Arquivo do relatório não encontrado.", e);
		} catch (Exception e) {
			Uteis.MensagemError("Erro ao imprimir relatório: ");
		}
	}

}
