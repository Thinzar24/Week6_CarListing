package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    CarRepository carRepository;
//    @Autowired
//    CategoryRepository categoryRepository;


    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String listCars(Model model){
        model.addAttribute("cars", carRepository.findAll());
        return "carlist";
    }

    @GetMapping("/add")
    public String addCar(Model model){
        model.addAttribute("car", new Car());
        return "carform";
    }

    @PostMapping("/process")
    public String processCar(@Valid @ModelAttribute Car car,
                                   @RequestParam("file")MultipartFile file, BindingResult result){
        if(result.hasErrors()){
            return "carform";
        }

        if(file.isEmpty()){
            return "redirect:/add";
        }

        try{
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            car.setImage(uploadResult.get("url").toString());
            carRepository.save(car);
        }catch (IOException e){
            e.printStackTrace();
            return "redirect:/add";
        }
        return "redirect:/";
    }


    @RequestMapping("/details/{id}")
    public String carDetails(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "cardetails";
    }

    @RequestMapping("/update/{id}")
    public String carUpdate(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id));
        return "carform";
    }

    @RequestMapping("/delete/{id}")
    public String carDelete(@PathVariable("id") long id){
        carRepository.deleteById(id);
        return "redirect:/";
    }

}
