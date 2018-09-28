package zin.byh.org.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.list.*
import zin.byh.org.myapplication.data.User
import zin.byh.org.myapplication.adapter.FbList

class MainActivity : AppCompatActivity() {

    lateinit var fbPushEditTextId: EditText
    lateinit var fbPushEditTextName: EditText
    lateinit var fbPushButton: Button
    lateinit var fbRecyclerView: RecyclerView
    lateinit var fbRecyclerViewLayoutManager: RecyclerView.LayoutManager
    lateinit var  adapter : FbList

    val text: ArrayList<User> = ArrayList()
    val database = FirebaseDatabase.getInstance().getReference()
    val myRef = database.child("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fbPushEditTextId = findViewById(R.id.fbPushEditTextId)
        fbPushEditTextName = findViewById(R.id.fbPushEditTextName)
        fbPushButton = findViewById(R.id.fbPushButton)
        fbRecyclerView = findViewById(R.id.fbRecyclerView)
        fbRecyclerViewLayoutManager = LinearLayoutManager(this)
        fbRecyclerView.layoutManager = fbRecyclerViewLayoutManager


        adapter = FbList(text)
        fbRecyclerView.adapter = adapter

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                text.clear()
                Log.d("listuser size clear", adapter.listuser.size.toString())
                for(childSnapshot in p0.children){
                    text.add(childSnapshot.getValue(User::class.java)!!)
                    adapter.notifyDataSetChanged()
                    Log.d("listuser size", adapter.listuser.size.toString())
                    Log.d("listuser size", "${text.get(adapter.itemCount-1).userid}::${text.get(adapter.itemCount-1).username}")
                }
            }

        })

        fbPushButton.setOnClickListener {
            val user = User(fbPushEditTextId.text.toString(), fbPushEditTextName.text.toString())
            myRef.push().setValue(user)
            fbPushEditTextId.text = null
            fbPushEditTextName.text = null
        }
        Log.d("onCreate", "gggggg")
    }
}
