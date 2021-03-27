/**
 * 
 */
package ddo.argonnessen.argonauts.playeraudit.po;


/**
 * 
 */
public class Location {

	/**
	 * 
	 */
	String hexId;
	/**
	 * 
	 */
	String name;
	/**
	 * 
	 */
	Integer isPublicSpace;
	/**
	 * 
	 */
	String region;

	/**
	 * @return the hexId
	 */
	public String getHexId() {
		return hexId;
	}

	/**
	 * @param hexId
	 *            the hexId to set
	 */
	public void setHexId(String hexId) {
		this.hexId = hexId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the isPublicSpace
	 */
	public Boolean getIsPublicSpace() {
		return isPublicSpace != null && isPublicSpace > 0;
	}

	/**
	 * @param isPublicSpace
	 *            the isPublicSpace to set
	 */
	public void setIsPublicSpace(Boolean isPublicSpace) {
		if (isPublicSpace == null) {
			this.isPublicSpace = null;
		}
		this.isPublicSpace = isPublicSpace ? 1 : 0;
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
