package com.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.services.ServiceMacchina;
import com.models.data.LogMacchina;
import com.models.macchine.Macchina;

@RestController
public class MacchineWebController {
	
	@Autowired
	private ServiceMacchina serviceMacchine;
	
	@PostMapping("/log")
	public void registraLog(@RequestBody LogMacchina log) {
		serviceMacchine.registraLog(log);
	}
	
	@PostMapping("/macchine/aggiungi/{idMacchina}")
	public void aggiungiMacchina(@PathVariable String idMacchina) {
		serviceMacchine.aggiungiMacchina(idMacchina);
	}
	
	@GetMapping("/macchine")
	public List<Macchina> getAllMacchine() {
		return serviceMacchine.getAllMacchine();
	}
	
	@GetMapping("/macchine/info/{idMacchina}")
	public String getInfoMacchina(@PathVariable String idMacchina) {
		return serviceMacchine.getDatiMacchina(idMacchina);
	}
	
	@GetMapping("/macchine/stato/{stato}")
	public List<Macchina> getMacchineByStato(@PathVariable String stato){
		return serviceMacchine.getMacchineByStato(stato);
	}
	
	@DeleteMapping("/macchine/delete/{idMacchina}")
	public boolean rimuoviMacchina(@PathVariable String idMacchina) {
		return serviceMacchine.rimuoviMacchina(idMacchina);
	}

}
