package com.impulse.afterdarrk;

import android.media.Image;
import android.view.MotionEvent;
import android.view.View;

public abstract class Action implements View.OnTouchListener {
    private ActionType type;
    private Image img;
    private Player player;

    public Action(ActionType type, Image img, Player player) {
        this.type = type;
        this.img = img;
        this.player = player;
    }

    void use() {
        player.useAction(this.type);
    }

    Image getImage() {
        return this.img;
    }

    ActionType getType() {
        return this.type;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            player.useAction(type);
            return true;
        }
        return false;
    }
}
