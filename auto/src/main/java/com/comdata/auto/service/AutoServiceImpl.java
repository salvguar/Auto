package com.comdata.auto.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.comdata.auto.model.Auto;
import com.comdata.auto.DTO.AutoDTO;
import com.comdata.auto.repository.AutoDAO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AutoServiceImpl implements AutoService{

	@Autowired
	private AutoDAO repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Page<Auto> getFirstPage(int pagina){
		try {
			return repository.findAll(PageRequest.of(pagina, 5));
		}catch (Exception e) {
			return null;
		}
		
	}
	
	@Override
	public List<Auto> showAll() {
		return (List<Auto>) repository.findAll();
	}
	
	@Override
	public AutoDTO add() {
		try {
			log.info("aggiungi auto di default");
			Auto auto = new Auto("audi","hd293hh");
			repository.save(auto);
			return autoToDTO(auto);
		}catch (Exception e) {
			log.error("Validazione errata!");
			return null;
		}
		
	}

	@Override
	public AutoDTO add(Auto auto) {
		try {
			log.info("aggiungi auto con POST");
			repository.save(auto);
			return autoToDTO(auto);
		}catch (Exception e) {
			log.error("Validazione errata!");
			return null;
		}
		
	}

	@Override
	public AutoDTO edit(Auto auto) {
		try {
			log.info("modifica auto con PUT");
			repository.save(auto);
			return autoToDTO(auto);
		}catch (Exception e) {
			log.error("Validazione errata!");
			return null;
		}
	}

	@Override
	public AutoDTO delete(Auto auto) {
		try{
			log.info("elimina auto con DELETE");
			repository.delete(auto);
			return autoToDTO(auto);
		}catch (Exception e) {
			log.error("Validazione errata!");
			return null;
		}
		
	}

	@Override
	public AutoDTO autoToDTO(Auto auto) {
		AutoDTO autoDTO = this.modelMapper.map(auto, AutoDTO.class);
		return autoDTO;	
	}
}
