package zin.byh.org.myapplication.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import zin.byh.org.myapplication.R
import zin.byh.org.myapplication.data.User

class FbList(val listuser: ArrayList<User>) : RecyclerView.Adapter<FbList.itemViewHelper>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHelper {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
        return itemViewHelper(view)
    }

    override fun getItemCount(): Int {
        return listuser.size
    }

    override fun onBindViewHolder(holder: itemViewHelper, position: Int) {
        holder.userId!!.text = listuser.get(position).userid
        holder.userName!!.text = listuser.get(position).username
        Log.d("position value", position.toString())
    }


    class itemViewHelper(itemView: View, var userId:TextView? = null, var userName: TextView? = null) : RecyclerView.ViewHolder(itemView) {
        init {
            userId = itemView.findViewById(R.id.userId)
            userName = itemView.findViewById(R.id.userName)
        }
    }
}