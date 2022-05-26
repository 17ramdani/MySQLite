package com.ramdani.mysqlite

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
class MainActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// below code is to add on click
// listener to our add name button
        val addName = findViewById<Button>(R.id.addName)
        addName.setOnClickListener{
// below we have created
// a new DBHelper class,
// and passed context to it
            val db = DBHelper(this, null)
// creating variables for values
// in name and age edit texts
            val enterName =
                findViewById<EditText>(R.id.enterName)
            val enterAge = findViewById<EditText>(R.id.enterAge)
            val name = enterName.text.toString()
            val age = enterAge.text.toString()
// calling method to add
// name to our database
            db.addName(name, age)
// Toast to message on the screen
            Toast.makeText(this, "$name added to database",
                Toast.LENGTH_LONG).show()
// at last, clearing edit texts
            enterName.text.clear()
            enterAge.text.clear()
        }
// below code is to add on click
// listener to our print name button
        val printName = findViewById<Button>(R.id.printName)
        printName.setOnClickListener{
// creating a DBHelper class
// and passing context to it
            val db = DBHelper(this, null)
// below is the variable for cursor
// we have called method to get
// all names from our database
// and add to name text view
            val cursor = db.getName()
// moving the cursor to first position and
// appending value in the text view
            val name = findViewById<TextView>(R.id.Name)
            val age = findViewById<TextView>(R.id.Age)
            cursor!!.moveToFirst()
            name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
            age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
// moving our cursor to next
// position and appending values
            while(cursor.moveToNext()){
                name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
            }
// at last we close our cursor
            cursor.close()
        }
    }
}