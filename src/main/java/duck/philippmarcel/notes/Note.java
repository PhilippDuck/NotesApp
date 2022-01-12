package duck.philippmarcel.notes;

import java.util.UUID;

public class Note {
    private String title;
    private String text;
    private String uuid;

    public Note(String title, String text) {
        this.text = text;
        this.title = title;
        this.uuid = UUID.randomUUID().toString();
    }

    public Note(String title, String text, String uuid) {
        this.text = text;
        this.title = title;
        this.uuid = uuid;
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
}
