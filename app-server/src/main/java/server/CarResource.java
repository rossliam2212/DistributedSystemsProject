package server;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/cars")
public class CarResource {	
	@GET
	@Produces( {MediaType.APPLICATION_XML} )
	public List<Car> getCars() {
		try (CarDao carDao = new CarDao()) {
			return carDao.getCars();
		} catch (final Exception e ) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	@GET
	@Produces( {MediaType.APPLICATION_XML} )
	@Path("/id/{carId}")
	public Car getCarByID(@PathParam("carId") String id) {
		try (CarDao carDao = new CarDao()) {
			return carDao.getCarByID(Integer.parseInt(id));
		} catch (final Exception e ) {
			e.printStackTrace();
		}
		return new Car();
	}

	
//	@GET
//	@Produces( {MediaType.APPLICATION_XML} )
//	@Path("/manufacturer/{carMake}")
//	public List<Car> getCarsByMake(@PathParam("carMake") String make) {
//		return CarDao.instance.getCarsByMake(make);
//	}
	
//	@GET
//	@Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
//	@Path("/{carModel}")
//	public List<Car> getCarsByModel(@PathParam("carModel") String model) {
//		return CarDao.instance.getCarsByModel(model);
//	}
//	
//	@GET
//	@Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
//	@Path("/{carYear}")
//	public List<Car> getCarsByYear(@PathParam("carId") String year) {
//		return CarDao.instance.getCarsByYear(Integer.parseInt(year));
//	}
//	
//	@GET
//	@Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
//	@Path("/{carTransmission}")
//	public List<Car> getCarsByTransmission(@PathParam("carTransmission") String transmission) {
//		return CarDao.instance.getCarsByTransmission(transmission);
//	}
}
