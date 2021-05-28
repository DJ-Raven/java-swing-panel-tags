package main;

import java.awt.event.KeyEvent;

public interface EventTags {

    public void onAddItem(Item item, String text);

    public void onKeyType(Item item, String text, KeyEvent evt);

    public void onItemRemove(Item item, String text);

    public boolean isRemoveAble(Item item, String text);

    public boolean isAddAble(Item item, String text);
}
