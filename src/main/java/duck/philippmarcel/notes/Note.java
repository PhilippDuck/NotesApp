package duck.philippmarcel.notes;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * Class for a Note
 */
public class Note {
    private String title;                       // Title of Note
    private String text;                        // Text content of the note
    private String uuid;                        // Unique ID of note to identify
    private String creationDate = "noDate";     // Date of first creation

    public Note(String title, String text) {
        this.text = text;
        this.title = title;
        this.uuid = UUID.randomUUID().toString();
        this.creationDate = setCreationDate();
    }

    public Note(String title, String text, String uuid) {
        this.text = text;
        this.title = title;
        this.uuid = uuid;
        this.creationDate = setCreationDate();
    }

    public Note(String title, String text, String uuid, String creationDate) {
        this.text = text;
        this.title = title;
        this.uuid = uuid;
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return this.title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUuid() {
        return this.uuid;
    }

    public String getCreationDate() {
        return this.creationDate;
    }

    /**
     * Function that takes a timestamp and format it
     * @return returns the formatted timestamp as a String
     */
    public String setCreationDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy - HH:mm");
        return format.format(calendar.getTime());
    }
}
