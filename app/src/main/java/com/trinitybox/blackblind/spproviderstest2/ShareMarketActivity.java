package com.trinitybox.blackblind.spproviderstest2;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShareMarketActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private static final String TAG = "FireLog";
    private List<ShareMarket> shareMarketList;
    private ShareMarketListAdapter shareMarketListAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_market);

//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();

        shareMarketList = new ArrayList<>();
        shareMarketListAdapter = new ShareMarketListAdapter(shareMarketList);

        RecyclerView recyclerView = findViewById(R.id.rw_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(shareMarketListAdapter);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("UserThoughts").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null){

                    Log.d(TAG , "Error : " + e.getMessage());
                }

                for (DocumentChange documentChange: documentSnapshots.getDocumentChanges()){

                    switch (documentChange.getType()) {
                        case ADDED:
                            ShareMarket quotes = documentChange.getDocument().toObject(ShareMarket.class);
                            shareMarketList.add(quotes);
                            shareMarketListAdapter.notifyDataSetChanged();
                            break;
                        case REMOVED:
                            ShareMarket quotes1 = documentChange.getDocument().toObject(ShareMarket.class);
                            shareMarketList.remove(quotes1);
                            shareMarketListAdapter.notifyDataSetChanged();
                            break;
                        case MODIFIED:
                            ShareMarket quotes2 = documentChange.getDocument().toObject(ShareMarket.class);
                            shareMarketList.getClass();
                           shareMarketListAdapter.notifyDataSetChanged();
                            break;
                    }

                }

            }
        });
    }
}
