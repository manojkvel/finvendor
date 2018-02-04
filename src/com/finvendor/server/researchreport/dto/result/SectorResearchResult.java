package com.finvendor.server.researchreport.dto.result;

import com.finvendor.server.researchreport.dto.result.ifc.AbsEquitySectorResearchResult;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
public class SectorResearchResult extends AbsEquitySectorResearchResult {
	private String _PercentageContribToGdp;
	private String researchTone;
	
	public String get_PercentageContribToGdp() {
		return _PercentageContribToGdp;
	}
	public void set_PercentageContribToGdp(String _PercentageContribToGdp) {
		this._PercentageContribToGdp = _PercentageContribToGdp;
	}
	public String getResearchTone() {
		return researchTone;
	}
	public void setResearchTone(String researchTone) {
		this.researchTone = researchTone;
	}
}
