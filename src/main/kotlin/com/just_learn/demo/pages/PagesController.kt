package com.just_learn.demo.pages
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PagesController {
    @GetMapping("/hello")
    fun hello(model: Model): String {
        val foo1 = "hi" // const 같은거
        var foo2: String? = null
        foo2 = 333.toString();
        model.addAttribute("message", "Kotlin Spring Test $foo1 $foo2")
        return "hello"
    }

    @GetMapping("/notes")
    fun notesPage(): String{
        return "notes"
    }

    @GetMapping("/analyze")
    fun page(): String = "analyze"

}
