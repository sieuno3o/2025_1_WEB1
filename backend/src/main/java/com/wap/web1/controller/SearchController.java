package com.wap.web1.controller;

import com.wap.web1.domain.Category;
import com.wap.web1.domain.Region;
import com.wap.web1.dto.StudyGroupResponse;
import com.wap.web1.service.StudyGroupFilterSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grouplist")
public class SearchController {

    private final StudyGroupFilterSearchService studyGroupFilterSearchService;

    public SearchController(StudyGroupFilterSearchService studyGroupFilterSearchService) {
        this.studyGroupFilterSearchService = studyGroupFilterSearchService;
    }

    @GetMapping("/search")
    public StudyGroupResponse searchGroups (
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "7") int size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) List<Category> categories,
            @RequestParam(required = false) List<Region> regions
    ) {
        return studyGroupFilterSearchService.searchGroups(cursor,size,keyword, categories, regions);
    }
}
