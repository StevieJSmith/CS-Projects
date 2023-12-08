package dev.model;

public class SongArtist {

	private String artistName;
	private String albumName;
	private int track;


	public String getArtistName() {
		return artistName;
	}

	public String getAlbumName() {
		return albumName;
	}

	public int getTrack() {
		return track;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public void setTrack(int track) {
		this.track = track;
	}
}
