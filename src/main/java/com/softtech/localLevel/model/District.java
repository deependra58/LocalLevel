package com.softtech.localLevel.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.softtech.localLevel.util.LocalLevelType;
import com.softtech.localLevel.util.Status;





@Entity
@Table(name="district")
public class District implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	private String district;
	private String area;
	private String population;
	private String headquater;
	private String districtPicture;
	
	@Enumerated(EnumType.STRING)
	private LocalLevelType  localLevelType;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="state_id")
	private State state;
	
	@OneToMany(mappedBy="district", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Vdc> vdc;
	
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Vdc> getVdc() {
		return vdc;
	}

	public void setVdc(List<Vdc> vdc) {
		this.vdc = vdc;
	}

	@OneToMany(mappedBy="district",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<RuralMunicipality> ruralMunicipality;
	
	@OneToMany(mappedBy="district",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Municipality> municipality;
	
	@OneToMany(mappedBy="district",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<SubMetropolitan> subMetropolitan;
	
	@OneToMany(mappedBy="district",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Metropolitan> metropolitan;
	
	
	
	public LocalLevelType getLocalLevelType() {
		return localLevelType;
	}

	public void setLocalLevelType(LocalLevelType localLevelType) {
		this.localLevelType = localLevelType;
	}

	public List<RuralMunicipality> getRuralMunicipality() {
		return ruralMunicipality;
	}

	public void setRuralMunicipality(List<RuralMunicipality> ruralMunicipality) {
		this.ruralMunicipality = ruralMunicipality;
	}

	public List<Municipality> getMunicipality() {
		return municipality;
	}

	public void setMunicipality(List<Municipality> municipality) {
		this.municipality = municipality;
	}

	public List<SubMetropolitan> getSubMetropolitan() {
		return subMetropolitan;
	}

	public void setSubMetropolitan(List<SubMetropolitan> subMetropolitan) {
		this.subMetropolitan = subMetropolitan;
	}

	public List<Metropolitan> getMetropolitan() {
		return metropolitan;
	}

	public void setMetropolitan(List<Metropolitan> metropolitan) {
		this.metropolitan = metropolitan;
	}

	public String getDistrictPicture() {
		return districtPicture;
	}

	public void setDistrictPicture(String districtPicture) {
		this.districtPicture = districtPicture;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getHeadquater() {
		return headquater;
	}

	public void setHeadquater(String headquater) {
		this.headquater = headquater;
	}



	public Long getId() {
		return id;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	

	public void setId(Long id) {
		this.id = id;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	

	public District() {
		super();
	}

	public District(Long id) {
		this.id=id;
	}

	@Override
	public String toString() {
		return "District [id=" + id + ", district=" + district + ", area=" + area + ", population=" + population
				+ ", headquater=" + headquater + ", districtPicture=" + districtPicture + ", localLevelType="
				+ localLevelType + ", state=" + state + ", vdc=" + vdc + ", ruralMunicipality=" + ruralMunicipality
				+ ", municipality=" + municipality + ", subMetropolitan=" + subMetropolitan + ", metropolitan="
				+ metropolitan + "]";
	}
	
	/*@OneToMany(mappedBy="district",cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JsonManagedReference
	private List<NewVdc> vdc;
*/
	
	

	
	
	

}
