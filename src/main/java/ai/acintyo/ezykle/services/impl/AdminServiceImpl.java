package ai.acintyo.ezykle.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.format.DataFormatDetector;

import ai.acintyo.ezykle.bindings.AdminServiceRegForm;
import ai.acintyo.ezykle.bindings.AdminServicesForm;
import ai.acintyo.ezykle.entities.EzAdminServiceCenter;
import ai.acintyo.ezykle.entities.EzAdminServices;
import ai.acintyo.ezykle.exception.DataNotFoundException;
import ai.acintyo.ezykle.repositories.ServiceCenterRepo;
import ai.acintyo.ezykle.repositories.ServicesRepo;
import ai.acintyo.ezykle.services.AdminService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConfigurationProperties(prefix = "admin.service")
@Setter
public class AdminServiceImpl implements AdminService {

	private String centerSaveError;
	private String centerNotFoundError;
	private String centersNotFound;
	private String addError;
	private String servicesNotAvailable;
	private String serviceNotFound;
	private String centerDeleted;
	private String serviceDeleted;

	@Autowired
	private ServiceCenterRepo centerRepo;

	@Autowired
	private ServicesRepo servicesRepo;

	@Override
	public EzAdminServiceCenter serviceRegistration(AdminServiceRegForm serviceRegForm) {

		EzAdminServiceCenter serviceCenter = new EzAdminServiceCenter();
		
		serviceCenter.setCenterName(serviceRegForm.getCenterName());
		serviceCenter.setCenterLocation(serviceRegForm.getCenterLocation());
		serviceCenter.setContact(serviceRegForm.getContact());
		serviceCenter.setEmail(serviceRegForm.getEmail());
		serviceCenter.setCenterOpenTime(serviceRegForm.getOpeningTime().toString());
		serviceCenter.setCenterCloseTime(serviceRegForm.getClosingTime().toString());
		serviceCenter.setRegistrationDate(LocalDate.now().toString());
		serviceCenter.setServiceOptedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")));
		serviceCenter.setLastUpdatedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")));
		try {
			log.info("Service Center registered successfully");
			return centerRepo.save(serviceCenter);

		} catch (Exception e) {
			log.error("Error saving service center: {}", e.getMessage(), e);
			throw new RuntimeException(centerSaveError + e);

		}

	}

	@Override
	public EzAdminServices addService(AdminServicesForm servicesForm) {

		Optional<EzAdminServiceCenter> serviceCenter = centerRepo.findById(servicesForm.getServiceCenterId());
		EzAdminServices adminServices = new EzAdminServices();
		if (serviceCenter.isPresent()) {
			EzAdminServiceCenter ezAdminServiceCenter = serviceCenter.get();
			adminServices.setServiceCenter(ezAdminServiceCenter);
			adminServices.setServiceCenterId(servicesForm.getServiceCenterId());
			adminServices.setServiceName(servicesForm.getServiceName());
			adminServices.setServiceCost(servicesForm.getServiceCost());
			adminServices.setTermsConditions(servicesForm.getTermsAndConditions());
			adminServices.setRegistrationDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			adminServices.setServiceDesc(servicesForm.getServiceDesc());
			adminServices.setServiceOptedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")));
			adminServices.setLastUpdatedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")));
			
				log.info("Service Center registered successfully");

				return servicesRepo.save(adminServices);
		} else {
			throw new IllegalArgumentException(centerNotFoundError);
		}
		

	}

	@Override
	public Page<EzAdminServiceCenter> fetchAllServiceCenters(Pageable pageable) {

		Page<EzAdminServiceCenter> page = centerRepo.findAllServiceCenters(pageable);
		if (page.isEmpty()) {
			throw new DataNotFoundException(centersNotFound);
		} else {
			return page;
		}
	}

	@Override
	public EzAdminServiceCenter fetchServiceCenterById(Integer id) {

		Optional<EzAdminServiceCenter> center = centerRepo.findById(id);
		if (center.isEmpty()) {
			throw new IllegalArgumentException(centerNotFoundError + id);
		} else {
			return center.get();
		}
	}

	@Override
	public String deleteServiceCenter(Integer id) {

		EzAdminServiceCenter serviceCenter = centerRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(centerNotFoundError));
		centerRepo.delete(serviceCenter);
		return centerDeleted;
	}

	@Override
	public Page<EzAdminServices> fetchAllServices(Pageable pageable) {

		Page<EzAdminServices> page = servicesRepo.findAll(pageable);
		if (page.isEmpty()) {
			throw new DataNotFoundException(servicesNotAvailable);
		} else {
			return page;
		}

	}

	@Override
	public EzAdminServices fetchServiceById(Integer id) {
		Optional<EzAdminServices> opt = servicesRepo.findById(id);
		if (opt.isEmpty()) {
			throw new IllegalArgumentException(serviceNotFound + id);
		} else {
			return opt.get();
		}
	}

	@Override
	public EzAdminServiceCenter updateServiceCenterById(Integer id, AdminServiceRegForm serviceRegForm) {

		EzAdminServiceCenter existServiceCenter = centerRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(centerNotFoundError));
		existServiceCenter.setCenterName(serviceRegForm.getCenterName());
		existServiceCenter.setCenterLocation(serviceRegForm.getCenterLocation());
		existServiceCenter.setContact(serviceRegForm.getContact());
		existServiceCenter.setEmail(serviceRegForm.getEmail());
		existServiceCenter.setCenterOpenTime(serviceRegForm.getOpeningTime().toString());
		existServiceCenter.setCenterCloseTime(serviceRegForm.getClosingTime().toString());
		existServiceCenter
				.setLastUpdatedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")));
		return centerRepo.save(existServiceCenter);
	}

	@Override
	public EzAdminServices updateServiceById(Integer id, AdminServicesForm serviceForm) {

		EzAdminServices existService = servicesRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(serviceNotFound));

		existService.setServiceName(serviceForm.getServiceName());
		existService.setServiceCost(serviceForm.getServiceCost());
		existService.setTermsConditions(serviceForm.getTermsAndConditions());
		existService.setServiceDesc(serviceForm.getServiceDesc());
		existService.setLastUpdatedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")));
		return servicesRepo.save(existService);
	}

	@Override
	public String deleteServiceById(Integer id) {

		EzAdminServices serviceObj = servicesRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(serviceNotFound + id));
		servicesRepo.delete(serviceObj);
		return serviceDeleted + id;
	}

	@Override
	public ResponseEntity<?> getAllServicesServiceCenterByServiceCenterId(Integer serviceCenterId) {
		
		if(centerRepo.findById(serviceCenterId).isPresent()) {
			return new ResponseEntity<>(servicesRepo.findByServiceCenterId(serviceCenterId),HttpStatus.OK);
		};
		return new ResponseEntity<>(serviceCenterId+" not exist",HttpStatus.OK);
	}

//	@Override
//	public ResponseEntity<?> getAllServicesServiceCenterByServiceCenterId(Integer serviceCenterId) {
//		List<EzAdminServices> centerServices =servicesRepo.findAllServicesByServiceId(serviceCenterId) ;
//		if(!centerServices.isEmpty()) {
//			return new ResponseEntity<List<EzAdminServices>>(centerServices,HttpStatus.OK);
//		}
//		return new ResponseEntity<String>("center does not exist",HttpStatus.OK);
//	}
}
