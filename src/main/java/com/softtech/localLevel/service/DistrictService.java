package com.softtech.localLevel.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.NotFoundException;
import com.softech.localLevel.request.DistrictCreationRequest;
import com.softech.localLevel.request.DistrictEditRequest;
import com.softtech.localLevel.dto.DistrictDetailsDto;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.Metropolitan;
import com.softtech.localLevel.model.Municipality;
import com.softtech.localLevel.model.RuralMunicipality;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.model.SubMetropolitan;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.MetropolitanRepository;
import com.softtech.localLevel.repository.MunicipalityRepository;
import com.softtech.localLevel.repository.RuralMunicipalityRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.repository.SubMetropolitanRepository;
import com.softtech.localLevel.response.DistrictResponseDto;
import com.softtech.localLevel.response.MetropolitanResponseDto;
import com.softtech.localLevel.response.MunicipalityResponseDto;
import com.softtech.localLevel.response.RuralMunicipalResponseDto;
import com.softtech.localLevel.response.SubMetropolitanResponseDto;
import com.softtech.localLevel.util.Base64Util;
import com.softtech.localLevel.util.FileUtil;
import com.softtech.localLevel.util.LocalLevelType;
import com.softtech.localLevel.util.Status;

@Service
public class DistrictService {

	private static final Logger LOG = LoggerFactory.getLogger(DistrictService.class);
	@Autowired
	DistrictRepository districtRepository;
	@Autowired
	StateRepository stateRepository;
	
	@Autowired 
	RuralMunicipalityRepository ruralMunicipalityRepository;
	
	@Autowired 
	MunicipalityRepository municipalityRepository;
	
	@Autowired
	MetropolitanRepository metropolitanRepository;
	
	@Autowired
	SubMetropolitanRepository subMetropolitanRepository;

	@Transactional
	public List<DistrictResponseDto> listAllDistricts(String state) {

		LOG.info("\n\nRequest Accepted to list all districts from state name\n");
		List<DistrictResponseDto> districtResponseDtoList = new ArrayList<DistrictResponseDto>();
		State states = stateRepository.findByState(state);
		Long id = states.getId();
		
		System.out.println(id);
		List<District> districts = districtRepository.findAllByState(new State(states.getId()));
		System.out.println(districts.toString());

		districts.stream().forEach(u -> {
			DistrictResponseDto districtResponseDto = new DistrictResponseDto();
			districtResponseDto.setDistrict(u.getDistrict());

			districtResponseDtoList.add(districtResponseDto);

		});
		return districtResponseDtoList;

	}

	@Transactional
	public List<DistrictResponseDto> listAllDistricts() {
		List<DistrictResponseDto> districtResponseDtoList = new ArrayList<DistrictResponseDto>();
		List<District> districts = districtRepository.findAll();

		districts.stream().forEach(u -> {
			DistrictResponseDto districtResponseDto = new DistrictResponseDto();
			districtResponseDto.setDistrict(u.getDistrict());

			districtResponseDtoList.add(districtResponseDto);

		});
		return districtResponseDtoList;

	}

	@Transactional
	public DistrictDetailsDto getDistrictDetails(String district) {
		LOG.info("Request accepted to show district details");
		DistrictDetailsDto districtDetailsDto = new DistrictDetailsDto();
		District districts = districtRepository.findByDistrict(district);
		districtDetailsDto.setArea(districts.getArea());
		districtDetailsDto.setHeadquater(districts.getHeadquater());
		districtDetailsDto.setPopulation(districts.getPopulation());
		Long id = districts.getState().getId();
		// System.out.println("Id " + districts.getState().getId());
		State state = stateRepository.findById(id);
		districtDetailsDto.setState(state.getState());
		if (districts.getDistrictPicture() != null) {
			File file = new File(districts.getDistrictPicture());
			try {
				districtDetailsDto.setDistrictPicture(Base64Util.encodeFileToBase64Binary(file));
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				file.deleteOnExit();
			}
		}

		return districtDetailsDto;
	}

	@Transactional
	public void postDistrictPicture(String string, String districts) {
		LOG.info("\n\nRequest accepted to post district picture.");
		District district = districtRepository.findByDistrict(districts);
		if(district==null) {
			throw new NotFoundException("District "+districts+ "not found.");
		}
		File file = null;
		try {
			

			LOG.info("Request Accepted to post district picture");
			file = FileUtil.write(String.valueOf(new Date().getTime()).concat(".").concat("png"), string);
			LOG.info("path {}", file.getAbsolutePath());
			if (file != null)
				district.setDistrictPicture(file.getAbsolutePath());
			districtRepository.save(district);

		} catch (Exception e) {
			e.printStackTrace();
			if (file != null) {
				file.delete();
			}
		}
	}

	public void postDistrictDetail(DistrictCreationRequest districtCreationRequest) {
		District district = new District();
		district.setPopulation(districtCreationRequest.getPopulation());
		district.setArea(districtCreationRequest.getArea());
		// System.out.println(districtCreationRequest.getStateId());
		State state = stateRepository.findOne(districtCreationRequest.getStateId());
		district.setState(state);
		// district.setState(new State(districtCreationRequest.getStateId()));
		district.setDistrict(districtCreationRequest.getDistrict());
		district.setHeadquater(districtCreationRequest.getHeadquater());
		district.setStatus(Status.ACTIVE);
		districtRepository.save(district);

	}

	public District editDistrict(DistrictEditRequest districtEditRequest, String districts, Long districtId) {
		District district =null;
		if(districts==null) {
			district = districtRepository.findById(districtId);
			if(district==null) {
				throw new NotFoundException("District with the id "+districtId+ " not found.");
			}
		}
		else {
			district = districtRepository.findBydistrict(districts);
			if(district==null) {
				throw new NotFoundException("District with the name "+districts+ " not found.");
			}
			district = districtRepository.findBydistrict(districts);
		}
		district.setDistrict(districtEditRequest.getDistrict());
		district.setArea(districtEditRequest.getArea());
		State state = stateRepository.findByState(districtEditRequest.getState());
		if(state==null) {
			throw new NotFoundException(districtEditRequest.getState()+" doesn't exist!");
		}
		district.setState(state);
		district.setPopulation(districtEditRequest.getPopulation());
		district.setHeadquater(districtEditRequest.getHeadquater());
		district.setStatus(Status.ACTIVE);
		district.setLocalLevelType(LocalLevelType.DISTRICT);
		districtRepository.save(district);
		return district;

	}

//	public List<LocalLevelResponseDto> getLocalLevel(String district) {
//		District districts=districtRepository.findByDistrict(district);
//		List<LocalLevelResponseDto> localLevelResponseDtoList= new ArrayList<LocalLevelResponseDto>();
//		List<RuralMunicipality> ruralMunicipalityList=ruralMunicipalityRepository.findAllByDistrict(new District(districts.getId()));
//		List<Municipality> municipalityList=municipalityRepository.findAllBydistrict(new District(districts.getId()));
//		ruralMunicipalityList.stream().forEach(u -> {
//			//List<RuralMunicipalResponseDto> ruralMunicipalResponseDtoList=new ArrayList<RuralMunicipalResponseDto>();
//			RuralMunicipalResponseDto ruralMunicipalResponseDto=new RuralMunicipalResponseDto();
////			ruralMunicipalResponseDto.setRuralMunicipal(u.getRuralMunicipal());
////			ruralMunicipalResponseDto.setLocalLevelType(LocalLevelType.RURAL);
////			
//			localLevelResponseDtoList.add(getRuralMunicipalList(u));
//			
//			
//			
//			// districtResponseDtoList.add(districtResponseDto);
//
//		});
//		municipalityList.stream().forEach(v -> {
//			//List<RuralMunicipalResponseDto> ruralMunicipalResponseDtoList=new ArrayList<RuralMunicipalResponseDto>();
//			//RuralMunicipalResponseDto ruralMunicipalResponseDto=new RuralMunicipalResponseDto();
////			ruralMunicipalResponseDto.setRuralMunicipal(u.getRuralMunicipal());
////			ruralMunicipalResponseDto.setLocalLevelType(LocalLevelType.RURAL);
////			
//			localLevelResponseDtoList.add(getMunicipalList(v));
//			
//			
//			
//			// districtResponseDtoList.add(districtResponseDto);
//
//		});
//		return localLevelResponseDtoList;
//	}
//
//	private LocalLevelResponseDto getMunicipalList(Municipality v) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	

	

	public List<RuralMunicipalResponseDto> getRuralMunicipal(String district) {
		
		District districts=districtRepository.findByDistrict(district);
		List<RuralMunicipalResponseDto> ruralMunicipalResponseDtoList=new ArrayList<RuralMunicipalResponseDto>();
		List<RuralMunicipality> ruralMunicipalityList=ruralMunicipalityRepository.findAllByDistrict(new District(districts.getId()));
		ruralMunicipalityList.stream().forEach(u -> {
		
			RuralMunicipalResponseDto ruralMunicipalResponseDto=new RuralMunicipalResponseDto();
			ruralMunicipalResponseDto.setRuralMunicipal(u.getRuralMunicipal());
			ruralMunicipalResponseDto.setLocalLevelType(LocalLevelType.RURAL);
			ruralMunicipalResponseDtoList.add(ruralMunicipalResponseDto);
			

		});
		return ruralMunicipalResponseDtoList;
	}

	public List<MunicipalityResponseDto> getMunicipality(String district) {
		District districts=districtRepository.findByDistrict(district);
		List<MunicipalityResponseDto> municipalResponseDtoList=new ArrayList<MunicipalityResponseDto>();
		List<Municipality> municipalityList=municipalityRepository.findAllBydistrict(new District(districts.getId()));
		//List<Municipality> municipalityList=municipalityRepository.findAllByDistrict(new District(districts.getId()));
		municipalityList.stream().forEach(u -> {
		
			MunicipalityResponseDto municipalityResponseDto=new MunicipalityResponseDto();
			municipalityResponseDto.setMunicipal(u.getMunicipal());
			municipalityResponseDto.setLocalLevelType(LocalLevelType.MUNICIPAL);
			municipalResponseDtoList.add(municipalityResponseDto);
			//ruralMunicipalResponseDtoList.add(ruralMunicipalResponseDto);
			

		});
		return municipalResponseDtoList;
	}

	public List<MetropolitanResponseDto> getMetropolitan(String district) {
		District districts=districtRepository.findByDistrict(district);
		List<Metropolitan> metropolitanList=metropolitanRepository.findAllByDistrict(new District(districts.getId()));
		List<MetropolitanResponseDto> metropolitanResponseDtoList=new ArrayList<MetropolitanResponseDto>();
		metropolitanList.stream().forEach(u ->{
			MetropolitanResponseDto metropolitanResponseDto=new MetropolitanResponseDto();
			metropolitanResponseDto.setMetropolitan(u.getMetropolitan());
			metropolitanResponseDto.setLocalLevelType(LocalLevelType.METROPOLITAN);
			metropolitanResponseDtoList.add(metropolitanResponseDto);
			
			
			
		});
		return metropolitanResponseDtoList;
		
		
	}

	public List<SubMetropolitanResponseDto> getSubMetropolitan(String district) {
		 District districts=districtRepository.findByDistrict(district);
		 List<SubMetropolitan> subMetropolitanList=subMetropolitanRepository.findAllByDistrict(new District(districts.getId()));
		 List<SubMetropolitanResponseDto> subMetropolitanResponseDtoList=new ArrayList<SubMetropolitanResponseDto>();
		 subMetropolitanList.stream().forEach(u->{
			 SubMetropolitanResponseDto subMetropolitanResponseDto=new SubMetropolitanResponseDto();
			 subMetropolitanResponseDto.setSubMetropolitan(u.getSubMetropolitan());
			 subMetropolitanResponseDto.setLocalLevelType(LocalLevelType.SUBMETROPOLITAN);
			 subMetropolitanResponseDtoList.add(subMetropolitanResponseDto);
			 
			 
			 
		 });
		 return subMetropolitanResponseDtoList;
	}

//	public List<LocalLevelResponseDto> getLocalLevel(String district) {
//	
//		//LocalLevelResponseDto localLevelResponseDto=new LocalLevelResponseDto();
//		District districts=districtRepository.findByDistrict(district);
//		
//		List<LocalLevelResponseDto> localLevelResponseDtoList= new ArrayList<LocalLevelResponseDto>();
//		List<RuralMunicipalResponseDto> ruralMunicipalResponseDtoList=new ArrayList<RuralMunicipalResponseDto>();
//		List<RuralMunicipality> ruralMunicipalityList=ruralMunicipalityRepository.findAllByDistrict(new District(districts.getId()));		
//		List<Municipality> municipalityList=municipalityRepository.findAllBydistrict(new District(districts.getId()));
//		ruralMunicipalityList.stream().forEach(u -> {
//			//List<RuralMunicipalResponseDto> ruralMunicipalResponseDtoList=new ArrayList<RuralMunicipalResponseDto>();
//			RuralMunicipalResponseDto ruralMunicipalResponseDto=new RuralMunicipalResponseDto();
//			ruralMunicipalResponseDto.setRuralMunicipal(u.getRuralMunicipal());
//			
//			ruralMunicipalResponseDtoList.add(ruralMunicipalResponseDto);
//			
//			
//			// districtResponseDtoList.add(districtResponseDto);
//
//		});
//		localLevelResponseDtoList.addAll(ruralMunicipalResponseDtoList)
//		municipalityList.stream().forEach(v -> {
//			List<MunicipalityResponseDto> municipalityResponseDtoList=new ArrayList<MunicipalityResponseDto>();
//			MunicipalityResponseDto municipalityResponseDto=new MunicipalityResponseDto();
//			municipalityResponseDto.setMunicipal(v.getMunicipal());
//			municipalityResponseDtoList.add(municipalityResponseDto);
//			
//			//districtResponseDtoList.add(districtResponseDto);
//
//		});
//		
//		ruralMunicipalResponseDtoList.stream().forEach(w -> {
//			RuralMunicipalResponseDto ruralMunicipalResponseDto=new RuralMunicipalResponseDto();
//			
//			
//			
//			
//
//		});
//		
//		
//		
//		
//		localLevelResponseDtoList.add(ruralMunicipalResponseDtoList);
//
//		
//	}

}
