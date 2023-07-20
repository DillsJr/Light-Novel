package com.sata.izonovel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sata.izonovel.Model.ListFavoritRequestModel;
import com.sata.izonovel.Model.ListFavoritResponseModel;
import com.sata.izonovel.Retrofit.APIService;
import com.sata.izonovel.adpter.FavoritNovelAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FavoritNovelAdapter favoritNovelAdapter;

    private ProgressDialog progressDialog;

    private EditText etCari;
    private Button btnSearch;

    private List<ListFavoritResponseModel.Documents> documents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        setTitle(R.string.title_favorite);

        recyclerView = findViewById(R.id.reycle_favorite_novel);
        etCari = findViewById(R.id.et_Cari);
        btnSearch = findViewById(R.id.btnSearch);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        onLoadData();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = etCari.getText().toString().trim();
                if (!query.isEmpty()) {
                    performSearch(query);
                } else {
                    onLoadData();
                }
            }
        });
    }

    private void onLoadData() {
        ListFavoritRequestModel listFavoritRequestModel = new ListFavoritRequestModel();
        listFavoritRequestModel.setCollection("novel");
        listFavoritRequestModel.setDatabase("izonovel");
        listFavoritRequestModel.setDataSource("Cluster0");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sabar Masbroo...");
        progressDialog.show();

        APIService.endpoint().favoriteNovel(listFavoritRequestModel).enqueue(new Callback<ListFavoritResponseModel>() {
            @Override
            public void onResponse(Call<ListFavoritResponseModel> call, Response<ListFavoritResponseModel> response) {
                documents = response.body().getDocuments();

                favoritNovelAdapter = new FavoritNovelAdapter(FavoritActivity.this, documents);
                recyclerView.setAdapter(favoritNovelAdapter);

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ListFavoritResponseModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void performSearch(String query) {
        List<ListFavoritResponseModel.Documents> filteredList = new ArrayList<>();
        for (ListFavoritResponseModel.Documents document : documents) {
            String judulNovel = document.getJudul().toLowerCase();
            if (judulNovel.contains(query.toLowerCase())) {
                filteredList.add(document);
            }
        }
        favoritNovelAdapter.setFilteredList(filteredList);
    }
}
