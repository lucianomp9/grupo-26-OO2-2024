//package com.unla.grupo26.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.unla.grupo26.dto.CrearLoteDTO;
//import com.unla.grupo26.entities.Almacen;
//import com.unla.grupo26.entities.Lote;
//import com.unla.grupo26.mappers.LoteMapper;
//import com.unla.grupo26.services.IAlmacenServicio;
//import com.unla.grupo26.services.ILoteServicio;
//
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins = "*")
//public class LoteControlador {
//		@Autowired
//	    private ILoteServicio loteServicicio;
//
//		@Autowired
//	    private IAlmacenServicio almacenServicio;
//
//		@Autowired
//	    private LoteMapper loteMapper;
//
//		 @PostMapping("/lotes")
//	    public ResponseEntity<?> altaNuevoLote(@Valid @RequestBody CrearLoteDTO loteDTO, BindingResult bindingResult) {
//	        if (bindingResult.hasErrors()) {
//	            return ResponseEntity.badRequest().body("Error en los datos recibidos"); // Manejar errores de validación
//	        }
//
//	        try {
//	            Lote nuevoLote = loteMapper.crearLoteDTOToLote(loteDTO);
//
//	            //Asignar el almacén
//	            Almacen almacen = almacenServicio.obtenerAlmacenPorId(loteDTO.getIdAlmacen());
//	            nuevoLote.setAlmacen(almacen);
//
//	            Lote loteCreado = loteServicicio.registrarNuevoLote(nuevoLote);
//	            return ResponseEntity.ok(loteCreado);
//	        } catch (RuntimeException e) {
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
//	        }
//		 }
//}
