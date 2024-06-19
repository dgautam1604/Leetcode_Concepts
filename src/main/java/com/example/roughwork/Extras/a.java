package com.example.roughwork.Extras;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class a {
    static a aa = new a();

    private a() {

    }

    static a get() {
        return aa;
    }

    @GetMapping("/{id}")
    public void x(@PathVariable int n){

    }
    @GetMapping("/foos")
    public void x2(@RequestParam() int n){
        System.out.println(n);
    }
    //here after /foos it should have ?n=5

    @GetMapping("/foos")
    public void x3(@RequestParam(name="n") int n){
        System.out.println(n);
    }
    //here after /foos it should have ?n=5
}
