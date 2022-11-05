package hu.webuni.studentws.andro.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hu.webuni.studentws.andro.xmlws.SemesterService;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class WsConfig {

	private Bus bus;
	private SemesterService semesterService;
	
	@Bean
	public Endpoint endpoint( ) {
		EndpointImpl endpointImpl = new EndpointImpl(bus, semesterService);
		endpointImpl.publish("student");
		return endpointImpl;
	}
	
}
