package models;


import java.util.ArrayList;
import java.util.List;


public class NotificationEvent {

	/**
	 * Any relevant ids: user id, group id, session id, project id etc. 
	 */
	private List<Long> ids; 

	/**
	 * Appropriate message for the event that will be displayed
	 */
	private String description; 

	/**
	 * Create a new notification event
	 *
	 * @ param ids 			Any ids that are associated with this event
	 * @ param description  The description of this event
	 */
	public NotificationEvent(List<Long> ids, String description){
		this.ids = ids; 
		this.description = description;
	}


	public void setDescription(String description){
		this.description = description;
	}

	public List<Long> getIds(){
		return ids; 
	}

	public String getDescription(){
		return description; 
	}

	public String toString(){
		return "Event [ Ids = " + ids + ", description = " + description + "]"; 

	}
}