package rynkbit.tk.coffeelist.item;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import rynkbit.tk.coffeelist.R;
import rynkbit.tk.coffeelist.db.entity.Item;

/**
 * Created by michael on 11/13/16.
 */
public class ItemClickListener implements View.OnClickListener {
    private Item mItem;
    private ItemController mItemController;

    public ItemClickListener(ItemController itemController, Item item) {
        mItem = item;
        mItemController = itemController;
    }

    @Override
    public void onClick(View view) {
        if(mItem.getStock() > 0) {
            mItemController.buyItem(mItem);
        }else{
            mItemController.showItemNotAvaiable(mItem);
        }
    }
}
