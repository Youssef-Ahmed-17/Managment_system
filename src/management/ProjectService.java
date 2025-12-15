package management;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {

    private int nextProjectId = 1;
    private ArrayList<Project> projects;

    public ProjectService() {
        projects = new ArrayList<>();
    }


    public Project createProject(String name, int managerId, String description) {

        if (name == null || name.trim().isEmpty()) {
            return null;
        }

        Project project = new Project(nextProjectId++, name, managerId, description);
        projects.add(project);

        FileManager.saveProjects(projects);
        return project;
    }


    public Project getProjectById(int id) {
        for (Project project : projects) {
            if (project.getId() == id) {
                return project;
            }
        }
        return null;
    }

    public boolean projectExists(int id) {
        return getProjectById(id) != null;
    }

    public ArrayList<Project> getAllProjects() {
        return new ArrayList<>(projects);
    }


    public boolean updateProjectName(int id, String newName) {
        Project project = getProjectById(id);

        if (project == null || newName == null || newName.trim().isEmpty()) {
            return false;
        }

        project.setName(newName);
        FileManager.saveProjects(projects);
        return true;
    }

    public boolean updateDescription(int id, String newDescription) {
        Project project = getProjectById(id);

        if (project == null || newDescription == null || newDescription.trim().isEmpty()) {
            return false;
        }

        project.setDescription(newDescription);
        FileManager.saveProjects(projects);
        return true;
    }


    public boolean deleteProject(int id) {
        Project project = getProjectById(id);

        if (project == null) {
            return false;
        }

        projects.remove(project);
        FileManager.saveProjects(projects);
        return true;
    }


    public void loadProjects(List<Project> loadedProjects) {

        projects.clear();
        projects.addAll(loadedProjects);

        nextProjectId = 1;
        for (Project p : projects) {
            if (p.getId() >= nextProjectId) {
                nextProjectId = p.getId() + 1;
            }
        }
    }
}