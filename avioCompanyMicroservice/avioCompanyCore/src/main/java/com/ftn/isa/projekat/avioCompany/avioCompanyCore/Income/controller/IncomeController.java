package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Income.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.IncomeDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Income.service.IIncomeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RestController //oznacava samo da je ovo rest controller
@RequestMapping("/api/aviocompany/income") //mapira http zahtev sa klasom/metodom na serverskoj strani
@Api(value = "income") //sve ove api anotacije su vezane manje vise za dokumentaciju (moje misljenje)
public class IncomeController
{
	@Autowired
	IIncomeService incomeService;
	
	//READ BY ID
	@GetMapping("/{id}") //skracenica za @RequestMapping(value = "/id", method = RequestMethod.GET)
	//inace metoda GetMapping mapira ovo za url sa get zahtevom
	
	@ApiOperation(value = "Finds one income.", notes = "Returns one income.", httpMethod = "GET")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 404, message = "Not Found")
	})//response entity je kao neki nacin da se kreira custom odgovor koji sadrzi telo, zaglavlje i httpstatus kod odgovora, umesto toga moze se staviti @ResponseBody i to vraca samo podatke, ne i zaglavlje i http kod
	public ResponseEntity<IncomeDTO> getOneIncomeById(@PathVariable("id") Long id) //ovo PathVariable kupi parametar id iz URL-a i smesta u promenljivu Long id kao parametar funkcije
	{
		IncomeDTO incomeDto = incomeService.findOneById(id); //dto (data transfer object) je objekat koji se salje klijentu, a u servisu ce se raditi sa java objektima (pojo vrv)
		//da da, iz baze se izvlace java objekti i u servisu se radi sa njima, a servisne metode vracaju dto koji se ovde uzima i salje kao repsonse klijentu
		
		return (incomeDto.getId()!=null) ? new ResponseEntity<IncomeDTO>(incomeDto, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//ResponseEntity -> odgovor koji moze da sadrzi vise informacija (npr statusni kod, same podatke (metode anotirane sa @ResponseBody sadrze samo telo (body zaglavlje)), head zaglavlje itd..
	
	//READ ALL
	@GetMapping("/all")
	@ApiOperation(value = "Returns all incomes", httpMethod = "GET")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 404, message = "Not Found")
	})
	public ResponseEntity<List<IncomeDTO>> getAllIncomes()
	{
		List<IncomeDTO> incomes = incomeService.findAll();
		
		return (!incomes.isEmpty()) ? new ResponseEntity<List<IncomeDTO>>(incomes, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//INSERT NEW INCOME
	@PostMapping("/")
	@ApiOperation(value = "Create a income.", notes = "Returns the income that has been saved.", httpMethod = "POST")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
						   @ApiResponse(code = 400, message = "Bad request")
	})
	public ResponseEntity<IncomeDTO> addIncome(@RequestBody IncomeDTO dto) //responsebody konvertuje json u nesto koliko sam skapirao
	{
		IncomeDTO inc = incomeService.save(dto);
		
		return (inc != null) ? new ResponseEntity<IncomeDTO>(inc, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	//DELETE INCOME (mozda treba da se brise samo ako se obrise ta kompanija?)
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete income.", notes = "Returns the income that has been deleted.", httpMethod = "DELETE")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 404, message = "Not found")
	})
	public ResponseEntity<IncomeDTO> deleteIncome(@PathVariable("id") Long id)
	{
		IncomeDTO deleted = incomeService.deleteById(id);
		
		return (deleted.getId() != null) ? new ResponseEntity<IncomeDTO>(deleted, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//UPDATE (na trenutni prihod dodati novi, ali da admin nema ovu opciju nego da ide automatski - valjda)
	@PutMapping("/{id}")
	@ApiOperation(value = "Chane income.", notes = "Returns the income that has been changed", httpMethod = "PUT")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 400, message = "Bad request")
	})
	public ResponseEntity<IncomeDTO> changeIncome(@PathVariable("id") Long id, @RequestBody IncomeDTO dto) //RequestBody ide samo kod onih funkcija koje kreiraju nesto, novi objekat, valjda mapira taj java objekat na resurs spreman za slanje 
	//na klijentsku stranu (mada realno ovde se samo kreira novi objekat vtf (vidi rest-slajdove - vezbe3)
	//@ResponseBody ide kod get zahteva, @RequestBodu kod post i put
	{
		IncomeDTO editIncome = incomeService.changeIncome(id, dto);
		
		return (editIncome.getId() != null) ? new ResponseEntity<IncomeDTO>(editIncome, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/*
	 * (RADI)
	 * Prosledjuju se id kompanije za koju se trazi suma prihoda, pocetni i krajnji datum
	 */
	@GetMapping("/getsum/{id}/{startDate}/{endDate}")
	@ApiOperation(value = "Return sum of incomes for concrete time period.", httpMethod = "GET")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 400, message = "BAD REQUEST")
	})
	public ResponseEntity<Float> getSumOfIncomes(@PathVariable("id") Long id, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate)
	{
		DateTimeFormatter format = DateTimeFormatter.ISO_DATE_TIME;//DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		
		String st = LocalDateTime.parse(startDate).format(format);
		String end = LocalDateTime.parse(endDate).format(format);
		
		Float sum = incomeService.getSumOfIncomesByDate(id, LocalDateTime.parse(st), LocalDateTime.parse(end));
		
		return (sum != null) ? new ResponseEntity<Float>(sum, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
	}
}
