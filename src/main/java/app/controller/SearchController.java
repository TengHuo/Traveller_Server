package app.controller;

import app.jsonClass.PostRes;
import app.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhujay on 16/6/22.
 */
@RestController
public class SearchController {

    @Autowired
    SearchService searchService;

    @RequestMapping(value = "/posts/search",method = RequestMethod.GET)
    public PostRes KeyWordQuery(@RequestParam("keyword")String keyword){
        try {
            return searchService.ketWordQuery(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(999, e.toString());
        }
    }
}
