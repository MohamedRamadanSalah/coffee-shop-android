package android.bignerdranch.cofee;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;




    // ........................... The Constructor of Management Cart ...................................................
    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
// ......................................................................................................................




    // ..................................... GetListCart .................................................................
    public ArrayList<ItemsModel> getListCart() {
        // Recall saved items using tinyDB
        ArrayList<ItemsModel> cartList = tinyDB.getListObject("CartList");
        // If there is no list we create one
        return (cartList != null) ? cartList : new ArrayList<>();
    }
    // ..................................................................................................................



    // ....................................... The Insert Function ..................................................................
    public void insertItems(ItemsModel item) {

        // Recall saved items using tinyDB
        ArrayList<ItemsModel> listItem = getListCart();

        // We consider that the element does not exist.
        boolean existAlready = false;
        int index = -1;

        // Search The Item In the List
        for (int i = 0; i < listItem.size(); i++) {
            if (listItem.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                index = i;
                break;
            }
        }

        if (existAlready) {
            //  Update the number of quantities if available
            listItem.get(index).setNumberInCart(item.getNumberInCart());
        }
        else {
            // If it is not available we will replace it.
            listItem.add(item);
        }

        // We save the list
        tinyDB.putListObject("CartList", listItem);
        // The Item added
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
    }
//.................................................................................................................................



    //................................................ The MinusItem Function ....................................................
    public void minusItem(ArrayList<ItemsModel> listItems, int position, ChangeNumberItemsListener listener) {

        // If there is only one element we remove it so that no error occurs in the application
        if (listItems.get(position).getNumberInCart() == 1) {
            listItems.remove(position);
        }
        else {
            // We decrease the number of quantities by one.
            listItems.get(position).setNumberInCart(listItems.get(position).getNumberInCart() - 1);
        }

        // We save the list
        tinyDB.putListObject("CartList", listItems);

        // Data update
        listener.onChanged();

    }
// .................................................................................................................................




    // ................................................ The Remove Function ........................................................
    public void removeItem(ArrayList<ItemsModel> listItems, int position, ChangeNumberItemsListener listener) {
        listItems.remove(position);
        tinyDB.putListObject("CartList", listItems);
        listener.onChanged();
    }
// ...............................................................................................................................



// ................................................ The PlusItem Function ........................................................
    public void plusItem(ArrayList<ItemsModel> listItems, int position, ChangeNumberItemsListener listener) {
        // We decrease the number of quantities by one.
        listItems.get(position).setNumberInCart(listItems.get(position).getNumberInCart() + 1);
        // Save Data List
        tinyDB.putListObject("CartList", listItems);
        listener.onChanged();
    }
// ..............................................................................................................................




    // ............................................The Get Total Fee ............................................................
    public double getTotalFee() {
        ArrayList<ItemsModel> listItem = getListCart();
        double fee = 0.0;

        for (ItemsModel item : listItem) {
            fee += item.getPrice() * item.getNumberInCart();
        }

        return fee;
    }
    // .......................................................................................................................
}
