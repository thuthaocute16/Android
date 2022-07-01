package hcmute.edu.vn.foody_28.Activity.Home.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_28.Adapter.StoreFragmentAdapter;
import hcmute.edu.vn.foody_28.DatabaseConnection.StoreDB;
import hcmute.edu.vn.foody_28.Domain.Store;
import hcmute.edu.vn.foody_28.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CloseToYouFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CloseToYouFragment extends Fragment {

    private RecyclerView recyclerView_tab_close_to_you;
    private List<Store> storeList3;
    private Context context;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CloseToYouFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CloseToYouFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CloseToYouFragment newInstance(String param1, String param2) {
        CloseToYouFragment fragment = new CloseToYouFragment();
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
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_close_toy_ou, container, false);

        // Add the following lines to create RecyclerView
        recyclerView_tab_close_to_you = view.findViewById(R.id.recycle_close_to_you);
        recyclerView_tab_close_to_you.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        recyclerView_tab_close_to_you.setLayoutManager(linearLayoutManager);
        context = this.getContext();

        StoreDB storeDB = new StoreDB(view.getContext());
        StoreFragmentAdapter storeFragmentAdapter = new StoreFragmentAdapter(view.getContext());
        storeFragmentAdapter.setData(storeDB.get_list());
        recyclerView_tab_close_to_you.setAdapter(storeFragmentAdapter);
        return view;
    }

    public void swap_quicksort(Store store1, Store store2)
    {
        Store temp = store1;
        store1 = store2;
        store2 = temp;
    }
}