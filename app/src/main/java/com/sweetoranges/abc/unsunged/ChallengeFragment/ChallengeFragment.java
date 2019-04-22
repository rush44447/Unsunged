package com.sweetoranges.abc.unsunged.ChallengeFragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.dingmouren.layoutmanagergroup.echelon.EchelonLayoutManager;
import org.w3c.dom.Text;
import com.sweetoranges.abc.unsunged.R;
import java.util.Arrays;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class ChallengeFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private EchelonLayoutManager mLayoutManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_challenge,container,false);
        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        mLayoutManager = new EchelonLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new MyAdapter());

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        private int[] icons = {R.drawable.imgview,R.drawable.imgview,R.drawable.imgview,R.drawable.imgview,};
        private int[] bgs = {R.drawable.download,R.drawable.download,R.drawable.download,R.drawable.download,};
        private String[] nickNames = {"rushah","rushah","rushah","rushabh"};
        private String[] descs = {
                "hey there",
                "hey there",
                "hey there",
                "hey there"
        };
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_echelon,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.nickName.setText(nickNames[position % 4]);
        }

        @Override
        public int getItemCount() {
            return 60;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView  nickName;
            public ViewHolder(View itemView) {
                super(itemView);
                nickName = itemView.findViewById(R.id.tv_nickname);

            }
        }
    }


}
