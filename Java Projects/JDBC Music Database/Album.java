package dev.model;

public class Album {

	private int id;
	private String name;
	private int artistId;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getArtistId() {
		return artistId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}
}
