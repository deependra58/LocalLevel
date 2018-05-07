package com.softtech.localLevel.response;

import java.util.List;

import com.softtech.localLevel.model.Lakes;
import com.softtech.localLevel.model.Mountains;
import com.softtech.localLevel.model.ProtectedAreas;
import com.softtech.localLevel.model.Rivers;
import com.softtech.localLevel.model.Waterfall;

public class NaturalResourcesDtoList {
	
	List<MountainsResponseDto> mountains;
	List<RiversResponseDto> rivers;
	List<LakesResponseDto> lakes;
	List<WaterfallResponseDto> waterfalls;
	List<ProtectedAreaResponseDto> protectedAreases;
	public List<MountainsResponseDto> getMountains() {
		return mountains;
	}
	public void setMountains(List<MountainsResponseDto> mountains) {
		this.mountains = mountains;
	}
	public List<RiversResponseDto> getRivers() {
		return rivers;
	}
	public void setRivers(List<RiversResponseDto> rivers) {
		this.rivers = rivers;
	}
	public List<LakesResponseDto> getLakes() {
		return lakes;
	}
	public void setLakes(List<LakesResponseDto> lakes) {
		this.lakes = lakes;
	}
	public List<WaterfallResponseDto> getWaterfalls() {
		return waterfalls;
	}
	public void setWaterfalls(List<WaterfallResponseDto> waterfalls) {
		this.waterfalls = waterfalls;
	}
	public List<ProtectedAreaResponseDto> getProtectedAreases() {
		return protectedAreases;
	}
	public void setProtectedAreases(List<ProtectedAreaResponseDto> protectedAreases) {
		this.protectedAreases = protectedAreases;
	}
	public NaturalResourcesDtoList(List<MountainsResponseDto> mountains, List<RiversResponseDto> rivers,
			List<LakesResponseDto> lakes, List<WaterfallResponseDto> waterfalls,
			List<ProtectedAreaResponseDto> protectedAreases) {
		super();
		this.mountains = mountains;
		this.rivers = rivers;
		this.lakes = lakes;
		this.waterfalls = waterfalls;
		this.protectedAreases = protectedAreases;
	}
	
	
	

}