package com.project.bootcamp.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;

@Mapper
public interface StockMapper {
	
	StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    Stock toModel(StockDTO dto);

    StockDTO toDTO(Stock dto);
    
    List<StockDTO> toListDTO(List<Stock> dto);
        
}
