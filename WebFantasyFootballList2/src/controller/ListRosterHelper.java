package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import model.ListRoster;

/**
 * @author {Nathaniel Essick} - nlessick
 * CIS175 - Spring 2021
 * {2/10/2021}
 */
public class ListRosterHelper {
	//Entity manager allows the information to persist from the database into Eclipse
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConsoleFantasyFootballList");
	
	public void insertPlayer(ListRoster li) {
		//by adding the model list as an import we can find the roster list class
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public List<ListRoster> showAllPlayers(){
		//create the persistence in the method
		//second line will run through the entire list from the roster that is already created and returns all fields from the database
		EntityManager em = emfactory.createEntityManager();
		List<ListRoster>allPlayers = em.createQuery("Select r FROM ListRoster r").getResultList();
		return allPlayers;
	}
	
	public void deletePlayer(ListRoster toDelete) {
		//this method calls the list that is created in the database
		//we create the persistence using the database
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListRoster> typedQuery = em.createQuery("select li from ListRoster li where li.position = :selectedPosition and li.name = :selectedName", ListRoster.class);
		
		//search the database using a parameter of position and then a name
		typedQuery.setParameter("selectedPosition", toDelete.getPosition());
		typedQuery.setParameter("selectedName", toDelete.getName());
		
		//we only want one result
		typedQuery.setMaxResults(1);
		
		//get the result and save it into a new list item
		ListRoster result	= typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
		}
	
	public ListRoster searchForRosterById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListRoster found = em.find(ListRoster.class, idToEdit);
		em.close();
		return found;
	}

	public void updateRoster(ListRoster toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<ListRoster> searchForRosterByPosition(String positionName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListRoster> typedQuery = em.createQuery("select li from ListRoster li where li.position = :selectedPosition", ListRoster.class);
		typedQuery.setParameter("selectedPosition", positionName);

		List<ListRoster> foundPlayers = typedQuery.getResultList();
		em.close();
		return foundPlayers;
	}
	
	public List<ListRoster> searchForRosterByName(String nameName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListRoster> typedQuery = em.createQuery("select li from ListRoster li where li.name = :selectedName", ListRoster.class);
		typedQuery.setParameter("selectedItem", nameName);

		List<ListRoster> foundPlayers = typedQuery.getResultList();
		em.close();
		return foundPlayers;
	}
	
	//need to close the connection of persistence after making changes to the database
	public void cleanUp(){
		emfactory.close();
	}

}
