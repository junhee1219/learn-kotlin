package com.just_learn.demo.analysis

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/analyze")
class TextAnalyzeController(private val svc: TextAnalyzeService) {
    @PostMapping("/text")
    fun analyze(@RequestBody req: TextAnalyzeReq): TextAnalyzeRes =
        svc.analyze(req)
}
