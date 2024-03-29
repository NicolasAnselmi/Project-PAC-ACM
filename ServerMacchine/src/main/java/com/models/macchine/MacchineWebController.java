package com.models.macchine;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MacchineWebController {
	
	@Autowired
	private ServiceMacchina serviceMacchine;
	
	@PostMapping("/macchine/aggiungi")
	public Macchina aggiungiMacchina(@RequestParam String idMacchina, @RequestParam String tipoMacchina) {
		return serviceMacchine.aggiungiMacchina(idMacchina, tipoMacchina);
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
	
	@PostMapping("/macchine/update")
	public boolean updateMacchina(@RequestParam String idMacchina, @RequestParam String codiceLotto, @RequestParam String statoMacchina) {
		return serviceMacchine.updateMacchina(idMacchina, codiceLotto, statoMacchina);
	}

}
