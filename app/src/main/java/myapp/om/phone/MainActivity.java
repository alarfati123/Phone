package myapp.om.phone;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements        View.OnClickListener {

    private static final String TAG = "PhoneAuthActivity";
    private FirebaseAuth mAuth;
    private ViewFlipper viewFlipper;
    private ActionBar toolbar;



    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = getSupportActionBar();
        int image[]={R.drawable.slider1,R.drawable.slider2,R.drawable.slider3,R.drawable.slider4};
        viewFlipper=findViewById(R.id.v_flipper);

        for(int images:image){
            imageviwer(images);

        }
        Button mSignOutButton = (Button) findViewById(R.id.sign_out_button);
        TextView fireBaseId = (TextView) findViewById(R.id.detail);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth!=null){
            fireBaseId.setText(mAuth.getCurrentUser().getPhoneNumber());
        }
        mSignOutButton.setOnClickListener(this);
    }

    public void imageviwer(int image)
    {
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }

    @Override    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_out_button:
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, PhoneAuthActivity.class));
                finish();
                break;
        }
    }
}