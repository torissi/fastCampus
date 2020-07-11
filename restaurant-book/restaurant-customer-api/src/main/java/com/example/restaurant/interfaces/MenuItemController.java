package com.example.restaurant.interfaces;

import com.example.restaurant.application.MenuItemService;
import com.example.restaurant.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PatchMapping("/restaurant/{restaurantId}/menuitems")
    public String bulkUpdate(@RequestBody List<MenuItem> menuItems,
                            @PathVariable("restaurantId") Long restaurantId) {
        menuItemService.bulkUpdate(restaurantId, menuItems);

        return "";
    }
}
