package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.PlayerDetails;

/**
 * @author {Nathaniel Essick} - nlessick
 * CIS175 - Spring 2021
 * {3/3/2021}
 */
public class PlayerDetailsHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConsoleFantasyFootballList");
	
	public void insertNewPlayerDetails(PlayerDetails p) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<PlayerDetails> getLists() {
		EntityManager em = emfactory.createEntityManager();
		List<PlayerDetails> allDetails = em.createQuery("SELECT d FROM PlayerDetails d").getResultList();
		return allDetails;
	}
	
	public void deletePlayer(PlayerDetails toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PlayerDetails> typedQuery = em.createQuery("select detail from PlayerDetails detail where detail.id = :selectedId", PlayerDetails.class);
		
		// Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedId", toDelete.getId());
		
		// we only want one result
		typedQuery.setMaxResults(1);
		
		// get the result and save it into a new list item
		PlayerDetails result = typedQuery.getSingleResult();
		
		// remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public PlayerDetails searchForTeamDetailsById(Integer tempId) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		PlayerDetails found = em.find(PlayerDetails.class, tempId);
		em.close();
		return found;
	}
	
	public void updateList(PlayerDetails toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

}
