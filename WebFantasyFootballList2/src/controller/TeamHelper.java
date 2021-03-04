package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Team;

/**
 * @author {Nathaniel Essick} - nlessick
 * CIS175 - Spring 2021
 * {2/10/2021}
 */
public class TeamHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConsoleFantasyFootballList");
	
	public void insertTeam(Team t) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(t);;
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Team> showAllTeams() {
		EntityManager em = emfactory.createEntityManager();
		List<Team> allTeams = em.createQuery("SELECT t FROM Team t").getResultList();
		return allTeams;
	}
	
	public Team findTeam(String nameToLookUp) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Team> typedQuery = em.createQuery("select te from Team te where te.teamName = :selectedName", Team.class);
				typedQuery.setParameter("selectedName",  nameToLookUp);
		Team foundTeam;
		try {
			foundTeam = typedQuery.getSingleResult();
		} catch (NoResultException ex) {
			foundTeam = new Team(nameToLookUp);
		}
		em.close();
		return foundTeam;
	}

}
