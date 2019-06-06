package com.app.service;

import com.app.dao.Dao;
import com.app.dto.CategoryDto;
import com.app.entity.BottomCategory;
import com.app.entity.MiddleCategory;
import com.app.entity.TopCategory;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    private final Dao dao;

    public CategoryService(Dao dao) {
        this.dao = dao;
    }

    @Transactional
    public void populateCategories() {

        TopCategory tc1 = new TopCategory();
        tc1.setName("TOP_CATEGORY_1");
        TopCategory tc2 = new TopCategory();
        tc2.setName("TOP_CATEGORY_2");

        MiddleCategory mc1 = new MiddleCategory();
        mc1.setName("MIDDLE_CATEGORY_1");
        MiddleCategory mc2 = new MiddleCategory();
        mc2.setName("MIDDLE_CATEGORY_2");
        MiddleCategory mc3 = new MiddleCategory();
        mc3.setName("MIDDLE_CATEGORY_3");

        BottomCategory bc1 = new BottomCategory();
        bc1.setName("BOTTOM_CATEGORY_1");
        BottomCategory bc2 = new BottomCategory();
        bc2.setName("BOTTOM_CATEGORY_2");
        BottomCategory bc3 = new BottomCategory();
        bc3.setName("BOTTOM_CATEGORY_3");
        BottomCategory bc4 = new BottomCategory();
        bc4.setName("BOTTOM_CATEGORY_4");
        BottomCategory bc5 = new BottomCategory();
        bc5.setName("BOTTOM_CATEGORY_5");

        tc1.addMiddleCategory(mc1);
        tc1.addMiddleCategory(mc2);
        tc2.addMiddleCategory(mc3);

        mc1.addBottomCategory(bc1);
        mc1.addBottomCategory(bc2);
        mc1.addBottomCategory(bc3);
        mc2.addBottomCategory(bc4);
        mc3.addBottomCategory(bc5);

        dao.persist(tc1);
        dao.persist(tc2);
    }

    public List<CategoryDto> fetchCategories() {

        return dao.fetchCategories();
    }
}