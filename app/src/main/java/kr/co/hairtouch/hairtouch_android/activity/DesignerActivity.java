package kr.co.hairtouch.hairtouch_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.hairtouch.hairtouch_android.R;
import kr.co.hairtouch.hairtouch_android.adapter.DesignerListAdapter;
import kr.co.hairtouch.hairtouch_android.apimanager.DesignerService;
import kr.co.hairtouch.hairtouch_android.apimanager.ServiceGenerator;
import kr.co.hairtouch.hairtouch_android.recyclerview.DividerItemDecoration;
import kr.co.hairtouch.hairtouch_android.recyclerview.RecyclerItemClickListener;
import kr.co.hairtouch.hairtouch_android.model.Designer;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class DesignerActivity extends AppCompatActivity {

    @Bind(R.id.activity_designer_rv) RecyclerView designerRecyclerView;
    List<Designer> designerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer);

        ButterKnife.bind(this);

        // create service
        DesignerService shopService = ServiceGenerator.createService(DesignerService.class);
        Call<List<Designer>> call = shopService.loadDesigners();

        // asynchronous call
        call.enqueue(callback);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    Callback<List<Designer>> callback = new Callback<List<Designer>>() {
        @Override
        public void onResponse(Response<List<Designer>> response, Retrofit retrofit) {
            designerList = response.body();

            designerRecyclerView.setLayoutManager(new LinearLayoutManager(DesignerActivity.this));
            designerRecyclerView.setAdapter(new DesignerListAdapter(DesignerActivity.this, designerList));
            designerRecyclerView.addItemDecoration(new DividerItemDecoration(DesignerActivity.this));
            designerRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(DesignerActivity.this, new RecyclerItemClickListener.OnItemClickListener(){
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(DesignerActivity.this, DesignerDetailActivity.class);
                    intent.putExtra("id", designerList.get(position).getId());
//                    intent.putExtra("name", designerList.get(position).getName());
                    startActivity(intent);
                }
            }));
        }

        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(DesignerActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    };
}
