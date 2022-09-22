package proyect.springReactive.cuatroRaya.infraestructure.repository;

import javax.servlet.http.HttpServletRequest;

public interface RequestRepository {
	
	String getClientIp(HttpServletRequest request);
	
}