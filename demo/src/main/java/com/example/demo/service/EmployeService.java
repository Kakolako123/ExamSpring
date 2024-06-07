package com.example.demo.service;
import com.example.demo.entity.Employe;
import com.example.demo.repository.EmployeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeService {

    @Autowired
    private EmployeRepo employeRepository;

    public void saveEmploye(Employe employe) {
        employeRepository.save(employe);
    }

    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    public void deleteEmployeById(Long id) {
        employeRepository.deleteById(id);
    }
}

