package com.curso.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.spring.entity.Producto;
import com.curso.spring.payload.response.ResponseCodes;
import com.curso.spring.repository.service.ProductoService;

@CrossOrigin("*") // http://localhost:8080
@RestController
@RequestMapping("/api/productos") // http://localhost:8080/productos
public class ProductoController {

	private static final Logger log = LoggerFactory.getLogger(ProductoController.class);

	@Autowired
	ProductoService productoService;

	// Contrato de respuesta(lo recibe FRONTEND) //Este servicio es protegido para
	// los roles Usuario y administrador -> 1. Objetivo del servicio, lo que voy a
	// solucionar
	// 2. Los atributos que recibe mi API, o microservicio producto -> Lista,
	// parametro, recibo body
	// 3. Respuesta Esperada(Json Body -> (Objeto de tipo producto)) o flujo normal
	// 4. Flujo alternativo -> Excepciones o el Código de error según la
	// arquitectura
	/*
	 * @RequestMapping("/lista-productos") //
	 * http://localhost:8080/productos/lista-productos?producto="FRESAS" public
	 * ResponseEntity<?> getListaProductos(@RequestParam(value = "producto",
	 * required = true) String producto) { List<Producto> listaProductos = new
	 * ArrayList<>(); ResponseEntity<?> response = null; try {
	 * 
	 * listaProductos = productoService.listaTotalProductos(producto); response =
	 * new ResponseEntity<List<Producto>>(listaProductos, HttpStatus.OK);
	 * 
	 * } catch (Exception e) { log.error("Internal Server Error", e.getMessage());
	 * response = new ResponseEntity<List<Producto>>(listaProductos,
	 * HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * return response;
	 * 
	 * }
	 */

	@RequestMapping(value = "/lista-productos", method = RequestMethod.GET) // http://localhost:8080/productos/lista-productos?producto="FRESAS"
	public ResponseEntity<?> getListaProductos(@RequestParam(value = "producto", required = true) String producto) {
		List<Producto> listaProductos = new ArrayList<>();

		listaProductos = productoService.listaTotalProductos(producto);
		System.out.println("TAMAÑO"+listaProductos.size());

		if (listaProductos.isEmpty()) {
			return ResponseEntity.ok(new ResponseCodes(1, "Ocurrió un error inesperado, comuniquese con el administrador"));
		} else {
			return ResponseEntity.ok(listaProductos);
		}
	}

}
