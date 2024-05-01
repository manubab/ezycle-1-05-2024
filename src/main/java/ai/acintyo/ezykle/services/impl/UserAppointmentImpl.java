package ai.acintyo.ezykle.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ai.acintyo.ezykle.bindings.UserAppointmentForm;
import ai.acintyo.ezykle.entities.EzServiceAppointment;
import ai.acintyo.ezykle.exception.DataNotFoundException;
import ai.acintyo.ezykle.repositories.ServiceAppointmentRepo;
import ai.acintyo.ezykle.services.UserAppointmentService;
import ai.acintyo.ezykle.util.FormatterDateTime;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConfigurationProperties(prefix="user.appointment")
@Setter
public class UserAppointmentImpl implements UserAppointmentService {

	private String saveError;
	private String appointmentsNotAvailable;
	private String appointmentNotAvailable;
	private String appointmentDeleted;
	
	@Autowired
	ServiceAppointmentRepo appointmentRepo;
	@Autowired
	private FormatterDateTime formatteDateTime;

	@Override
	public EzServiceAppointment bookAppointment(UserAppointmentForm appointmentForm) {

		EzServiceAppointment appointment = new EzServiceAppointment();

		appointment.setName(appointmentForm.getName());
		appointment.setPhno(appointmentForm.getPhno());
		appointment.setVehicalModel(appointmentForm.getVehicalModel());
		appointment.setServiceRequestDate(formatteDateTime.formatLocalDate());
		appointment.setServiceRequestTime(formatteDateTime.formatLocalTime());
		appointment.setServiceType(appointmentForm.getServiceType());
		appointment.setRegistrationDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		appointment.setServiceOptedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")));
		appointment.setLastUpdatedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")));

		try {
			log.info("Appointment booked successfully for: {}");

			return appointmentRepo.save(appointment);
		} catch (Exception e) {
			log.error("Error booking appointment for: {}",
					e.getMessage(), e);

			throw new RuntimeException(saveError, e);
		}

	}

	@Override
	public Page<EzServiceAppointment> fetchAllAppointments(Pageable pageable) {
		Page<EzServiceAppointment> page = appointmentRepo.findAll(pageable);
		if (page.isEmpty()) {
			throw new DataNotFoundException(appointmentsNotAvailable);
		} else {
			return page;
		}
	}

	@Override
	public EzServiceAppointment fetchAppointementById(Integer id) {
		Optional<EzServiceAppointment> opt = appointmentRepo.findById(id);
		if (opt.isEmpty()) {
			throw new IllegalArgumentException(appointmentNotAvailable + id);
		} else {
			return opt.get();
		}
	}
	@Override
	public EzServiceAppointment updateServiceAppointmentById(Integer id, UserAppointmentForm appointmentForm) {
		
	 EzServiceAppointment existAppointment= appointmentRepo.findById(id).orElseThrow(()->new IllegalArgumentException(appointmentNotAvailable));
	 existAppointment.setName(appointmentForm.getName());
		existAppointment.setPhno(appointmentForm.getPhno());
		existAppointment.setVehicalModel(appointmentForm.getVehicalModel());
		existAppointment.setServiceRequestDate(formatteDateTime.formatLocalDate());
		existAppointment.setServiceRequestTime(formatteDateTime.formatLocalTime());
		existAppointment.setServiceType(appointmentForm.getServiceType());
		existAppointment.setLastUpdatedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")));
	 return  appointmentRepo.save(existAppointment);

	}
	@Override
	public String deleteAppointmentById(Integer id) {
		EzServiceAppointment appointment = appointmentRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Appointment not available by given id:"+id));
		appointmentRepo.delete(appointment);
		return appointmentDeleted +id;
	}
	
	
}
