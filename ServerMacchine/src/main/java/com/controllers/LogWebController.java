package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.models.data.LogMacchina;
import com.services.ServiceLog;

@RestController
public class LogWebController {

	@Autowired
	private ServiceLog logService;

	@PostMapping("/log/addLog")
	public void addLogMacchina(@RequestParam long idLog, @RequestParam String idLogger, @RequestParam String title,
			@RequestParam String body) {
		logService.addLogMacchina(new LogMacchina(idLog, idLogger, title, body));
	}
	
	@GetMapping("/log/{idMacchina}")
	public List<LogMacchina> getLogByIdMacchina(@PathVariable String idMacchina){
		return logService.getLogByIdMacchina(idMacchina);
	}
	
	@GetMapping("/log")
	public List<LogMacchina> getAllLogMacchina(){
		return logService.getAllLogMacchina();
	}

}
