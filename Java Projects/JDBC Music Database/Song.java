package dev.model;

public class Song {
	private int id;
	private int track;
	private String name;
	private int albumId;

	public int getId() {
		return id;
	}

	public int getTrack() {
		return track;
	}

	public String getName() {
		return name;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTrack(int track) {
		this.track = track;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}
}
