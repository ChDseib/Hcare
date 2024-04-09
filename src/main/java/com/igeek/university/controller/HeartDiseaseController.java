package com.igeek.university.controller;

import com.igeek.university.entity.HeartDisease;
import com.igeek.university.service.IHeartDiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/heartdisease")
public class HeartDiseaseController {
    @Autowired
    private IHeartDiseaseService heartDiseaseService;

    @PostMapping("/add")
    public String add(@RequestBody HeartDisease heartDisease){
        try {
            if (heartDisease.getAge() == null || heartDisease.getGender() == null || heartDisease.getChestPainType() == null ||
                    heartDisease.getRestingBloodPressure() == null || heartDisease.getCholesterol() == null || heartDisease.getFastingBloodSugar() == null ||
                    heartDisease.getRestingECG() == null || heartDisease.getMaxHeartRate() == null || heartDisease.getExerciseInducedAngina() == null ||
                    heartDisease.getStDepression() == null) {
                return "除ID外所有都为必填项！";
            }
            heartDiseaseService.save(heartDisease);
        } catch (Exception e) {
            e.printStackTrace();
            return "添加失败!";
        }
        return "添加成功！";
    }

    @GetMapping("/{id}")
    public HeartDisease get(@PathVariable Integer id) {
        return heartDiseaseService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        heartDiseaseService.deleteById(id);
    }

    @PostMapping("/edit")
    public String edit(@RequestBody HeartDisease heartDisease){
        try {
            if (heartDisease.getAge() == null || heartDisease.getGender() == null || heartDisease.getChestPainType() == null ||
                    heartDisease.getRestingBloodPressure() == null || heartDisease.getCholesterol() == null || heartDisease.getFastingBloodSugar() == null ||
                    heartDisease.getRestingECG() == null || heartDisease.getMaxHeartRate() == null || heartDisease.getExerciseInducedAngina() == null ||
                    heartDisease.getStDepression() == null) {
                return "除ID外所有都为必填项！";
            }
            heartDiseaseService.save(heartDisease);
        } catch (Exception e) {
            e.printStackTrace();
            return "编辑失败！";
        }
        return "编辑成功！";
    }
    @GetMapping("/list")
    public List<HeartDisease> list() {
        return heartDiseaseService.findAll();
    }
}