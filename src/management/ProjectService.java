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
        if (name == null || name.isEmpty()) {
            return null;
        }

        Project project = new Project(nextProjectId++, name, managerId, description);
        projects.add(project);
        return project;
    }

    public Project getProjectById(int id) {

        for (int i = 0; i < projects.size(); i++) {

            Project project = projects.get(i);

            if (project.getId() == id) {
                return project;
            }
        }
        return null;
    }

    public boolean projectExists(int id) {
        for (int i = 0; i < projects.size(); i++) {

            Project project = projects.get(i);

            if (project.getId() == id) {
                return true;
            }
        }
        return false;

    }

    public boolean deleteProject(int id) {
        Project project = getProjectById(id);
        if (project != null) {
            projects.remove(project);
            return true;
        }
        return false;
    }

    public ArrayList<Project> getAllProjects() {
        return new ArrayList<>(projects);
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

    public boolean updateProjectName(int id, String newName) {
        Project project = getProjectById(id);
        if (project == null || newName == null || newName.trim().isEmpty()) {
            return false;
        }
        project.setName(newName);
        return true;
    }

    public void updateDescription(int id, String newdescription) {
        Project project = getProjectById(id);
        project.setDescription(newdescription);
        ;

    }


}
