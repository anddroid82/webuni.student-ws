package hu.webuni.studentws.andro.xmlws;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class SemesterServiceImpl implements SemesterService {

	@Override
	public int getFreeSemesterByIdentifier(String identifier) {
		return new Random().nextInt(0, 10);
	}

}
