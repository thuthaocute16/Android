package hcmute.edu.vn.foody_28.Activity.Home.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import hcmute.edu.vn.foody_28.Adapter.StoreFragmentAdapter;
import hcmute.edu.vn.foody_28.DatabaseConnection.StoreDB;
import hcmute.edu.vn.foody_28.DatabaseConnection.StoreOfMenuDB;
import hcmute.edu.vn.foody_28.Domain.Store;
import hcmute.edu.vn.foody_28.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyALotFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyALotFragment extends Fragment
{
    private RecyclerView recyclerView_tab_buy_a_lot;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BuyALotFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuyALotFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuyALotFragment newInstance(String param1, String param2) {
        BuyALotFragment fragment = new BuyALotFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_buy_a_lot, container, false);

        recyclerView_tab_buy_a_lot = view.findViewById(R.id.recycle_buy_a_lot);
        recyclerView_tab_buy_a_lot.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        recyclerView_tab_buy_a_lot.setLayoutManager(linearLayoutManager);

        StoreDB storeDB = new StoreDB(view.getContext());
        StoreFragmentAdapter storeFragmentAdapter = new StoreFragmentAdapter(view.getContext());
        storeFragmentAdapter.setData(storeDB.get_list());
        //Toast.makeText(view.getContext(), storeDB.get_list().get(0).getStoreName(), Toast.LENGTH_SHORT).show();
        recyclerView_tab_buy_a_lot.setAdapter(storeFragmentAdapter);
        return view;
    }
}