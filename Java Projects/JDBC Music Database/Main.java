package dev.sjs;

import dev.model.Artist;
import dev.model.Datasource;
import dev.model.SongArtist;

import java.util.List;

public class Main {

	public static void main(String[] args) {

		Datasource datasource = new Datasource();
		if(!datasource.open()) {
			System.out.println("Can not open Datasource!!!");
			return;
		}

		List<Artist> artists = datasource.queryArtists(Datasource.ORDER_BY_DESC);
		if ( artists == null) {
			System.out.println("No artists!!!");
			return;
		}

		for(Artist artist : artists) {
			System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
		}

		System.out.println("-----");


		List<String> albumsForArtist = datasource.queryAlbumsForArtist("Iron Maiden", Datasource.ORDER_BY_ASC);

		int i = 1;
		for(String album: albumsForArtist) {

			System.out.println("Album " + i++ + " = " + album);
		}

		System.out.println("-----");

		List<SongArtist> songArtists = datasource.queryArtistsForSong("Go Your Own Way", datasource.ORDER_BY_ASC);

		if ( songArtists == null) {
			System.out.println("No artists!!!");
			return;
		}

		for(SongArtist songArtist : songArtists) {
			System.out.println("Artist name = " + songArtist.getArtistName() +
					", Album name = " + songArtist.getAlbumName() +
					", Track = " + songArtist.getTrack());
		}

		System.out.println("end-----");

		datasource.queryMetaData(Datasource.TABLE_SONGS);

		System.out.println("-----");

		datasource.queryMetaData(Datasource.TABLE_ALBUMS);

		System.out.println("-----");

		datasource.queryMetaData(Datasource.TABLE_ARTISTS);

		System.out.println("-----");

		int count = datasource.getCount(Datasource.TABLE_SONGS);
		System.out.println("Number of songs is : " + count);

		System.out.println("-----");

		songArtists = datasource.querySongInfoView("Killer Queen");
		// scanner can be used for song title, sql injection prevented by prepared statement with ?

		if (songArtists.isEmpty()) {
			System.out.println("Couldn't find a song with the provided title!!!");
			return;
		}

		for(SongArtist artist : songArtists) {
			System.out.println("Artist name: " + artist.getArtistName() +
					", Album name: " + artist.getAlbumName() +
					", Track number: " + artist.getTrack());
		}

		System.out.println("-----");

		datasource.insertSong("Like A Rolling Stone", "Bob Dylan", "Bob Dylan's Greatest Hits", 5);


		datasource.close();

	}

}
