package com.curso.spring.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.jasperreports.JasperReportsUtils;


import com.curso.spring.payload.request.ReportRequest;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@RestController
@RequestMapping("/api/reporte")
public class ReportController {

	@PostMapping(value = "/generar-reporte", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<ByteArrayResource> generarReporte(@RequestBody ReportRequest reportRequest){
		
		String ruta = "C:\\Test";
		ByteArrayResource resource = null;
		File miArchivo = null;
		try {
			//PDF
			InputStream entrada = this.getClass().getResourceAsStream("/MiPrimerReporte.jrxml");
			
			JasperDesign design = JRXmlLoader.load(entrada);
			JasperReport report = JasperCompileManager.compileReport(design);
			Map<String, Object> parametros = new HashMap<>();
			
			parametros.put("MI_PRIMER_PARAMETRO", reportRequest.getNombre());
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList("Reporte"));
			
			miArchivo = new File(ruta);
			
			FileOutputStream salida = new FileOutputStream(miArchivo);
			
			JasperReportsUtils.renderAsPdf(report, parametros, dataSource, salida);
			miArchivo = new File(ruta + "Test.pdf");
			
			Path apuntador = Paths.get(miArchivo.getAbsolutePath());
			resource = new ByteArrayResource(Files.readAllBytes(apuntador));
			
			
			
		} catch (Exception e) {
			System.out.println("ERROR"+e.getMessage());
		}
		
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=Test.pdf")
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.contentLength(miArchivo.length())
				.body(resource);
		
	}
	
}
