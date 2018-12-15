package com.finvendor.api.resources.researchreport.equity.dto.result;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
public abstract class AbsEquitySectorResearchResult extends AbsResearchReportResult {
	
	protected String sector;
	
	/**Industry Sub Type*/
	protected String subSector; 
	
	protected String broker;
	
	protected String researchedByCfa;

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getSubSector() {
		return subSector;
	}

	public void setSubSector(String subSector) {
		this.subSector = subSector;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getResearchedByCfa() {
		return researchedByCfa;
	}

	public void setResearchedByCfa(String researchedByCfa) {
		this.researchedByCfa = researchedByCfa;
	}
	
	
}
