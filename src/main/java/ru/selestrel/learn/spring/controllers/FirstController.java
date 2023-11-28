package ru.selestrel.learn.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String lastName,
                            Model model) {
        model.addAttribute("message", "Hello, " + name + " " + lastName);

        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculatorPage(@RequestParam(value = "firstNumber") int firstNumber,
                                 @RequestParam(value = "secondNumber") int secondNumber,
                                 @RequestParam(value = "action") String action,
                                 Model model) {
        String description = "Perform %s of %d and %d:".formatted(action, firstNumber, secondNumber);
        String result = switch (action) {
            case "multiplication" -> String.valueOf(firstNumber * secondNumber);
            case "addition" -> String.valueOf(firstNumber + secondNumber);
            case "subtraction" -> String.valueOf(firstNumber - secondNumber);
            case "division" -> String.valueOf((double) firstNumber / secondNumber);
            default -> "unknown operation, please use multiplication, addition, subtraction or division";
        };

        model.addAttribute("description", description);
        model.addAttribute("result", result);

        return "first/calculator";
    }
}
