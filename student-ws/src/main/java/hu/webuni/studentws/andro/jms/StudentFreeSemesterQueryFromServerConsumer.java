package hu.webuni.studentws.andro.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import hu.webuni.student.andro.model.StudentFreeSemesterQuery;
import hu.webuni.studentws.andro.xmlws.SemesterServiceImpl;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class StudentFreeSemesterQueryFromServerConsumer {

	private JmsTemplate jmsTemplate;
	private SemesterServiceImpl semesterServiceImpl;

	@JmsListener(destination = "freesemesterqueryfromserver")
	public void onStudentFreeSemesterQueryFromServer(StudentFreeSemesterQuery fsq) {
		fsq.setFreeSemester(semesterServiceImpl.getFreeSemesterByIdentifier(fsq.getIdentifier()));
		this.jmsTemplate.convertAndSend(fsq.getReplyTopicName(), fsq);
	}

}
