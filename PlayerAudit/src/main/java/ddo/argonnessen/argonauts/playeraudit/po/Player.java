package ddo.argonnessen.argonauts.playeraudit.po;

import java.util.Set;

import ddo.argonnessen.argonauts.common.bean.Named;

/**
 * 
 */
public class Player implements Named {

	/**
	 * 
	 */
	String name;
	/**
	 * 
	 */
	String gender;
	/**
	 * 
	 */
	String race;
	/**
	 * 
	 */
	Integer totalLevel;
	/**
	 * 
	 */
	Set<PlayerClass> classes;
	/**
	 * location
	 */
	Location location;
	/**
	 * 
	 */
	Long groupId;
	/**
	 * 
	 */
	String guild;
	/**
	 * 
	 */
	Integer inParty;
	
	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * @return the race
	 */
	public String getRace() {
		return race;
	}
	
	/**
	 * @param race the race to set
	 */
	public void setRace(String race) {
		this.race = race;
	}
	
	/**
	 * @return the totalLevel
	 */
	public Integer getTotalLevel() {
		return totalLevel;
	}
	
	/**
	 * @param totalLevel the totalLevel to set
	 */
	public void setTotalLevel(Integer totalLevel) {
		this.totalLevel = totalLevel;
	}
	
	/**
	 * @return the classes
	 */
	public Set<PlayerClass> getClasses() {
		return classes;
	}
	
	/**
	 * @param classes the classes to set
	 */
	public void setClasses(Set<PlayerClass> classes) {
		this.classes = classes;
	}
	
	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}
	
	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	
	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}
	
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	/**
	 * @return the guild
	 */
	public String getGuild() {
		return guild;
	}
	
	/**
	 * @param guild the guild to set
	 */
	public void setGuild(String guild) {
		this.guild = guild;
	}
	
	/**
	 * @return the inParty
	 */
	public Integer getInParty() {
		return inParty;
	}
	
	/**
	 * @param inParty the inParty to set
	 */
	public void setInParty(Integer inParty) {
		this.inParty = inParty;
	}
}
