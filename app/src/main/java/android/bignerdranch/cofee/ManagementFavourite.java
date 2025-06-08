package android.bignerdranch.cofee;
import android.content.Context;
import android.widget.Toast;
import android.util.Log;
import java.util.ArrayList;

public class ManagementFavourite {
    private Context context;
    private TinyDB tinyFavDB;


    // ....................................... The Constructor of ManagementFavourite .......................................
    ManagementFavourite(Context context)
    {
        this.context = context;
        this.tinyFavDB = new TinyDB(context);
    }
    // .......................................................................................................................


    // ..................................... GetListFav .................................................................
    public ArrayList<ItemsModel>getListFav()
    {
        // Recall saved items using tinyDB
        ArrayList<ItemsModel> FavList = tinyFavDB.getListObject("FavList");
        // If there is no list we create one
        return (FavList != null) ? FavList : new ArrayList<>();
    }
    // ..................................................................................................................

    // ....................................... The Insert Function ..................................................................
    public void insertFavItems(ItemsModel item)
    {
        // Recall saved items using tinyDB
        ArrayList<ItemsModel> listItem = getListFav();

        // We consider that the element does not exist
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

            Toast.makeText(context,"This Item is Favourite",Toast.LENGTH_SHORT).show();
        }
        else {
            // If it is not available we will replace it
            listItem.add(item);
            // The Item added
            Toast.makeText(context, "Added to your Favourite", Toast.LENGTH_SHORT).show();
        }
        // We save the list
        tinyFavDB.putListObject("FavList", listItem);

    }
    //.................................................................................................................................

    // ................................................ The Remove Function ........................................................
    public void removeItem(ArrayList<ItemsModel> listItemSelected, int position, ChangeNumberItemsListener listener) {
        listItemSelected.remove(position);
        tinyFavDB.putListObject("FavList", listItemSelected);
        listener.onChanged();
    }


// ...............................................................................................................................

}
