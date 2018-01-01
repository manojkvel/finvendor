/**
 * 
 */
package com.finvendor.util;

import java.util.Arrays;
import java.util.List;

/**
 * @author rayulu vemula
 *
 */
public final class AssetSecurityTypes {

public enum Assets {
		
		// Enum types
		
		EQUITIES("Equities"),
		
		FI("FI"),
		
		INDICES("Indices"),
		
		DERIVATIVES("Derivatives"),
		
		FX("FX"),
		
		AI("AI"),
		
		MISC("Misc");
		
		private Assets(final String name) {
			this.name = name;
		}
 
		private String name;

		public String getName() {
			return name;
		}
		
	} 

public enum SecurityTypes {
		
		EQUITIES ("Common-Stocks", "Preferreds", "Warrants/Rights", "CFDs", "REITs", "Depository Receipts"),
		
		FI ("Govt Long-term Bonds", "Money Markets", "Corp bonds/ Convertibles", "Municipals Bonds", "Securitized Products"),
		
		INDICES ("Equity Index/ETF", "Fixed Income Indices/ETFs", "Derivative Indices/ETFs", "FX/Currency Indices/ETFs", "Commodity Indices/ETFs", "Hedge Fund Indices", "Mutual Funds", "PE Indices", "Cross Asset ETFs", "Sharia Compliant Indices/ETFs/Funds"),
		
		DERIVATIVES ("Exchg-Options", "Exchg-Futures Outrights", "Exchg-Futures Spreads", "OTC Products", "Commodity spot"),
		
		FX("Spot Market"),
		
		AI("Hedge-Funds", "Fund of Funds", "Commodity-Pool (CTA)", "PE", "VC", "Real-estates"),
		
		MISC("Financial Calendar", "Entity Data", "Economic Data", "Credit-Rating", "Earnings forecast", "Fundamental data");

		private final List<String> values;

		SecurityTypes(String ...values) {
	        this.values = Arrays.asList(values);
	    }

	    public List<String> getValues() {
	        return values;
	    }
		
	} 
}
