package id.semmi.mymvptrial.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.semmi.mymvptrial.Model.Library;
import id.semmi.mymvptrial.Model.RetrofitService;
import id.semmi.mymvptrial.MvpApplication;
import id.semmi.mymvptrial.Presenter.MyPresenter;
import id.semmi.mymvptrial.Presenter.PresenterInteractor;
import id.semmi.mymvptrial.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private Button button;
    private ProgressBar progressBar;
    private TextView textView;
    private RetrofitService service;
    private PresenterInteractor presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.myButton);
        progressBar = (ProgressBar) findViewById(R.id.myProgressBar);
        textView = (TextView) findViewById(R.id.myTextView);
        button.setOnClickListener(this);
        service = ((MvpApplication)getApplication()).getService();
        presenter = new MyPresenter(this,service);

    }

    @Override
    public void onClick(View v) {
      int id = v.getId();
      switch (id){
          case R.id.myButton:
              presenter.loadData();
              Toast.makeText(MainActivity.this, "asd", Toast.LENGTH_SHORT).show();
              break;
      }
    }


    public void setOnProgress(){
        button.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void getDataResult(List<Library> libraries){
        textView.setText(libraries.get(0).getJudul());
        progressBar.setVisibility(View.GONE);
        button.setEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }


}
