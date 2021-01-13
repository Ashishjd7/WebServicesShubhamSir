package in.hawknetwork.mywebapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{

    EditText e1,e2,e3;
    Button button, button1;

    String getUrl = "https://ashishjd7.000webhostapp.com/data.php";
    String setUrl = "https://ashishjd7.000webhostapp.com/insert.php";

    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1 = findViewById(R.id.name);
        e2 = findViewById(R.id.salary);
        e3 = findViewById(R.id.phone);

        button = findViewById(R.id.register);
        button1= findViewById(R.id.show);

        requestQueue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                 final ProgressDialog pd = new ProgressDialog(MainActivity.this);
                pd.setMessage("Please Wait");
                pd.show();

                //to send request to the server
                StringRequest myRequest = new StringRequest(Request.Method.POST, setUrl,
                        new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this,""+response,Toast.LENGTH_SHORT).show();

                    }
                },
                        new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this,""+error.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                })
                {
                    //we have added one more block (init block)
                    // an init block is (when an obj is created it will 'execute' before constructor)
                  //we will override the function get params

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError
                    {
                        Map<String, String> myMap = new HashMap<String, String>();
                        myMap.put("name",  e1.getText().toString());
                        myMap.put("salary",e2.getText().toString());
                        myMap.put("phone", e3.getText().toString());

                        return myMap;
                    }
                };

                requestQueue.add(myRequest);
            }
        });

        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final ProgressDialog pd = new ProgressDialog(MainActivity.this);
                pd.setMessage("Please Wait");
                pd.show();

                StringRequest st1 = new StringRequest(Request.Method.POST, getUrl,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response)
                            {
                                pd.dismiss();
                                Toast.makeText(MainActivity.this," "+response,Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                pd.dismiss();
                                Toast.makeText(MainActivity.this," "+error.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });

                requestQueue.add(st1);  //add the request to fetch data
            }
        });

    }
}
