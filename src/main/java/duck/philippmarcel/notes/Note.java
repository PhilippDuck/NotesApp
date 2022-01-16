package duck.philippmarcel.notes;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.UUID;

public class Note {
    private String title;
    private String text;
    private String uuid;
    private String creationDate = "noDate";

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

    public String setCreationDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy - HH:mm");
        return format.format(calendar.getTime());
    }
}
