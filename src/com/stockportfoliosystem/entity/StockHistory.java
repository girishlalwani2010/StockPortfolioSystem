
package com.stockportfoliosystem.entity;



import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * @author Girish Lalwani
 * 
 */
@Entity
@Table(name="STOCKHISTORY")
public class StockHistory {
		
    
	/**
	 * Instantiating  StockHistoryCompoundKey
	 */
	private StockHistoryCompoundKey stockHistoryCompoundKey =new StockHistoryCompoundKey();
	


	/** 
	 * Declared field price corresponding to price attribute in table STOCKHISTORY
	 */
	@Column(name="price")
    private float price;
    

	
    
	/**
	 * this method is used to get StockHistory compositeKey
	 * @return the stockHistoryCompoundKey
	 */

	@EmbeddedId
	public StockHistoryCompoundKey getStockHistoryCompoundKey() {
		return stockHistoryCompoundKey;
	}

	/**
	 * this method is used to set  the stockHistoryCompositeKey
	 * @param stockHistoryCompoundKey  
	 */
	
	public void setStockHistoryCompoundKey(StockHistoryCompoundKey stockHistoryCompoundKey) {
		this.stockHistoryCompoundKey = stockHistoryCompoundKey;
	}

	

	/**
	 * this method is used to get  the  current price
	 * @return the current price
	 */
	public float getPrice() {
		return price;
	}
	
	/**
	 *  this method is used to set  the  current price
	 * @param price 
	 */
	public void setPrice(float price) {
		this.price = price;
	}


	

    }
