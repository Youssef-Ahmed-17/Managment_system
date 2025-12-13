package management;

public class Project {

    private final int id ;
    private String name;
    private int managerId ;
    private String description ;


    public Project ( int id , String name , int managerId , String description  ) {

        this.id = id ;
        this.name = name ;
        this.managerId = managerId ;
        this.description = description ;

    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManagerId() {
        return managerId;
    }

    public String getDescription() {
        return description;
    }
}