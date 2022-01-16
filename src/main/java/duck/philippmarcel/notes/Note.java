package duck.philippmarcel.notes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class Note {
    private String title;
    private String text;
    private String uuid;
    private String creationDate;

    public Note(String title, String text) {
        this.text = text;
        this.title = title;
        this.uuid = UUID.randomUUID().toString();
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy - HH:mm");
        this.creationDate = format.format(calendar.getTime());
        System.out.println(creationDate);
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
}
