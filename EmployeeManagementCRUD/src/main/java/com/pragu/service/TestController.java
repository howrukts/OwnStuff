package com.pragu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pragu.service.model.Address;
import com.pragu.service.model.Person;
import com.pragu.service.response.GenericResponse;

@RestController
@RequestMapping("/Persons")
public class TestController {
	
	@Autowired                                                                                                    
    private PersonDAO personDAO;

	/* Fetch all persons
	 * Output : List of persons in json format
	 */
    @RequestMapping(method = RequestMethod.GET)
    public List<Person> getAllPersons() {
    	List<Person> persons = (List<Person>) personDAO.findAll();
    	return persons;
    }
    
    /* Fetch specific person in json format based on the pathvariable-id
     * Input : id of person as path variable
     * Output : Info/details about the specific person
     */
    @RequestMapping(value = "{id}/", method = RequestMethod.GET)
    public Person findById(@PathVariable int id) {    	
    	Person person = personDAO.findById(id);
    	return person;
    }
    
    /*Add person along with address in json format
     * Input : Json format of person structure along with address
     * Output : 0 if success, non-zero if failure
     */
    @RequestMapping(method = RequestMethod.POST)
    public GenericResponse addPerson(@RequestBody Person person) {
    	
    	GenericResponse response = new GenericResponse();
        try {
          Person newPerson = personDAO.save(person);  
    	  response.setErrorCode(0);
    	  response.setMessage("Person created successfully");
        }
        catch(Exception e) {
          e.printStackTrace();
          response.setErrorCode(-1);
          response.setMessage("Unable to create Person");
        }
    	return response;
    }

    /* Add address to a person based on the person's id provided in the path
     * Input : Json format of address structure.
     * Output : 0 if success, non-zero if failure
     */
    @RequestMapping(value = "addAddress/{id}/", method = RequestMethod.PUT)
    public GenericResponse addAddress(@PathVariable int id, @RequestBody Address address) { 
    	GenericResponse response = new GenericResponse();
    	boolean isAdded = true;
    	try {
    	 Person person = personDAO.findById(id);
    	 if(person==null) {
    		response.setErrorCode(-1);
    	    response.setMessage("Invalid input");
    	    return response;
    	 }
    	 person.setAddress(address);
    	 Person updatedPerson = personDAO.save(person);
    	}
    	catch(Exception e) {
         e.printStackTrace();
         response.setErrorCode(-1);
         response.setMessage("Unable to add address");
    	}      
    	response.setErrorCode(0);
  	    response.setMessage("Address added successfully");
    	return response;
    }
    
    
}
