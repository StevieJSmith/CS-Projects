package dev.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

	// database connection
	public static final String DB_NAME = "Game_Review.db";
	public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

	// games table
	public static final String TABLE_GAMES = "games";
	public static final String COLUMN_GAME_ID = "_id";
	public static final String COLUMN_GAME_TITLE = "title";
	public static final String COLUMN_GAME_RELEASE_DATE = "release_date";

	// platforms table
	public static final String TABLE_PLATFORMS = "platforms";
	public static final String COLUMN_PLATFORM_ID = "_id";
	public static final String COLUMN_PLATFORM_NAME = "name";

	// genres table
	public static final String TABLE_GENRES = "genres";
	public static final String COLUMN_GENRE_ID = "_id";
	public static final String COLUMN_GENRE_NAME = "name";

	// game_genres table (joins games and genres)
	public static final String TABLE_GAME_GENRES = "game_genres";
	public static final String COLUMN_GAME_GENRES_GAME = "game";
	public static final String COLUMN_GAME_GENRES_GENRE = "genre";

	// game_platforms table (joins games and platforms)
	public static final String TABLE_GAME_PLATFORMS = "game_platforms";
	public static final String COLUMN_GAME_PLATFORMS_GAME = "game";
	public static final String COLUMN_GAME_PLATFORMS_PLATFORM = "platform";

	// game_reviews table (joins games and reviews)
	public static final String TABLE_GAME_REVIEWS = "game_reviews";
	public static final String COLUMN_GAME_REVIEWS_GAME = "game";
	public static final String COLUMN_GAME_REVIEWS_SCORE = "score";



	// Order by
	public static final int ORDER_BY_NONE = 1;
	public static final int ORDER_BY_ASC = 2;
	public static final int ORDER_BY_DESC = 3;

	// Queries
	public static final String QUERY_GAME_BY_TITLE_SORT =
			" ORDER BY " + COLUMN_GAME_TITLE + " COLLATE NOCASE ";

	public static final String QUERY_YEAR_FOR_GAME_PREP =
			" SELECT " + COLUMN_GAME_TITLE + " FROM " + TABLE_GAMES + " WHERE " +
					COLUMN_GAME_RELEASE_DATE + " LIKE ?";
	public static final String QUERY_GENRE_FOR_GAME_PREP =
			" SELECT " + COLUMN_GAME_TITLE + " FROM " + TABLE_GAMES + " INNER JOIN " + TABLE_GAME_GENRES +
					" ON " + TABLE_GAMES + "." + COLUMN_GAME_ID + " = " +
					TABLE_GAME_GENRES + "." + COLUMN_GAME_GENRES_GAME + " INNER JOIN " +
					TABLE_GENRES + " ON " + TABLE_GENRES + "." + COLUMN_GENRE_ID + " = " +
					TABLE_GAME_GENRES + "." + COLUMN_GAME_GENRES_GENRE + " WHERE " +
					COLUMN_GENRE_NAME + " = ?" ;
	public static final String QUERY_PLATFORM_FOR_GAME_PREP =
			" SELECT " + COLUMN_GAME_TITLE + " FROM " + TABLE_GAMES + " INNER JOIN " + TABLE_GAME_PLATFORMS +
					" ON " + TABLE_GAMES + "." + COLUMN_GAME_ID + " = " +
					TABLE_GAME_PLATFORMS + "." + COLUMN_GAME_PLATFORMS_GAME + " INNER JOIN " +
					TABLE_PLATFORMS + " ON " + TABLE_PLATFORMS + "." + COLUMN_PLATFORM_ID + " = " +
					TABLE_GAME_PLATFORMS + "." + COLUMN_GAME_PLATFORMS_PLATFORM + " WHERE " +
					COLUMN_PLATFORM_NAME + " = ?" ;

	public static final String QUERY_GAME_FOR_PLATFORM_PREP =
			" SELECT " + COLUMN_PLATFORM_NAME + " FROM " + TABLE_PLATFORMS + " INNER JOIN " + TABLE_GAME_PLATFORMS +
					" ON " + TABLE_PLATFORMS + "." + COLUMN_PLATFORM_ID + " = " +
					TABLE_GAME_PLATFORMS + "." + COLUMN_GAME_PLATFORMS_PLATFORM + " INNER JOIN " +
					TABLE_GAMES + " ON " + TABLE_GAMES + "." + COLUMN_GAME_ID + " = " +
					TABLE_GAME_PLATFORMS + "." + COLUMN_GAME_PLATFORMS_GAME + " WHERE " +
					COLUMN_GAME_TITLE + " = ?" ;

	public static final String QUERY_GAME_FOR_GENRE_PREP =
			" SELECT " + COLUMN_GENRE_NAME + " FROM " + TABLE_GENRES + " INNER JOIN " + TABLE_GAME_GENRES +
					" ON " + TABLE_GENRES + "." + COLUMN_GENRE_ID + " = " +
					TABLE_GAME_GENRES + "." + COLUMN_GAME_GENRES_GENRE + " INNER JOIN " +
					TABLE_GAMES + " ON " + TABLE_GAMES + "." + COLUMN_GAME_ID + " = " +
					TABLE_GAME_GENRES + "." + COLUMN_GAME_GENRES_GAME + " WHERE " +
					COLUMN_GAME_TITLE + " = ?" ;

	public static final String QUERY_GAME_FOR_SCORE_PREP =
			" SELECT " + COLUMN_GAME_REVIEWS_SCORE + " FROM " +
					TABLE_GAMES + " INNER JOIN " + TABLE_GAME_REVIEWS + " ON " +
					TABLE_GAMES + "." + COLUMN_GAME_ID + " = " + TABLE_GAME_REVIEWS + "." +
					COLUMN_GAME_REVIEWS_GAME + " WHERE " + COLUMN_GAME_TITLE + " = ?";

	// Prepared Statements
	private PreparedStatement queryYearForGame;
	private PreparedStatement queryGenreForGame;
	private PreparedStatement queryPlatformForGame;
	private PreparedStatement queryGameForPlatform;
	private PreparedStatement queryGameForGenre;
	private PreparedStatement queryGameForScore;

	private Connection conn;

	public boolean open() {
		try {
			conn = DriverManager.getConnection(CONNECTION_STRING);
			queryYearForGame = conn.prepareStatement(QUERY_YEAR_FOR_GAME_PREP);
			queryGenreForGame = conn.prepareStatement(QUERY_GENRE_FOR_GAME_PREP);
			queryPlatformForGame = conn.prepareStatement(QUERY_PLATFORM_FOR_GAME_PREP);
			queryGameForPlatform = conn.prepareStatement(QUERY_GAME_FOR_PLATFORM_PREP);
			queryGameForGenre = conn.prepareStatement(QUERY_GAME_FOR_GENRE_PREP);
			queryGameForScore = conn.prepareStatement(QUERY_GAME_FOR_SCORE_PREP);


			return true;

		} catch (SQLException e) {
			System.out.println("Open Error: " + e.getMessage());
			return false;
		}
	}

	public void close() {
		try {
			if(queryYearForGame != null) {
				queryYearForGame.close();
			}
			if(queryGenreForGame != null) {
				queryGenreForGame.close();
			}
			if(queryPlatformForGame != null) {
				queryPlatformForGame.close();
			}
			if(queryGameForPlatform != null) {
				queryGameForPlatform.close();
			}
			if(queryGameForGenre != null) {
				queryGameForGenre.close();
			}
			if(queryGameForScore != null) {
				queryGameForScore.close();
			}

			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Close Error : " + e.getMessage());
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
		String sql = "SELECT COUNT(*) AS count FROM " + table;

		try (Statement statement = conn.createStatement();
			 ResultSet resultset = statement.executeQuery(sql)) {

			int count = resultset.getInt("count");

			return count;
		} catch (SQLException e) {
			System.out.println("Query Failed : " + e.getMessage());
			return -1;
		}
	}


	public List<Game> queryGames(int sortOrder) {

		StringBuilder sb = new StringBuilder("SELECT * FROM ");
		sb.append(TABLE_GAMES);

		orderBy(sb, sortOrder, QUERY_GAME_BY_TITLE_SORT);


		try (Statement statement = conn.createStatement();
			 ResultSet resultSet = statement.executeQuery(sb.toString())) {

			List<Game> games = new ArrayList<>();

			while (resultSet.next()) {
				Game game = new Game();
				game.setId(resultSet.getInt(1));
				game.setTitle(resultSet.getString(2));
				game.setReleaseDate(resultSet.getString(3));
				games.add(game);
			}

			return games;

		} catch (SQLException e) {
			System.out.println("Query Failed!!! : " + e.getMessage());
			return null;
		}
	}

	public List<Game> queryYearForGame(String year) {

		String chosenYear = "%" + year.substring(2);
		try {
			queryYearForGame.setString(1, chosenYear);
			ResultSet resultSet = queryYearForGame.executeQuery();

			List<Game> games = new ArrayList<>();

			while (resultSet.next()) {
				Game game = createGame(resultSet);
				games.add(game);
			}

			return games;

		} catch (SQLException e) {
			System.out.println("Query Failed : " + e.getMessage());
			return null;
		}
	}

	public List<Game> queryGenreForGame(String genre) {

		try {
			queryGenreForGame.setString(1, genre);
			ResultSet resultSet = queryGenreForGame.executeQuery();

			List<Game> games = new ArrayList<>();

			while (resultSet.next()) {
				Game game = createGame(resultSet);
				games.add(game);
			}

			return games;

		} catch (SQLException e) {
			System.out.println("Query Failed : " + e.getMessage());
			return null;
		}
	}

	public List<Game> queryPlatformForGame(String platform) {

		try {
			queryPlatformForGame.setString(1, platform);
			ResultSet resultSet = queryPlatformForGame.executeQuery();

			List<Game> games = new ArrayList<>();

			while (resultSet.next()) {
				Game game = createGame(resultSet);
				games.add(game);
			}

			return games;

		} catch (SQLException e) {
			System.out.println("Query Failed : " + e.getMessage());
			return null;
		}
	}

	public List<Platform> queryGameForPlatforms(String game) {

		try {
			queryGameForPlatform.setString(1, game);
			ResultSet resultSet = queryGameForPlatform.executeQuery();

			List<Platform> platforms = new ArrayList<>();

			while (resultSet.next()) {
				Platform platform = new Platform();
				platform.setName(resultSet.getString(1));
				platforms.add(platform);
			}

			return platforms;

		} catch (SQLException e) {
			System.out.println("Query Failed : " + e.getMessage());
			return null;
		}
	}

	public List<Genre> queryGameForGenre(String game) {

		try {
			queryGameForGenre.setString(1, game);
			ResultSet resultSet = queryGameForGenre.executeQuery();

			List<Genre> genres = new ArrayList<>();

			while (resultSet.next()) {
				Genre genre = new Genre();
				genre.setName(resultSet.getString(1));
				genres.add(genre);
			}

			return genres;

		} catch (SQLException e) {
			System.out.println("Query Failed : " + e.getMessage());
			return null;
		}
	}

	public List<GameReview> queryGameForScore(String game) {

		try {
			queryGameForScore.setString(1, game);
			ResultSet resultSet = queryGameForScore.executeQuery();

			List<GameReview> gameReviews = new ArrayList<>();

			while (resultSet.next()) {
				GameReview gameReview = new GameReview();
				gameReview.setScore(resultSet.getInt(1));
				gameReviews.add(gameReview);
			}

			return gameReviews;

		} catch (SQLException e) {
			System.out.println("Query Failed : " + e.getMessage());
			return null;
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

	public Game createGame(ResultSet resultSet) throws SQLException {
		Game game = new Game();
		game.setTitle(resultSet.getString(1));
		return game;
	}

}
