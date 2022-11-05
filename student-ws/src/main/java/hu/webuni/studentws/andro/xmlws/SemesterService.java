package hu.webuni.studentws.andro.xmlws;

import javax.jws.WebService;

@WebService
public interface SemesterService {
	public int getFreeSemesterByIdentifier(String identifier);
}
