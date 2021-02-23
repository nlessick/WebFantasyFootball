
import java.util.List;
import java.util.Scanner;

import controller.ListRosterHelper;
import model.ListRoster;

/**
 * @author {Nathaniel Essick} - nlessick
 * CIS175 - Spring 2021
 * {2/10/2021}
 */
public class StartProgram {
	
	static Scanner in = new Scanner(System.in);
	static ListRosterHelper lih = new ListRosterHelper();

	//this method uses the persistence we created with the ListRosterHelper
	//first we get both requirements for the object then send the new object to the method
	private static void addAPlayer() {
		// TODO Auto-generated method stub
		System.out.print("Enter a position: ");
		String position = in.nextLine();
		System.out.print("Enter a name: ");
		String name = in.nextLine();
		ListRoster toAdd = new ListRoster(position,name);
		lih.insertPlayer(toAdd);

	}

	private static void deleteAPlayer() {
		// TODO Auto-generated method stub
		//this method will create a new player that is sent to the deletePlayer method within the list helper
		//it will then use the parameters that was entered, find the player and delete it from the database
		System.out.print("Enter the position to delete: ");
		String position = in.nextLine();
		System.out.print("Enter the name to delete: ");
		String name = in.nextLine();
		ListRoster toDelete = new ListRoster(position, name);
		lih.deletePlayer(toDelete);

	}

	private static void editAPlayer() {
		// TODO Auto-generated method stub
		//method gives the user the option on how to search for a player to edit
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Position");
		System.out.println("2 : Search by Name");
		int searchBy = in.nextInt();
		in.nextLine();
		List<ListRoster> foundPlayers;
		if (searchBy == 1) {
			System.out.print("Enter the position: ");
			String positionName = in.nextLine();
			foundPlayers = lih.searchForRosterByPosition(positionName);
			
		} else {
			System.out.print("Enter the name: ");
			String nameName = in.nextLine();
			foundPlayers = lih.searchForRosterByName(nameName);

		}

		if (!foundPlayers.isEmpty()) {
			System.out.println("Found Results.");
			for (ListRoster l : foundPlayers) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			ListRoster toEdit = lih.searchForRosterById(idToEdit);
			System.out.println("Retrieved " + toEdit.getName() + " from " + toEdit.getPosition());
			System.out.println("1 : Update Position");
			System.out.println("2 : Update Name");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Position: ");
				String newPosition = in.nextLine();
				toEdit.setPosition(newPosition);
			} else if (update == 2) {
				System.out.print("New Name: ");
				String newName = in.nextLine();
				toEdit.setName(newName);
			}

			lih.updateRoster(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();

	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to new fantasty football roster list! ---");
		while (goAgain) {
			System.out.println("*  Select an option:");
			System.out.println("*  1 -- Add a player");
			System.out.println("*  2 -- Edit a player");
			System.out.println("*  3 -- Delete a player");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit the awesome program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addAPlayer();
			} else if (selection == 2) {
				editAPlayer();
			} else if (selection == 3) {
				deleteAPlayer();
			} else if (selection == 4) {
				viewTheList();
			} else {
				lih.cleanUp();
				System.out.println("   Thanks for stopping by!   ");
				goAgain = false;
			}

		}

	}

	private static void viewTheList() {
		// TODO Auto-generated method stub
		//for loop will use the method from the list helper
		List<ListRoster>allPlayers = lih.showAllPlayers();
		for(ListRoster singlePlayer : allPlayers) {
			System.out.println(singlePlayer.returnRosterDetails());
		}
	}

}
