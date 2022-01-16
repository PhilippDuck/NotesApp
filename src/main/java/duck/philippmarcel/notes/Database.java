package duck.philippmarcel.notes;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * Database class. Creates a new database and table in users directory.
 */
public class Database {

    private String path = System.getProperty("user.home");
    private final String seperator = File.separator;
    private String url = "jdbc:sqlite:" + path + seperator + "notes.db";

    public Database() {
        createDatabase();
        createTable();
    }

    /**
     * If no database exists, this function creates one.
     */
    public void createDatabase() {
        try (Connection conn = this.connect()) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This Function creates a new table in database.
     */
    public void createTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS notes (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	title text NOT NULL,\n"
                + "	text text,\n"
                + " uuid text,\n"
                + " creationDate text \n"
                + ");";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Trys to connect to a database
     * @return returns a connection
     */
    private Connection connect() {
        // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Sends a new entry to database.
     * @param title
     * @param text
     * @param uuid
     * @param creationDate
     */
    public void insert(String title, String text, String uuid, String creationDate) {
        String sql = "INSERT INTO notes(title,text,uuid, creationDate) VALUES(?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, text);
            pstmt.setString(3, uuid);
            pstmt.setString(4, creationDate);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Function get all entry's from database table
     * @return returns an Arraylist with all notes
     */
    public ArrayList<Note> getAllNotes(){
        String sql = "SELECT title, text, uuid, creationDate FROM notes";
        ArrayList<Note> notes = new ArrayList<Note>();

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                Note note = new Note(rs.getString("title"), rs.getString("text"), rs.getString("uuid"), rs.getString("creationDate"));
                notes.add(note);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return notes;
    }

    /**
     * Deletes an entry with specific uuid
     * @param uuid
     */
    public void delete(String uuid) {
        String sql = "DELETE FROM notes WHERE uuid = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.println("Delete: " + uuid);
            // set the corresponding param
            pstmt.setString(1, uuid);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates an existing entry
     * @param uuid
     * @param title
     * @param text
     */
    public void update(String uuid, String title, String text) {
        String sql = "UPDATE notes SET title = ? , "
                + "text = ? "
                + "WHERE uuid = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, title);
            pstmt.setString(2, text);
            pstmt.setString(3, uuid);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
