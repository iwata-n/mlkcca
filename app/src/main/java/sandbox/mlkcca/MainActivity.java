package sandbox.mlkcca;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mlkcca.client.DataElement;
import com.mlkcca.client.DataStore;
import com.mlkcca.client.DataStoreEventListener;
import com.mlkcca.client.MilkCocoa;

public class MainActivity extends AppCompatActivity {

    // PUSHプラットフォーム
    private MilkCocoa milkCocoa;
    private DataStore dataStore;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onPause() {
        super.onPause();
        milkCocoa.logout();
    }

    private void init() {

        milkCocoa = new MilkCocoa("zooiunofuth.mlkcca.com");
        dataStore = milkCocoa.dataStore("messages");
        dataStore.addDataStoreEventListener(new DataStoreEventListener() {
            @Override
            public void onPushed(DataElement dataElement) {
                Log.d("Milkcocoa", "onPushed");
                if (dataElement == null) {
                    Log.e("Milkcocoa", "data is null");
                    return;
                }

                final DataElement data = dataElement;
                final String title = data.getValue("title");
                final String content = data.getValue("content");
                Log.d("Milkcocoa", title + " " + content);
            }

            @Override
            public void onSetted(DataElement dataElement) {
                Log.d("Milkcocoa", "onSetted");
            }

            @Override
            public void onSended(DataElement dataElement) {
                Log.d("Milkcocoa", "onSended");
                if (dataElement == null) {
                    Log.e("Milkcocoa", "data is null");
                    return;
                }

                final DataElement data = dataElement;
                final String title = data.getValue("title");
                final String content = data.getValue("content");
                Log.d("Milkcocoa", title + " " + content);
            }

            @Override
            public void onRemoved(DataElement dataElement) {
                Log.d("Milkcocoa", "onremoved");
            }
        });
        dataStore.on("push");
        dataStore.on("send");
        Log.d("Milkcocoa", "setuped");
    }
}
