package game;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.json.JSONObject;

public class OnlineHighScore {

	String highScore = null;


	public boolean setHighScore(String name, int level, double time) {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		jsonObject.put("level", Integer.toString(level));
		jsonObject.put("time", Double.toString(time));
		return updateHighScore(jsonObject.toString());
	}


	public String getHighScore() {
		String urlString = "http://localhost:8080/UserManagement/rest/UserService/highscore";
		JSONObject jsonObject = new JSONObject(getString(urlString));
		return jsonObject.getString("time");
	}



	public String getHighScore(int level) {
		String urlString = "http://localhost:8080/UserManagement/rest/UserService/highscore/level=" + level;
		String time;
		try {
			JSONObject jsonObject = new JSONObject(getString(urlString));
			time = jsonObject.getString("time");
		} catch (NullPointerException e) {
			time = "-1";
		}
		return time;
	}



	/**
	 *
	 * @param urlString
	 * @return Returns JSON string of content from database
	 */
	private String getString(String urlString) {
		String jsonString = null;

		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("charset", "utf-8");
			connection.connect();
			InputStream inStream = connection.getInputStream();
			jsonString = streamToString(inStream);
			connection.disconnect();
		} catch (IOException ex) {
			System.out.println("Någonting blev fel!\nVar god försök igen!");
			return "Error";
		} catch (NoSuchElementException ex) {
			return null;
		}
		return jsonString;
	}


	private static String streamToString(InputStream inputStream) {
		@SuppressWarnings("resource")
		String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
		return text;
	}



	/**
	 * Used to post a new high score in server database
	 * @param jsonString
	 * @return true if update was successful. False if something went wrong.
	 */
	private boolean updateHighScore(String jsonString) {

		String urlString = "http://localhost:8080/UserManagement/rest/UserService/highscore";

		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("Accept", "application/json");

			try(OutputStream outputStream = connection.getOutputStream()) {
				byte[] input = jsonString.getBytes("utf-8");
				outputStream.write(input, 0, input.length);
			}
			System.out.println(connection.getResponseCode());

			connection.disconnect();
		} catch (IOException ex) {
			System.out.println("Någonting blev fel!\nVar god försök igen!");
			return false;
		}
		return true;
	}



}
