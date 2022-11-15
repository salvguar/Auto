package com.comdata.auto.service;

import java.util.List;

import com.comdata.auto.model.Auto;
import com.comdata.auto.DTO.AutoDTO;

public interface AutoService {
	
	public List<Auto> showAll();
	
	public AutoDTO add();
	
	public AutoDTO autoToDTO(Auto auto);
	
	public AutoDTO add(Auto auto);
	
	public AutoDTO edit(Auto auto);
	
	public AutoDTO delete(Auto auto);
	
}
