///*************************************************************************
// * 
// *  All Rights Reserved.
// * 
// * NOTICE:  All information contained here in is, and remains
// * the property of Texas Imaginology and its suppliers,
// * if any.  The intellectual and technical concepts contained
// * here in are proprietary to Texas Imaginology. Dissemination of this
// * information or reproduction of this material is strictly forbidden unless
// * prior written permission is obtained from Texas Imaginology.
// * 
// */
//package com.softtech.localLevel.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.softtech.localLevel.model.Address;
//import com.softtech.localLevel.repository.AddressRepository;
//import com.softtech.localLevel.request.AddressCreationRequest;
//
///**
// * <<Description Here>>
// * @author Lothbroke
// * @version 
// * @since , Feb 27, 2018
// */
//@Service
//public class AddressService {
//
//	/**
//	 *<<Add description here>>
//	 * @param addressDto
//	 * @return
//	 * @author
//	 * @since , Modified In: @version, By @author
//	 */
//	
//	private static final Logger LOG = LoggerFactory.getLogger(AddressService.class);
//	
//	@Autowired 
//	AddressRepository addressRepository;
//	
//	public Address createAddress(AddressCreationRequest addressDto) {
//		
//		LOG.debug("Creating Address");
//		Address address=new Address();
//		address.setDistrict(addressDto.getDistrict());
//		address.setState(addressDto.getState());
//		address.setVdc(addressDto.getVdc());
//		address=addressRepository.save(address);
//		return address;
//		
//	}
//	
//	
//
//}
