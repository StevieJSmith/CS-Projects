package dev.sjs;

import dev.model.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

	private Datasource datasource;

	@BeforeEach
	public void setup () {
		System.out.println("-----");
		datasource = new Datasource();
		if(!datasource.open()) {
			System.out.println("Can't open the Datasource!!!");
		}


	}

	@AfterAll
	public static void closing() {
		System.out.println("-----");
		System.out.println("All tests are completed!");

	}

	public static Stream<testParameters> testConditionsOnYearForGame () {
		List<String> array1 = new ArrayList<>(List.of("Final Fantasy VII"));
		List<String> array2 = new ArrayList<>(Arrays.asList("Final Fantasy IX", "Monster Rancher 2"));
		List<String> array3 = new ArrayList<>(Arrays.asList("Final Fantasy XVI", "Hogwarts Legacy",
				"The Legend of Zelda Tears of the Kingdom", "Baldur's Gate 3", "Street Fighter 6"));
		return Stream.of(
				new testParameters("1997", array1),
				new testParameters("2000", array2),
				new testParameters("2023", array3)
		);
	}

	public static Stream<testParameters> testConditionsOnGenreForGame () {
		List<String> array1 = new ArrayList<>(Arrays.asList("Forza Motorsport 3", "Mario Kart DS",
				"CTR Crash Team Racing", "Split/Second", "Mario Kart Wii"));
		List<String> array2 = new ArrayList<>(List.of("Resident Evil 7 Biohazard"));
		List<String> array3 = new ArrayList<>(Arrays.asList("Baldur's Gate 3", "Persona 5 Royal",
				"Pikmin 2", "Fire Emblem Three Houses", "Final Fantasy Tactics"));
		return Stream.of(
				new testParameters("Racing", array1),
				new testParameters("Horror", array2),
				new testParameters("Strategy", array3)
		);
	}
	public static Stream<testParameters> testConditionsOnPlatformForGame () {
		List<String> array1 = new ArrayList<>(Arrays.asList("Final Fantasy VII", "Final Fantasy IX",
				"Monster Rancher 2", "CTR: Crash Team Racing", "Final Fantasy Tactics"));
		List<String> array2 = new ArrayList<>(List.of("Final Fantasy X"));
		List<String> array3 = new ArrayList<>(Arrays.asList("Halo 3", "Gears of War 3",
				"The Elder Scrolls V Skyrim", "Mass Effect 3", "Forza Motorsport 3",
				"Batman Arkham Asylum", "Borderlands 2", "L.A. Noire"));
		return Stream.of(
				new testParameters("PS1", array1),
				new testParameters("PS2", array2),
				new testParameters("Xbox 360", array3)
		);
	}

	public static Stream<testParameters> testConditionsOnGameForPlatform () {
		List<String> array1 = new ArrayList<>(List.of("Wii"));
		List<String> array2 = new ArrayList<>(Arrays.asList("Switch", "GameCube"));
		List<String> array3 = new ArrayList<>(Arrays.asList("Ps4", "Ps5",
				"Xbox One", "Pc"));
		return Stream.of(
				new testParameters("Paper Mario", array1),
				new testParameters("Super Mario Sunshine", array2),
				new testParameters("Monster Hunter World", array3)
		);
	}
	public static Stream<testParameters> testConditionsOnGameForGenre () {
		List<String> array1 = new ArrayList<>(List.of("Rpg"));
		List<String> array2 = new ArrayList<>(Arrays.asList("Adventure", "Platformer", "Action"));
		List<String> array3 = new ArrayList<>(Arrays.asList("Rpg", "Action"));
		return Stream.of(
				new testParameters("Paper Mario", array1),
				new testParameters("Super Mario Sunshine", array2),
				new testParameters("Monster Hunter World", array3)
		);
	}

	public static Stream<testParameters> testConditionsOnGameForScore () {
		List<String> array1 = new ArrayList<>(List.of("87"));
		List<String> array2 = new ArrayList<>(List.of("92"));
		List<String> array3 = new ArrayList<>(List.of("93"));
		return Stream.of(
				new testParameters("Final Fantasy VII Remake", array1),
				new testParameters("Final Fantasy VII", array2),
				new testParameters("Paper Mario", array3)
		);
	}

	@ParameterizedTest
	@MethodSource("testConditionsOnYearForGame")
	void queryYearForGame (testParameters parameters) {
		List<Game> games = datasource.queryYearForGame(parameters.getInput());
		int i = 0;
		for(Game game: games) {
			assertEquals(parameters.getExpected().get(i), game.getTitle());
			i++;
		}
		System.out.println("Test Year for Game successful!");
	}

	@ParameterizedTest
	@MethodSource("testConditionsOnGenreForGame")
	void queryGenreForGame (testParameters parameters) {
		List<Game> games = datasource.queryGenreForGame(parameters.getInput());
		int i = 0;
		for(Game game: games) {
			assertEquals(parameters.getExpected().get(i), game.getTitle());
			i++;
		}
		System.out.println("Test Genre for Game successful!");
	}

	@ParameterizedTest
	@MethodSource("testConditionsOnPlatformForGame")
	void queryPlatfromForGame (testParameters parameters) {
		List<Game> games = datasource.queryPlatformForGame(parameters.getInput());
		int i = 0;
		for(Game game: games) {
			assertEquals(parameters.getExpected().get(i), game.getTitle());
			i++;
		}
		System.out.println("Test Platform for Game successful!");
	}

	@ParameterizedTest
	@MethodSource("testConditionsOnGameForPlatform")
	void queryGameForPlatform (testParameters parameters) {
		List<Platform> platforms = datasource.queryGameForPlatforms(parameters.getInput());
		int i = 0;
		for(Platform platform: platforms) {
			assertEquals(parameters.getExpected().get(i), platform.getName());
			i++;
		}
		System.out.println("Test Game for Platform successful!");
	}

	@ParameterizedTest
	@MethodSource("testConditionsOnGameForGenre")
	void queryGameForGenre (testParameters parameters) {
		List<Genre> genres = datasource.queryGameForGenre(parameters.getInput());
		int i = 0;
		for(Genre genre: genres) {
			assertEquals(parameters.getExpected().get(i), genre.getName());
			i++;
		}
		System.out.println("Test Game for Genre successful!");
	}

	@ParameterizedTest
	@MethodSource("testConditionsOnGameForScore")
	void queryGameForScore (testParameters parameters) {
		List<GameReview> gameReviews = datasource.queryGameForScore(parameters.getInput());
		int i = 0;
		for(GameReview gameReview: gameReviews) {
			assertEquals(Integer.parseInt(parameters.getExpected().get(i)), gameReview.getScore());
			i++;
		}
		System.out.println("Test Game for Score successful!");
	}

	static class testParameters {
		private String input;
		private List<String> expected;

		public testParameters(String input, List<String> expected) {
			this.input = input;
			this.expected = expected;
		}

		public String getInput() {
			return input;
		}

		public List<String> getExpected() {
			return expected;
		}
	}
}