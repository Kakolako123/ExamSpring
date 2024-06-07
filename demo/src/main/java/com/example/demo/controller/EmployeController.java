package com.example.demo.controller;

import com.example.demo.entity.Employe;
import com.example.demo.entity.Project;
import com.example.demo.repository.EmployeRepo;
import com.example.demo.repository.ProjectRepo;
import com.example.demo.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class EmployeController {

    @Autowired
    private EmployeRepo employeRepository;

    @Autowired
    private ProjectRepo projectRepository;

    @Autowired
    private EmployeService employeService;

    @GetMapping("/assignProject")
    public String showAssignProjectForm(Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "assignProject";
    }

    @PostMapping("/assignProject")
    public String assignProject(@RequestParam Long employeId, @RequestParam Long projectId) {
        Employe employe = employeRepository.findById(employeId).orElseThrow(() -> new RuntimeException("Employe not found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));

        employe.addProject(project);
        employeRepository.save(employe);

        return "redirect:/assignProject";
    }

    @GetMapping("/addEmploye")
    public String showAddEmployeForm(Model model) {
        model.addAttribute("employe", new Employe());
        return "add";
    }

    @PostMapping("/addEmploye")
    public String addEmploye(Employe employe) {
        employeService.saveEmploye(employe);
        return "list";
    }

    @GetMapping("/employes")
    public String listEmployes(Model model) {
        List<Employe> employes = employeService.getAllEmployes();
        model.addAttribute("employes", employes);
        return "list";
    }

    @PostMapping("/employes/remove/{id}")
    public String removeEmploye(@PathVariable Long id) {
        employeService.deleteEmployeById(id);
        return "redirect:/employes";
    }
}