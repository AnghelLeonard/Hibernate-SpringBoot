package com.bookstore.controller;

import com.bookstore.entity.Inventory;
import com.bookstore.service.InventoryService;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes({InventoryController.INVENTORY_ATTR})
public class InventoryController {

    protected static final String INVENTORY_ATTR = "inventory";
    private static final String BINDING_RESULT = "org.springframework.validation.BindingResult." + INVENTORY_ATTR;

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/load/{id}")
    public String fetchInventory(@PathVariable Long id, Model model) {
        if (!model.containsAttribute(BINDING_RESULT)) {
            model.addAttribute(INVENTORY_ATTR, inventoryService.fetchInventoryById(id));
        }

        return "index";
    }

    @PostMapping("/update")
    public String updateInventory(@Validated @ModelAttribute(INVENTORY_ATTR) Inventory inventory,
            BindingResult bindingResult, RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {

        if (!bindingResult.hasErrors()) {
            try {
                Inventory updatedInventory = inventoryService.updateInventory(inventory);
                redirectAttributes.addFlashAttribute("updatedInventory", updatedInventory);
            } catch (OptimisticLockingFailureException e) {
                bindingResult.reject("", "Another user updated the data. Press the link above to reload it.");
            }
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BINDING_RESULT, bindingResult);
            return "redirect:load/" + inventory.getId();
        }

        sessionStatus.setComplete();

        return "redirect:success";
    }

    @GetMapping(value = "/success")
    public String success() {
        return "success";
    }

    @InitBinder
    void allowFields(WebDataBinder webDataBinder
    ) {
        webDataBinder.setAllowedFields("quantity");
    }
}
