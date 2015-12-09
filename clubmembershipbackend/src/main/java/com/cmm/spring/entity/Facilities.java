package com.cmm.spring.entity;

import java.util.ArrayList;
import java.util.List;

public class Facilities {
	
	List<IndoorFacilities> indoorFacilitiesList=new ArrayList<IndoorFacilities>();
	
	List<OutdoorFacilities> outdoorFacilitiesList=new ArrayList<OutdoorFacilities>();
	
	List<LeisureFacilities> leisureFacilitiesList=new ArrayList<LeisureFacilities>();
	
	
	public Facilities(){}

	public List<IndoorFacilities> getIndoorFacilitiesList() {
		return indoorFacilitiesList;
	}

	public void setIndoorFacilitiesList(List<IndoorFacilities> indoorFacilitiesList) {
		this.indoorFacilitiesList = indoorFacilitiesList;
	}

	public List<OutdoorFacilities> getOutdoorFacilitiesList() {
		return outdoorFacilitiesList;
	}

	public void setOutdoorFacilitiesList(
			List<OutdoorFacilities> outdoorFacilitiesList) {
		this.outdoorFacilitiesList = outdoorFacilitiesList;
	}

	public List<LeisureFacilities> getLeisureFacilitiesList() {
		return leisureFacilitiesList;
	}

	public void setLeisureFacilitiesList(
			List<LeisureFacilities> leisureFacilitiesList) {
		this.leisureFacilitiesList = leisureFacilitiesList;
	}
	
	
	

}
