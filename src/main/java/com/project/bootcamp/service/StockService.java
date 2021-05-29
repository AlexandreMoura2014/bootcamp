package com.project.bootcamp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.bootcamp.StockNotFoundException;
import com.project.bootcamp.exception.BusinessException;
import com.project.bootcamp.exception.NotFoundException;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.model.mapper.StockMapper;
import com.project.bootcamp.reporsitory.StockRepository;
import com.project.bootcamp.util.MessageUtils;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;

	private final StockMapper stockMapper = StockMapper.INSTANCE;

	@Transactional
	public StockDTO save(StockDTO dto) {
		Optional<Stock> stockExiste = stockRepository.findByNameAndDate(dto.getName(), dto.getDate());
		if (stockExiste.isPresent()) {
			throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
		}
		Stock stock = stockMapper.toModel(dto);
		stock = stockRepository.save(stock);
		dto.setId(stock.getId());
		return dto;
	}

	@Transactional
	public StockDTO update(@Valid StockDTO dto) {
		Optional<Stock> stockExiste = stockRepository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());
		if (stockExiste.isPresent()) {
			throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
		}
		Stock stock = stockMapper.toModel(dto);
		stock = stockRepository.save(stock);
		return dto;
	}

	@Transactional(readOnly = true)
	public List<StockDTO> listaAll() {
		List<Stock> listaStock = stockRepository.findAll();
		return listaStock.stream().map(stockMapper::toDTO).collect(Collectors.toList());

	}

	@Transactional(readOnly = true)
	public StockDTO findById(Long id) throws StockNotFoundException {
		Stock stock = verifyIdExists(id);
		return stockMapper.toDTO(stock);
	}

	@Transactional
	public StockDTO delete(Long id) {
		Stock stock = verifyIdExists(id);
		stockRepository.deleteById(id);
		return stockMapper.toDTO(stock);
	}


		
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<StockDTO> findByCurrentDate() {
        return stockRepository.findByCurrentDate(LocalDate.now())
        	.map(stockMapper::toListDTO)	
        	//.map(this::toDTO)	
            .orElseThrow(NotFoundException::new);
    }

	private Stock verifyIdExists(Long id) {
		return stockRepository.findById(id).orElseThrow(NotFoundException::new);
	}
	
//	private List<StockDTO> toDTO(List<Stock> listStock) {
//		return listStock.stream()
//				.map(l -> l.getDTO())
//				.collect(Collectors.toList());
//	}
}
