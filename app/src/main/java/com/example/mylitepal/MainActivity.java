package com.example.mylitepal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;



public class MainActivity extends AppCompatActivity {

    private Button createData;
    private Button addData;
    private Button deleteData;
    private Button queryData;
    private Button updateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createData = (Button) findViewById(R.id.create_data);
        addData = (Button) findViewById(R.id.add_data);
        deleteData = (Button) findViewById(R.id.delete_data);
        queryData = (Button) findViewById(R.id.query_data);
        updateData = (Button) findViewById(R.id.update_data);

        createData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setAuthor("author2");
                book.setName("china");
                book.setPages(343);
                book.setPrice(23.99);
                book.save();
            }
        });

        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setAuthor("china");
                book.setPages(400);
                book.updateAll("price=? and name=?", "23.99", "china");
            }
        });

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class, "pages<?", "400");
            }
        });

        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = DataSupport.findAll(Book.class);
                for (Book book : books) {
                    Log.d("MainActivity", "book name is" + book.getName());
                    Log.d("MainActivity", "book author is" + book.getAuthor());
                    Log.d("MainActivity", "book pages is" + book.getPages());
                    Log.d("MainActivity", "book price is" + book.getPrice());
                }
            }
        });

    }
}
