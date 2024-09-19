package model;

public class data {
    private int id;
    private String filename;
    private String path;

    public data(int id, String filename,String path ) {
        this.id = id;
        this.path = path;
        this.filename = filename;
    }

    private String email;
    public data(int id, String filename, String path, String email) {
        this.id = id;
        this.filename = filename;
        this.path = path;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
