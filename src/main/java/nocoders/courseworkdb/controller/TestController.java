package nocoders.courseworkdb.controller;

import nocoders.courseworkdb.model.Question;
import nocoders.courseworkdb.model.QuestionForm;
import nocoders.courseworkdb.model.Result;
import nocoders.courseworkdb.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TestController {

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("title", "Станица тестировании");
        return "test";
    }

    @Autowired
    Result result;
    @Autowired
    QuizService qService;

    Boolean submitted = false;

    @ModelAttribute("result")
    public Result getResult() {
        return result;
    }

    @GetMapping("/test/index")
    public String home() {
        return "index.html";
    }

//    @GetMapping("/quiz")
//    public String quizPage() {
//        return "quiz.html"; // quiz.html должен быть расположен в папке templates
//    }

//    @PostMapping("/quiz")
//    public String quiz(@RequestParam String username, Model m, RedirectAttributes ra) {
//        if(username.equals("")) {
//            ra.addFlashAttribute("warning", "You must enter your name");
//            return "redirect:/";
//        }
//
//        submitted = false;
//        result.setUsername(username);
//
//        QuestionForm qForm = qService.getQuestions();
//        m.addAttribute("qForm", qForm);
//
//
//        return "quiz.html";
//    }

//    @GetMapping("/quiz")
//    public String quiz() {
//        return "quiz.html";
//    }

    @PostMapping("/quiz")
    public String quiz(@RequestParam String username, Model m, RedirectAttributes ra) {
        try {
            if(username.equals("")) {
                ra.addFlashAttribute("warning", "You must enter your name");
                return "redirect:/index";
            }

            submitted = false;
            result.setUsername(username);

            QuestionForm qForm = qService.getQuestions();
            m.addAttribute("qForm", qForm);

            return "quiz";
        } catch(Exception e) {
            ra.addFlashAttribute("error", "An error occurred: " + e.getMessage());
            return "redirect:/error";
        }
    }


    @PostMapping("/submit")
    public String submit(@ModelAttribute QuestionForm qForm, Model m) {
        if(!submitted) {
            result.setTotalCorrect(qService.getResult(qForm));
            qService.saveScore(result);
            submitted = true;
        }

        return "result.html";
    }

    @GetMapping("/score")
    public String score(Model m) {
        List<Result> sList = qService.getTopScore();
        m.addAttribute("sList", sList);

        return "scoreboard.html";
    }

}















//    @GetMapping("/test")
//    public String test(Model model) {
//      model.addAttribute("title", "Станица тестировании");
//      return "test";
//    }
//
//    @Autowired
//    Result result;
//    @Autowired
//    QuizService qService;
//
//    Boolean submitted = false;
//
//    @ModelAttribute("result")
//    public Result getResult() {
//        return result;
//    }
//
//    @GetMapping("/index")
//    public String home() {
//        return "index.html";
//    }
//
////    @GetMapping("/quiz")
////    public String quizPage() {
////        return "quiz.html"; // quiz.html должен быть расположен в папке templates
////    }
//
////    @PostMapping("/quiz")
////    public String quiz(@RequestParam String username, Model m, RedirectAttributes ra) {
////        if(username.equals("")) {
////            ra.addFlashAttribute("warning", "You must enter your name");
////            return "redirect:/";
////        }
////
////        submitted = false;
////        result.setUsername(username);
////
////        QuestionForm qForm = qService.getQuestions();
////        m.addAttribute("qForm", qForm);
////
////
////        return "quiz.html";
////    }
//
////    @GetMapping("/quiz")
////    public String quiz() {
////        return "quiz.html";
////    }
//
//    @PostMapping("/quiz")
//    public String quiz(@RequestParam String username, Model m, RedirectAttributes ra) {
//        try {
//            if(username.equals("")) {
//                ra.addFlashAttribute("warning", "You must enter your name");
//                return "redirect:/index";
//            }
//
//            submitted = false;
//            result.setUsername(username);
//
//            QuestionForm qForm = qService.getQuestions();
//            m.addAttribute("qForm", qForm);
//
//            return "quiz";
//        } catch(Exception e) {
//            ra.addFlashAttribute("error", "An error occurred: " + e.getMessage());
//            return "redirect:/error";
//        }
//    }
//
//
//    @PostMapping("/submit")
//    public String submit(@ModelAttribute QuestionForm qForm, Model m) {
//        if(!submitted) {
//            result.setTotalCorrect(qService.getResult(qForm));
//            qService.saveScore(result);
//            submitted = true;
//        }
//
//        return "result.html";
//    }
//
//    @GetMapping("/score")
//    public String score(Model m) {
//        List<Result> sList = qService.getTopScore();
//        m.addAttribute("sList", sList);
//
//        return "scoreboard.html";
//    }

//    @GetMapping("/register")
//    public String register(Model model) {
//        model.addAttribute("title", "Страница регистрации");
//        return "register";
//    }
