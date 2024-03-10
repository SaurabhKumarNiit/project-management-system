package projectManagementcom.example.projectmanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectManagementcom.example.projectmanagementsystem.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> { }
