package org.brohede.marcus.sqliteapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MountainDetailsActivity extends AppCompatActivity {
    private MountainReaderDbHelper dbHelper;
    private ImageView imgImageView;
    private EditText nameEditText, heightEditText, locationEditText, imgUrlEditText, urlEditText;
    private TextView idTextView;
    private Button updateButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain_details);

        dbHelper = new MountainReaderDbHelper(this);

        Intent intent = getIntent();
        final int id = intent.getIntExtra(MainActivity.MOUNTAIN_ID, 0);
        String name = intent.getStringExtra(MainActivity.MOUNTAIN_NAME);
        String location = intent.getStringExtra(MainActivity.MOUNTAIN_LOCATION);
        final int height = intent.getIntExtra(MainActivity.MOUNTAIN_HEIGHT, 0);
        final String imgURL = intent.getStringExtra(MainActivity.MOUNTAIN_IMAGE);
        String articleURL = intent.getStringExtra(MainActivity.MOUNTAIN_ARTICLE);

        imgImageView = (ImageView) findViewById(R.id.mountain_img);
        nameEditText = (EditText) findViewById(R.id.editText_name);
        heightEditText = (EditText) findViewById(R.id.editText_height);
        locationEditText = (EditText) findViewById(R.id.editText_location);
        imgUrlEditText = (EditText) findViewById(R.id.editText_img_url);
        urlEditText = (EditText) findViewById(R.id.editText_url);
        idTextView = (TextView) findViewById(R.id.mountain_id);
        updateButton = (Button) findViewById(R.id.button_update);
        deleteButton = (Button) findViewById(R.id.button_delete);

        new DownloadImage(imgImageView).execute(imgURL);
        nameEditText.setText(name);
        heightEditText.setText(Integer.toString(height));
        locationEditText.setText(location);
        imgUrlEditText.setText(imgURL);
        urlEditText.setText(articleURL);
        idTextView.setText("ID: " + id);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameEditText.getText().toString().trim().length() > 0 && heightEditText.getText().toString().length() > 0) {
                    int updatedRows = dbHelper.updateData(
                            id,
                            nameEditText.getText().toString(),
                            Integer.parseInt(heightEditText.getText().toString()),
                            locationEditText.getText().toString(),
                            imgUrlEditText.getText().toString(),
                            urlEditText.getText().toString()
                    );
                    if(imgUrlEditText.getText().toString() != imgURL) {
                        new DownloadImage(imgImageView).execute(imgUrlEditText.getText().toString());
                    }
                    Toast.makeText(getApplicationContext(), "Successfully updated " + updatedRows + " row(s)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Name and height cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MountainDetailsActivity.this);
                builder.setTitle("Are you sure?");
                builder.setMessage("All information about this mountain will be deleted!");
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int deletedRows = dbHelper.deleteData(id);
                        Toast.makeText(getApplicationContext(), "Successfully deleted " + deletedRows + " row(s)", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MountainDetailsActivity.this, MainActivity.class);
                        intent.putExtra(MainActivity.COMING_FROM_DETAILS, MainActivity.COMING_FROM_DETAILS);
                        startActivity(intent);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MountainDetailsActivity.this, MainActivity.class);
        intent.putExtra(MainActivity.COMING_FROM_DETAILS, MainActivity.COMING_FROM_DETAILS);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(MountainDetailsActivity.this, MainActivity.class);
            intent.putExtra(MainActivity.COMING_FROM_DETAILS, MainActivity.COMING_FROM_DETAILS);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
