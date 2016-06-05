package com.pragu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pragu.service.model.Sensor;
import com.pragu.service.response.GenericResponse;

@RestController
@RequestMapping("/sensors")
public class TestController {
	
	@Autowired                                                                                                    
    private SensorDAO sensorDAO;
	
	/* Fetch all sensors 
	 * Usage : Can be called from UI to list all the sensors and its endpoint mappings in UI
	 * Output : List of sensor objects in json format
	 */
    @RequestMapping(method = RequestMethod.GET)
    public List<Sensor> getAllSensors() {
    	List<Sensor> sensors = (List<Sensor>) sensorDAO.findAll();
    	return sensors;
    }
    
    /* Fetch specific sensor object based on the pathvariable-suid
     * Usage : Can be called from UI to fetch particular sensor's info
     * Input : suid of sensor as path variable
     * Output : Info/details about the specific sensor
     */
    @RequestMapping(value = "{suid}/", method = RequestMethod.GET)
    public Sensor findBySuid(@PathVariable String suid) {
    	Sensor sensor = sensorDAO.findBySuid(suid);
    	return sensor;
    }
    
    /* Fetch specific sensor object based on the pathvariable-euid
     * Usage : Can be called by user from a particular endpoint to get the sponsor data
     * Input : euid of endpoint as path variable
     * Output : Info/details about the specific sensor
     */
    @RequestMapping(value = "endpoint/{euid}/", method = RequestMethod.GET)
    public Sensor findByEuid(@PathVariable String euid) {
    	Sensor sensor = sensorDAO.findByEuid(euid);
    	return sensor;
    }
    
    /*Add sensor
     * Usage : Can be called from UI to create sensor and map it to an endpoint 
     * Input : Json format of sensor structure
     * Output : created sensor object
     */
    @RequestMapping(method = RequestMethod.POST)
    public Sensor addSensor(@RequestBody Sensor sensor) {
      Sensor newSensor = sensorDAO.save(sensor);
      return newSensor;
    }

    /* Update sensor data based on the path variables provided
     * Usage : Can be called from sensor's end to update its values (asssuming that sensor-endpoint mapping is already known/done)
     * Input : sensor's uuid(suid), sensor data(value)
     * Output : 0 if success, non-zero if failure
     */
    @RequestMapping(value = "{suid}/{value}/", method = RequestMethod.PUT)
    public GenericResponse updateSensorData(@PathVariable String suid, @PathVariable int value) { 
    	GenericResponse response = new GenericResponse();
    	try {
    	 Sensor sensor = sensorDAO.findBySuid(suid);
    	 if(sensor==null) {
    		response.setErrorCode(-1);
    	    response.setMessage("Invalid Sensor suid");
    	    return response;
    	 }
    	 sensor.setValue(value);
    	 Sensor updateSensor = sensorDAO.save(sensor);
    	}
    	catch(Exception e) {
         e.printStackTrace();
         response.setErrorCode(-1);
         response.setMessage("Unable to update sensor data");
    	}      
    	response.setErrorCode(0);
  	    response.setMessage("Sensor data updated successfully");
    	return response;
    }
    
    
}
