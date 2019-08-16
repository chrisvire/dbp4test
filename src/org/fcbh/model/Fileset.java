package org.fcbh.model;

public class Fileset {
    private String bookId;
    private String bookName;
    private String chapterStart;
    private String path;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getChapterStart() {
        return chapterStart;
    }

    public void setChapterStart(String chapterStart) {
        this.chapterStart = chapterStart;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
