package ddo.argonnessen.argonauts.common.po;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ddo.argonnessen.argonauts.common.bean.Named;
import ddo.argonnessen.argonauts.common.po.key.PlayerKey;

/**
 * 
 */
@Entity
public class Player implements Named, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@EmbeddedId
	private
	PlayerKey key = new PlayerKey();
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
	@OneToMany(mappedBy = "player")
	@NotFound(action = NotFoundAction.IGNORE)
	Set<PlayerClass> playerClasses;
	/**
	 * location
	 */
	@ManyToOne
	@JoinColumn(name = "hexid")
	Location location;
	/**
	 * 
	 */
	@Column(name = "groupid")
	Integer groupId;
	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "guild")
	Guild guild;
	/**
	 * 
	 */
	@Column(name = "inparty")
	Boolean inParty;
	
	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return getKey().getName();
	}
	
	/**
	 * @param name the name to set
	 */
	@Override
	public void setName(String name) {
		this.getKey().setName(name);
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
	 * @return the classes
	 */
	public Set<PlayerClass> getPlayerClasses() {
		return playerClasses;
	}

	/**
	 * @param playerClasses
	 *            the classes to set
	 */
	public void setPlayerClasses(Set<PlayerClass> playerClasses) {
		this.playerClasses = playerClasses;
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
	public Integer getGroupId() {
		return groupId;
	}
	
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	/**
	 * @return the guild
	 */
	public Guild getGuild() {
		return guild;
	}
	
	/**
	 * @param guild the guild to set
	 */
	public void setGuild(Guild guild) {
		this.guild = guild;
	}
	
	/**
	 * @return the inParty
	 */
	public Boolean getInParty() {
		return inParty;
	}
	
	/**
	 * @param inParty the inParty to set
	 */
	public void setInParty(Boolean inParty) {
		this.inParty = inParty;
	}

	@Override
	public String toString() {
		return getKey().toString();
	}

	/**
	 * @return key
	 */
	public PlayerKey getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(PlayerKey key) {
		this.key = key;
	}
}
