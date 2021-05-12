package com.itheima.controller;

import com.itheima.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/updateNoLock/{id}/{saleNum}")
    public void updateNoLock(@PathVariable("id") Integer id, @PathVariable("saleNum") int saleNum) {
        bookService.updateStock(id, saleNum);
    }

    @GetMapping("/updateMysqlConditionsLock/{id}/{saleNum}")
    public void updateMysqlConditionsLock(@PathVariable("id") Integer id, @PathVariable("saleNum") int saleNum) {
        bookService.updateMysqlConditionsLock(id, saleNum);
    }

    @GetMapping("/updateByVersion/{id}/{saleNum}")
    public void updateByVersion(@PathVariable("id") Integer id, @PathVariable("saleNum") int saleNum) {
        bookService.updateByVersion(id, saleNum);
    }


    @GetMapping("/updateByZk/{id}/{saleNum}")
    public void updateByZk(@PathVariable("id") Integer id, @PathVariable("saleNum") int saleNum) {
        bookService.updateByZk(id, saleNum);
    }

    @GetMapping("/updateByZkCuratorLock/{id}/{saleNum}")
    public void updateByZkCuratorLock(@PathVariable("id") Integer id, @PathVariable("saleNum") int saleNum) {
        bookService.updateByZkCuratorLock(id, saleNum);
    }

    @GetMapping("/updateByRedisson/{id}/{saleNum}")
    public void updateByRedisson(@PathVariable("id") Integer id, @PathVariable("saleNum") int saleNum) {
        bookService.updateByRedisson(id, saleNum);
    }
}
