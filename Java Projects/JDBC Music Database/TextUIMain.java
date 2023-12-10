package dev.sjs;

import dev.model.Artist;
import dev.model.Datasource;
import dev.model.SongArtist;

import java.util.List;
import java.util.Scanner;

public class TextUIMain {

	public static void main(String[] args) {

		Datasource datasource = new Datasource();
		if(!datasource.open()) {
			System.out.println("Can not open Datasource!!!");
			return;
		}

		Scanner scan = new Scanner(System.in);
		boolean isOn = true;

		while(isOn) {
			displayMenu();
			String option = scan.nextLine();
			if (option.equals("1")) {
				int order = chooseOrder(scan);
				List<Artist> artists = datasource.queryArtists(order);

				System.out.println("-----");
				if (artists.isEmpty()) {
					System.out.println("No Artist's Found!!!");
					return;
				}

				for(Artist artist : artists) {
					System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
				}
				System.out.println("-----");


			}
			else if (option.equals("2")) {
				int order = chooseOrder(scan);
				System.out.println("Please enter the artist's name:");
				List<String> albumsForArtist = datasource.queryAlbumsForArtist(scan.nextLine(), order);

				System.out.println("-----");
				if (albumsForArtist.isEmpty()) {
					System.out.println("No album's were found for this Artist!");
				}

				int i = 1;
				for(String album: albumsForArtist) {

					System.out.println("Album " + i++ + " = " + album);
				}
				System.out.println("-----");

			}
			else if (option.equals("3")) {
				System.out.println("Please enter the song of the artist:");
				List<SongArtist> songArtists = datasource.querySongInfoView(scan.nextLine());

				System.out.println("-----");
				if (songArtists.isEmpty()) {
					System.out.println("Couldn't find an Artist from the provided song!");
				}

				for(SongArtist artist : songArtists) {
					System.out.println("Artist name: " + artist.getArtistName() +
							", Album name: " + artist.getAlbumName() +
							", Track number: " + artist.getTrack());
				}
				System.out.println("-----");

			}
			else if (option.equalsIgnoreCase("x")) {
				datasource.close();
				isOn = false;

			}else {
				System.out.println("-----");
				System.out.println("Please choose a valid option (1,2,3 or x)!!!");
				System.out.println("-----");
			}
		}


	}

	public static void displayMenu() {
		System.out.println("""
					============================
					       Music Database
					============================
					1 - All Artists
					2 - Albums (from artist)
					3 - Artist (from song)
					x - Exit
					----------------------------
					  Please choose an option
					  from above (1,2,3 or x):
					----------------------------
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
