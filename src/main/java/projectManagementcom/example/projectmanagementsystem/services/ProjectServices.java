package projectManagementcom.example.projectmanagementsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectManagementcom.example.projectmanagementsystem.exceptions.ProjectAlreadyExistException;
import projectManagementcom.example.projectmanagementsystem.exceptions.ProjectNotFoundException;
import projectManagementcom.example.projectmanagementsystem.models.Project;
import projectManagementcom.example.projectmanagementsystem.repositories.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServices {

@Autowired
ProjectRepository projectRepository;


    public Project addProject(Project project) throws ProjectAlreadyExistException {
        if(projectRepository.findById(project.getId()).isPresent())
        {
            throw new ProjectAlreadyExistException();
        }
        return projectRepository.save(project);
    }


    public boolean deleteProject(int Id) throws ProjectNotFoundException {
        boolean result = false;
        if (projectRepository.findById(Id).isEmpty()){
            throw new ProjectNotFoundException();
        }else{
            projectRepository.deleteById(Id);
            result = true;
        }
        return result;
    }

    public Project updateProject(Project project, int itemId) throws ProjectNotFoundException {
        Optional<Project> optionalItem = projectRepository.findById(itemId);
        if(optionalItem.isEmpty()){
            return null;
        }
        Project existingItem = optionalItem.get();
        if (project.getName()!=null){
            existingItem.setName(project.getName());
        }
        if (project.getDescription()!=null){
            existingItem.setDescription(project.getDescription());
        }
        if(project.getStartDate()!=null){
            existingItem.setStartDate(project.getStartDate());
        }
        if(project.getEndDate()!=null){
            existingItem.setEndDate(project.getEndDate());
        }
        return projectRepository.save(existingItem);
    }


    public List<Project> getAllProjects()  {
        List<Project> projectList = projectRepository.findAll();
        return projectList;
    }

//    public Project getItemByItemId(int Id) throws ProjectNotFoundException {
//        return projectRepository.(Id);
//    }

    public Optional<Project> getEntityById(int id) {
        return projectRepository.findById(id);
    }
}
