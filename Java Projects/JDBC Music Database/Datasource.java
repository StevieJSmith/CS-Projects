package dev.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Datasource {

	public static final String DB_NAME = "music.db";
	public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

	public static final String TABLE_ALBUMS = "albums";
	public static final String COLUMN_ALBUM_ID = "_id";
	public static final String COLUMN_ALBUM_NAME = "name";
	public static final String COLUMN_ALBUM_ARTIST = "artist";
	public static final int INDEX_ALBUM_ID = 1;
	public static final int INDEX_ALBUM_NAME = 2;
	public static final int INDEX_ALBUM_ARTIST = 3;


	public static final String TABLE_SONGS = "songs";
	public static final String COLUMN_SONG_ID = "_id";
	public static final String COLUMN_SONG_TRACK = "track";
	public static final String COLUMN_SONG_TITLE = "title";
	public static final String COLUMN_SONG_ALBUM = "album";
	public static final int INDEX_SONG_ID = 1;
	public static final int INDEX_SONG_TRACK = 2;
	public static final int INDEX_SONG_TITLE = 3;
	public static final int INDEX_SONG_ALBUM = 4;


	public static final String TABLE_ARTISTS = "artists";
	public static final String COLUMN_ARTIST_ID = "_id";
	public static final String COLUMN_ARTIST_NAME = "name";

	public static final int INDEX_ARTIST_ID = 1;
	public static final int INDEX_ARTIST_NAME = 2;

	public static final int ORDER_BY_NONE = 1;
	public static final int ORDER_BY_ASC = 2;
	public static final int ORDER_BY_DESC = 3;

	public static final String QUERY_ALBUMS_BY_ARTIST_START =
			"SELECT " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " FROM " + TABLE_ALBUMS +
					" INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST +
					" = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
					" WHERE " + TABLE_ARTISTS + "." + COLUMN_ALBUM_NAME + " = \"";
	public static final String QUERY_ARTIST_FOR_SONG_START =
			"SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
					TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
					TABLE_SONGS + "." + COLUMN_SONG_TRACK + " FROM " + TABLE_SONGS +
					" INNER JOIN " + TABLE_ALBUMS + " ON " +
					TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
					" INNER JOIN " + TABLE_ARTISTS + " ON " +
					TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
					" WHERE " + TABLE_SONGS + "." + COLUMN_SONG_TITLE + " = \"";

	public static final String QUERY_ARTISTS_BY_NAME_SORT =
			" ORDER BY " + COLUMN_ARTIST_NAME + " COLLATE NOCASE ";
	public static final String QUERY_ALBUMS_BY_ARTIST_SORT =
			" ORDER BY " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

	public static final String QUERY_ARTIST_FOR_SONG_SORT =
			" ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
					TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

	public static final String TABLE_ARTIST_SONG_VIEW = "artists_list";
	public static final String QUERY_VIEW_SONG_INFO = "SELECT " +
			COLUMN_ALBUM_ARTIST + ", " + COLUMN_SONG_ALBUM + ", " +
			COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
			" WHERE " + COLUMN_SONG_TITLE + " = \"";

	public static final String QUERY_VIEW_SONG_INFO_PREP = "SELECT " + COLUMN_ALBUM_ARTIST + ", " +
			COLUMN_SONG_ALBUM + ", " + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
			" WHERE " + COLUMN_SONG_TITLE + " = ?";


	public static final String INSERT_ARTIST = "INSERT INTO " + TABLE_ARTISTS +
			'(' + COLUMN_ARTIST_NAME + ") VALUES(?)";
	public static final String INSERT_ALBUM = "INSERT INTO " + TABLE_ALBUMS +
			'(' + COLUMN_ALBUM_NAME + ", " + COLUMN_ALBUM_ARTIST + ") VALUES(?, ?)";
	public static final String INSERT_SONG = "INSERT INTO " + TABLE_SONGS +
			'(' + COLUMN_SONG_TRACK + ", " + COLUMN_SONG_TITLE + ", " + COLUMN_SONG_ALBUM + ") VALUES(?, ?, ?)";

	public static final String QUERY_ARTIST = "SELECT " + COLUMN_ARTIST_ID + " FROM " +
			TABLE_ARTISTS + " WHERE " + COLUMN_ARTIST_NAME + " = ?";
	public static final String QUERY_ALBUM = "SELECT " + COLUMN_ALBUM_ID + " FROM " +
			TABLE_ALBUMS + " WHERE " + COLUMN_ARTIST_NAME + " = ?";
	public static final String QUERY_SONG = "SELECT " + COLUMN_SONG_ID + " FROM " +
			TABLE_SONGS + " WHERE " + COLUMN_SONG_TITLE + " = ?";

	private PreparedStatement querySongInfoView;
	private PreparedStatement insertIntoArtists;
	private PreparedStatement insertIntoAlbums;
	private PreparedStatement insertIntoSongs;

	private PreparedStatement queryArtist;
	private PreparedStatement queryAlbum;
	private PreparedStatement querySong;

	private Connection conn;

	public boolean open() {
		try {
			conn = DriverManager.getConnection(CONNECTION_STRING);
			querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);
			insertIntoArtists = conn.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
			insertIntoAlbums = conn.prepareStatement(INSERT_ALBUM, Statement.RETURN_GENERATED_KEYS);
			insertIntoSongs = conn.prepareStatement(INSERT_SONG);
			queryArtist = conn.prepareStatement(QUERY_ARTIST);
			queryAlbum = conn.prepareStatement(QUERY_ALBUM);
			querySong = conn.prepareStatement(QUERY_SONG);


			return true;

		} catch (SQLException e) {
			System.out.println("Open!!! : " + e.getMessage());
			return false;
		}
	}

	public void close() {
		try {
			if (querySongInfoView != null) {
				querySongInfoView.close();
			}
			if (insertIntoArtists != null) {
				insertIntoArtists.close();
			}
			if (insertIntoAlbums != null) {
				insertIntoAlbums.close();
			}
			if (insertIntoSongs != null) {
				insertIntoSongs.close();
			}
			if (queryArtist != null) {
				queryArtist.close();
			}
			if (queryAlbum != null) {
				queryAlbum.close();
			}
			if (querySong != null) {
				querySong.close();
			}

			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Close!!! : " + e.getMessage());
		}
	}

	public List<Artist> queryArtists(int sortOrder) {

		StringBuilder sb = new StringBuilder("SELECT * FROM ");
		sb.append(TABLE_ARTISTS);

		orderBy(sb, sortOrder, QUERY_ARTISTS_BY_NAME_SORT);


		try (Statement statement = conn.createStatement();
			 ResultSet resultSet = statement.executeQuery(sb.toString())) {

			List<Artist> artists = new ArrayList<>();

			while (resultSet.next()) {
				Artist artist = new Artist();
				artist.setId(resultSet.getInt(INDEX_ARTIST_ID));
				artist.setName(resultSet.getString(INDEX_ARTIST_NAME));
				artists.add(artist);
			}

			return artists;

		} catch (SQLException e) {
			System.out.println("Query Failed!!! : " + e.getMessage());
			return null;
		}
	}


	public List<String> queryAlbumsForArtist(String artistName, int sortOrder) {

		StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
		sb.append(artistName);
		sb.append("\"");

		orderBy(sb, sortOrder, QUERY_ALBUMS_BY_ARTIST_SORT);

		System.out.println("SQL statement = " + sb);

		try (Statement statement = conn.createStatement();
			 ResultSet resultSet = statement.executeQuery(sb.toString())) {

			List<String> albums = new ArrayList<>();
			while (resultSet.next()) {
				albums.add(resultSet.getString(1)); // album name returned
			}

			return albums;

		} catch (SQLException e) {
			System.out.println("Query Failed : " + e.getMessage());
			return null;
		}

	}

	public List<SongArtist> queryArtistsForSong(String songName, int sortOrder) {

		StringBuilder sb = new StringBuilder(QUERY_ARTIST_FOR_SONG_START);
		sb.append(songName);
		sb.append("\"");

		orderBy(sb, sortOrder, QUERY_ARTIST_FOR_SONG_SORT);

		System.out.println("SQL statement : " + sb);

		try (Statement statement = conn.createStatement();
			 ResultSet resultset = statement.executeQuery(sb.toString())) {

			List<SongArtist> songArtists = new ArrayList<>();

			while (resultset.next()) {
				SongArtist songArtist = createSongArtist(resultset);
				songArtists.add(songArtist);
			}
			return songArtists;
		} catch (SQLException e) {
			System.out.println("Query Failed : " + e.getMessage());
			return null;
		}
	}

	public void queryMetaData(String table) {
		String sql = "SELECT * FROM " + table;

		try (Statement statement = conn.createStatement();
			 ResultSet resultset = statement.executeQuery(sql)) {

			ResultSetMetaData meta = resultset.getMetaData();
			int numColumns = meta.getColumnCount();
			for (int i = 1; i <= numColumns; i++) {
				System.out.format("Column %d in the " + table + " table is %s\n",
						i, meta.getColumnName(i));

			}
		} catch (SQLException e) {
			System.out.println("Query Failed : " + e.getMessage());
		}
	}

	public int getCount(String table) {
		String sql = "SELECT COUNT(*) AS count, MIN(_id) AS min_id FROM " + table;

		try (Statement statement = conn.createStatement();
			 ResultSet resultset = statement.executeQuery(sql)) {

			int count = resultset.getInt("count");
			int min = resultset.getInt("min_id");

			System.out.format("Count = %d, Min = %d\n", count, min);
			return count;
		} catch (SQLException e) {
			System.out.println("Query Failed : " + e.getMessage());
			return -1;
		}
	}

	public boolean createView() {

		try (Statement statement = conn.createStatement()) {

			statement.execute(TABLE_ALBUMS);  // insert view sql here
			return true;
		} catch (SQLException e) {
			System.out.println("Query Failed : " + e.getMessage());
			return false;
		}
	}

	public List<SongArtist> querySongInfoView(String title) {

		try {
			querySongInfoView.setString(1, title);
			ResultSet resultset = querySongInfoView.executeQuery();

			List<SongArtist> songArtists = new ArrayList<>();
			while (resultset.next()) {
				SongArtist songArtist = createSongArtist(resultset);
				songArtists.add(songArtist);
			}
			return songArtists;

		} catch (SQLException e) {
			System.out.println("Query Failed : " + e.getMessage());
			return null;
		}
	}

	private int insertArtist(String name) throws SQLException {

		queryArtist.setString(1, name);
		ResultSet resultSet = queryArtist.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt(1);
		} else {
			insertIntoArtists.setString(1, name);
			int affectedRows = insertIntoArtists.executeUpdate();

			if (affectedRows != 1) {
				throw new SQLException("Couldn't insert artist!");
			}

			ResultSet generatedKeys = insertIntoArtists.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			} else {
				throw new SQLException("Couldn't get _id for artist!");
			}
		}
	}

	private int insertAlbum(String name, int artistId) throws SQLException {

		queryAlbum.setString(1, name);
		ResultSet resultSet = queryAlbum.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt(1);
		} else {
			insertIntoAlbums.setString(1, name);
			insertIntoAlbums.setInt(2, artistId);
			int affectedRows = insertIntoAlbums.executeUpdate();

			if (affectedRows != 1) {
				throw new SQLException("Couldn't insert album!");
			}

			ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			} else {
				throw new SQLException("Couldn't get _id for album!");
			}
		}
	}

	public void insertSong(String title, String artist, String album, int track) {
		boolean exists = false;
		try {
			querySong.setString(1, title);
			ResultSet resultSet = querySong.executeQuery();
			if (resultSet.next()) {
				System.out.println("Song already exists!!!");
				exists = true;
			} else {
				conn.setAutoCommit(false);

				int artistId = insertArtist(artist);
				int albumId = insertAlbum(album, artistId);

				insertIntoSongs.setInt(1, track);
				insertIntoSongs.setString(2, title);
				insertIntoSongs.setInt(3, albumId);

				int affectedRows = insertIntoSongs.executeUpdate();

				if (affectedRows == 1) {
					conn.commit();
				} else {
					throw new SQLException("The song insert failed!");
				}
			}
		} catch (Exception e) {
			System.out.println("Insert song exception : " + e.getMessage());
			try {
				System.out.println("Preforming rollback...");
				conn.rollback();
			} catch (SQLException e2) {
				System.out.println("Rollback error : " + e2.getMessage());
			}
		} finally {
			try {
				if(!exists) {
					System.out.println("Resetting default commit behaviour!");
					conn.setAutoCommit(true);
				}
			} catch (SQLException e3) {
				System.out.println("Couldn't reset auto-commit : " + e3.getMessage());
			}

		}

	}

	public void orderBy(StringBuilder sb, int sortOrder, String sortMethod) {
		if (sortOrder != ORDER_BY_NONE) {
			sb.append(sortMethod);
			if (sortOrder == ORDER_BY_DESC) {
				sb.append("DESC");
			} else {
				sb.append("ASC");
			}
		}
	}

	public SongArtist createSongArtist(ResultSet resultSet) throws SQLException {
		SongArtist songArtist = new SongArtist();
		songArtist.setArtistName(resultSet.getString(1));
		songArtist.setAlbumName(resultSet.getString(2));
		songArtist.setTrack(resultSet.getInt(3));
		return songArtist;
	}
}
