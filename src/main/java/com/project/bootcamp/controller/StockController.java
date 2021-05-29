package com.project.bootcamp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bootcamp.StockNotFoundException;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.service.StockService;

@CrossOrigin // liberar a API para ser acessada externamente
@RestController	
@RequestMapping("/stock")
public class StockController {
	
	 @Autowired
	  private StockService stockService;
	
      @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<StockDTO> save(@Valid @RequestBody StockDTO dto){
    	  stockService.save(dto);
    	  return ResponseEntity.ok(dto);
      }
      
      @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<StockDTO> update(@Valid @RequestBody StockDTO dto){
    	  return ResponseEntity.ok(stockService.update(dto));
      }
      
      @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<List<StockDTO>> findAll(){
    	  return ResponseEntity.ok(stockService.listaAll());
      }
      
      @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<StockDTO> findId(@PathVariable Long id) throws StockNotFoundException{
    	  return ResponseEntity.ok(stockService.findById(id));
      }
      
      @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<StockDTO> delete(@PathVariable Long id) throws StockNotFoundException{
    	  return ResponseEntity.ok(stockService.delete(id));
      }
      
      @GetMapping(value = "/today", produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<List<StockDTO>> findByCurrentDate() {
          return ResponseEntity.ok(stockService.findByCurrentDate());
      }

}
