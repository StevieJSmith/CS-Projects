package dev.sjs;

import dev.model.*;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Datasource datasource = new Datasource();
		if(!datasource.open()) {
			System.out.println("Can't open the Datasource!!!");
			return;
		}

		Scanner scan = new Scanner(System.in);
		boolean isOn = true;

		while(isOn) {
			displayMenu();
			String option = scan.nextLine();
			if(option.equalsIgnoreCase("1")) {
				int order = chooseOrder(scan);
				List<Game> games = datasource.queryGames(order);

				if(games == null) {
					System.out.println("No Game's found!");
					return;
				}

				System.out.println("-----");

				for(Game game: games) {
					System.out.println("ID: " + game.getId() + ", Title: " + game.getTitle() + ", Release Date: " + game.getReleaseDate());
				}

				System.out.println("-----");
			}
			else if(option.equalsIgnoreCase("2")) {
				System.out.println("Choose a year:");
				String year = scan.nextLine();
				List<Game> games = datasource.queryYearForGame(year);

				try {
					if (games == null || Integer.parseInt(year) < 1995 || Integer.parseInt(year) > 2024) {
						System.out.println("-----");
						System.out.println("No Game's found!");
						System.out.println("-----");
						continue;
					}
				} catch (NumberFormatException e) {
					System.out.println("-----");
					System.out.println("Invalid input!");
					System.out.println("-----");
					continue;
				}

				System.out.println("-----");

				for(Game game: games) {
					System.out.println("-> " + game.getTitle());
				}
				System.out.println("-----");
			}
			else if(option.equalsIgnoreCase("3")) {
				System.out.println("Choose a genre:");
				String genre = scan.nextLine();
				List<Game> games = datasource.queryGenreForGame(genre);

				if(games.isEmpty()) {
					System.out.println("-----");
					System.out.println("No Game's found!");
					System.out.println("-----");
					continue;
				}

				System.out.println("-----");

				for(Game game: games) {
					System.out.println("-> " + game.getTitle());
				}

				System.out.println("-----");
			}
			else if(option.equalsIgnoreCase("4")) {
				System.out.println("Choose a platform:");
				String platform = scan.nextLine();
				List<Game> games = datasource.queryPlatformForGame(platform);

				if(games.isEmpty()) {
					System.out.println("-----");
					System.out.println("No Game's found!");
					System.out.println("-----");
					continue;
				}

				System.out.println("-----");

				for(Game game: games) {
					System.out.println("-> " + game.getTitle());
				}

				System.out.println("-----");
			}
			else if(option.equalsIgnoreCase("5")) {
				System.out.println("Choose a game");
				String game = scan.nextLine();

				List<Platform> platforms = datasource.queryGameForPlatforms(game);

				if(platforms.isEmpty()) {
					System.out.println("-----");
					System.out.println("No platform's found!");
					System.out.println("-----");
					continue;
				}

				System.out.println("-----");

				for(Platform platform: platforms) {
					System.out.println("-> " + platform.getName());
				}

				System.out.println("-----");
			}
			else if(option.equalsIgnoreCase("6")) {
				System.out.println("Choose a game:");
				String game = scan.nextLine();

				List<Genre> genres = datasource.queryGameForGenre(game);

				if(genres.isEmpty()) {
					System.out.println("-----");
					System.out.println("No Genre's found!");
					System.out.println("-----");
					continue;
				}

				System.out.println("-----");

				for(Genre genre: genres) {
					System.out.println("-> " + genre.getName());
				}

				System.out.println("-----");
			}
			else if(option.equalsIgnoreCase("7")) {
				System.out.println("Choose a game:");
				String game = scan.nextLine();

				List<GameReview> gameReviews = datasource.queryGameForScore(game);

				if(gameReviews.isEmpty()) {
					System.out.println("-----");
					System.out.println("No Score's found!");
					System.out.println("-----");
					continue;
				}

				System.out.println("-----");

				for(GameReview gameReview: gameReviews) {
					System.out.println("-> " + gameReview.getScore());
				}

				System.out.println("-----");



			}
			else if(option.equalsIgnoreCase("X")) {
				System.out.println("Database shutting down...");
				isOn = false;
			}else{
				System.out.println("-----");
				System.out.println("Please choose a valid option (1,2,3,4,5,6,7 or X)");
				System.out.println("-----");
			}

		}

	}

	public static void displayMenu() {
		System.out.println("""
					======================================================
					       				Game Database
					======================================================
					1 - All Games		| 5 - Platform (from Game)
					2 - Game (from Year)	| 6 - Genre (from Game)
					3 - Game (from Genre)	| 7 - Review Score (from Game)
					4 - Game (from Platform)| X - Exit
					------------------------------------------------------
					  				Please choose an option
					  			from above (1,2,3,4,5,6,7 or X):
					------------------------------------------------------
					   """);
	}

	public static int chooseOrder(Scanner scan) {
		System.out.println("Choose a order to display in (ASC, DESC or NONE):");
		int order = switch (scan.nextLine().toUpperCase()) {
			case "ASC" -> Datasource.ORDER_BY_ASC;
			case "DESC" -> Datasource.ORDER_BY_DESC;
			default -> Datasource.ORDER_BY_NONE;
		};
		return order;
	}

}
