package com.example.restaurant.application;

import com.example.restaurant.domain.MenuItem;
import com.example.restaurant.domain.MenuItemRepository;
import com.example.restaurant.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> getMenuItems(Long restaurantId) {
        return menuItemRepository.findAllByRestaurantId(restaurantId);
    }

    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems) {
            update(restaurantId, menuItem);
        }
    }

    private void update(Long restaurantId, MenuItem menuItem) {
        if (menuItem.isDestroy()) {
            menuItemRepository.deleteById(menuItem.getId());
            return;
        }
        menuItem.setRestaurantId(restaurantId);
        menuItemRepository.save(menuItem);
    }
}
