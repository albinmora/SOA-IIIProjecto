package com.example.albin.sportec.Model;

import com.example.albin.sportec.R;

/**
 * Created by albertlo on 7/10/17.
 */

public enum NavBarItem {
    MAIN(R.id.m_main_item),
    SPORT(R.id.m_sport_item),
    CHALLENGE(R.id.m_challenge_item),
    HISTORY(R.id.m_history_item),
    LOGOUT(R.id.m_logout_item);


    private int itemId;
    NavBarItem(int itemId) {
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public static NavBarItem fromViewId(int viewId) {
        for(NavBarItem navBarItem : NavBarItem.values()) {
            if (navBarItem.getItemId() == viewId) {
                return navBarItem;
            }
        }
        throw new IllegalStateException("Cannot find viewType");
    }
}
