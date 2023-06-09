package com.example.demo.calculatrice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculs")
public class CalculatriceController {
	
	private final CalculatriceService calculatriceService;
	
	public CalculatriceController(CalculatriceService calculatriceService) {
		this.calculatriceService = calculatriceService;
	}
	
	@GetMapping("/additions")
	public int add(@RequestParam int a, @RequestParam int b) {
		return calculatriceService.add(a, b);
	}
	
	@GetMapping("/soustractions")
	public int sous(@RequestParam int a, @RequestParam int b) {
		return calculatriceService.sous(a, b);
	}
	
	@GetMapping("/multiplications")
	public int mult(@RequestParam int a, @RequestParam int b) {
		return calculatriceService.mult(a, b);
	}
	
	@GetMapping("/divisions")
	public int div(@RequestParam int a, @RequestParam int b) {
		return calculatriceService.div(a, b);
	}
	
	@GetMapping("/modulo")
	public int modulo(@RequestParam int a, @RequestParam int b) {
		return calculatriceService.modulo(a, b);
	}
	
	@PostMapping("/calculs")
	public int calcul(@RequestBody Calculatrice calculatrice) {
		return calculatriceService.calcul(calculatrice);
	}
	
	

}
