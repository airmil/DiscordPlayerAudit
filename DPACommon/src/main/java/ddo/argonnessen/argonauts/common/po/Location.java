package ddo.argonnessen.argonauts.common.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import ddo.argonnessen.argonauts.common.bean.Named;

/**
 * location
 */
@Entity
public class Location implements Named, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	String hexid;
	/**
	 * 
	 */
	String name;
	/**
	 * 
	 */
	@Column(name = "ispublicspace")
	Boolean isPublicSpace;
	/**
	 * 
	 */
	String region;

	/**
	 * @return the hexId
	 */
	public String getHexId() {
		return hexid;
	}

	/**
	 * @param hexId
	 *            the hexId to set
	 */
	public void setHexId(String hexId) {
		this.hexid = hexId;
	}

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the isPublicSpace
	 */
	public Boolean getIsPublicSpace() {
		return isPublicSpace;
	}

	/**
	 * @param isPublicSpace
	 *            the isPublicSpace to set
	 */
	public void setIsPublicSpace(Boolean isPublicSpace) {
		this.isPublicSpace = isPublicSpace;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region
	 *            the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
}
