package database;

import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;

public class DatabaseManager {
	private Database db;
    private static final String DATABASE_NAME = "game.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseManager() {
        db = DatabaseFactory.getNewDatabase(DATABASE_NAME, DATABASE_VERSION, null, null);
    }

    public void setupDatabase() throws SQLiteGdxException {
        db.setupDatabase();
        db.openOrCreateDatabase();

        String createTable = "CREATE TABLE IF NOT EXISTS SaveData (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "playerX REAL, " +
                "playerY REAL, " +
                "currentLevel INTEGER);";

        try {
            db.execSQL(createTable);
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }

    public void saveGame(float playerX, float playerY, int currentLevel) {
        String deleteOldData = "DELETE FROM SaveData";
        String insertNewData = "INSERT INTO SaveData (playerX, playerY, currentLevel) VALUES (" + playerX + ", " + playerY + ", " + currentLevel + ")";

        try {
            db.execSQL(deleteOldData);
            db.execSQL(insertNewData);
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }

    public float[] loadGame() {
        String selectData = "SELECT playerX, playerY, currentLevel FROM SaveData";
        float[] gameData = new float[3];

        try {
            DatabaseCursor cursor = db.rawQuery(selectData);
            if (cursor != null && cursor.next()) {
                gameData[0] = cursor.getFloat(0); // playerX
                gameData[1] = cursor.getFloat(1); // playerY
                gameData[2] = cursor.getInt(2);   // currentLevel
            }
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }

        return gameData;
    }

    public void closeDatabase() {
        try {
			db.closeDatabase();
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
    }
}
